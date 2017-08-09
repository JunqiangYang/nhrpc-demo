package org.jsets.rpc.processor;

import java.util.concurrent.atomic.AtomicInteger;

import org.jsets.rpc.transfer.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * @ClassName: ServiceProcessor
 * @Description: 服务处理器
 * @author wangjie
 *
 */
public class ServiceProcessor {

	private static Logger logger = LoggerFactory.getLogger(ServiceProcessor.class);
	private static final AtomicInteger atomicInteger = new AtomicInteger(0);
	public static Object handleRequest(RequestWrapper request) {
		Object result = null;
		try {
			String serviceName = request.getServiceName();
			String methodName = request.getMethodName();
			final String clientIp = request.getClientIP();
			final String user = request.getUser();
			final String password = request.getPassword();
			Service service = SerivceExporter.getServiceByName(serviceName);
			if (service == null) {
				throw new RuntimeException("找不到名称为：[" + serviceName + "]的服务。");
			}
			Class<?> processorClass = service.getProviderClass();
			final Object processor = service.getProvider();
			final Object[] args = request.getArgs();
			final MethodAccess method = MethodAccess.get(processorClass);
			final int methodIndex = method.getIndex(methodName, request.getArgsTypes());
			RemoteContext remoteContext = RemoteContext.getRemoteContext();
			remoteContext.setClientIp(clientIp);
			remoteContext.setUser(user);
			remoteContext.setPassword(password);
			if (service.isAsyn()) {
				System.out.println(service+" is yibu");
				new Thread(new Runnable() {
					public void run() {
						method.invoke(processor, methodIndex, args);
					}
				},service.getId()+"-thread-"+atomicInteger.getAndIncrement()).start();
				return result;
			}else{
				result = method.invoke(processor, methodIndex, args);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return result;
	}
}
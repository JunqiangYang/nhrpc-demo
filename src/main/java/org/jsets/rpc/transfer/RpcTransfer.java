package org.jsets.rpc.transfer;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.jsets.rpc.conf.SerivceConfig;
import org.jsets.rpc.processor.SerivceExporter;
import org.jsets.rpc.processor.Service;
import org.jsets.rpc.protocol.hessian.HessionHandler;
import org.jsets.rpc.server.NamedThreadFactory;
import org.jsets.rpc.server.Server;
import com.caucho.hessian.client.HessianProxyFactory;


public class RpcTransfer {
	
	public static final  SerivceConfig serivceConfig = SerivceConfig.insSerivceConfig();
	public static final  HessianProxyFactory proxyFactory = new HessianProxyFactory();
	public static final Map<String,Object> proxyMaps = new HashMap<String,Object>();
	
	
	public static void addService(Service service){
		SerivceExporter.addToServiceMap(service);
	}
	
	public static void startServer(int port,String contextRoot){
		serivceConfig.setPort(port);
		serivceConfig.setContextRoot(contextRoot);
		SerivceExporter.export();//导出服务
		ThreadFactory threadFactory = new NamedThreadFactory(serivceConfig.getContextRoot()+"-PROCESS-");
		ExecutorService threadPoolExecutor = new ThreadPoolExecutor(serivceConfig.getCorePoolSize(), 
				serivceConfig.getMaxPoolSize(), serivceConfig.getKeepAliveTime(),
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);
		HessionHandler handler = new HessionHandler(serivceConfig,threadPoolExecutor);
		Server server = new Server(handler,serivceConfig);
		server.start();
	}
	
	public static <T> T createClientProxy(Class api,String url,String name){
		try {
			T t = (T) proxyFactory.create(api, url);
			proxyMaps.put(name, t);
			return t;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T getClientProxy(String name){
		return (T)proxyMaps.get(name);
	}
	
}

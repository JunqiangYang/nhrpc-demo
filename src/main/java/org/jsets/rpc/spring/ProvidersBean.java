package org.jsets.rpc.spring;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.ArrayList;
import org.jsets.rpc.conf.SerivceConfig;
import org.jsets.rpc.processor.SerivceExporter;
import org.jsets.rpc.processor.Service;
import org.jsets.rpc.protocol.hessian.HessionHandler;
import org.jsets.rpc.server.NamedThreadFactory;
import org.jsets.rpc.server.Server;
import org.jsets.rpc.utils.ReflectKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProvidersBean implements ApplicationContextAware,InitializingBean, DisposableBean,ApplicationListener<ContextRefreshedEvent>{
	private final Logger logger = LoggerFactory.getLogger(ProvidersBean.class);

	public boolean tcpNoDelay= true;//启用 禁用Nagle算法
	public boolean reuseAddress= true;//是否允许重用Socket所绑定的本地地址
	public int port= 8081;//端口
	public int corePoolSize= 4;//核心进程数
	public int maxPoolSize= 100;//最大进程数
	public int keepAliveTime= 3000;//连接保存时间
	public boolean token= false;//权限TOKEN
	public String contextRoot= "nhrpc";//rpc 访问上下文
	private static boolean isStarted = false;
	
	private Server server;
	private List<ProviderBean> providerBeans = new ArrayList<ProviderBean>();
	protected ApplicationContext applicationContext;
	protected SerivceConfig serivceConfig = SerivceConfig.insSerivceConfig();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		serivceConfig.setPort(this.getPort());
		serivceConfig.setContextRoot(this.getContextRoot());
		for(ProviderBean providerBean:providerBeans){
			Service service = new Service();
			service.setId(providerBean.getBeanId());
			service.setName(providerBean.getBeanId());
			service.setOverload(providerBean.isOverload());
			service.setTypeClass(ReflectKit.forName(providerBean.getInterfaze()));
			Object refBean = applicationContext.getBean(providerBean.getRef());
			service.setProviderClass(refBean.getClass());
			service.setProvider(refBean);
			SerivceExporter.addToServiceMap(service);
		}
	    initializeServer();
	}
	

	
	@Override
	public void destroy() throws Exception {
		if(server!=null){
			server.stop();
		}
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!isStarted){
			isStarted = true;
			startServer();
		}
	}

	protected void initializeServer(){
		SerivceExporter.export();//导出服务
		ThreadFactory threadFactory = new NamedThreadFactory(serivceConfig.getContextRoot()+"-PROCESS-");
		ExecutorService threadPoolExecutor = new ThreadPoolExecutor(serivceConfig.getCorePoolSize(), 
				serivceConfig.getMaxPoolSize(), serivceConfig.getKeepAliveTime(),
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);
		HessionHandler handler = new HessionHandler(serivceConfig,threadPoolExecutor);
		server = new Server(handler,serivceConfig);
	}
	
	protected void startServer(){
		if(server!=null){
			server.start();
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the tcpNoDelay
	 */
	public boolean isTcpNoDelay() {
		return tcpNoDelay;
	}

	/**
	 * @param tcpNoDelay the tcpNoDelay to set
	 */
	public void setTcpNoDelay(boolean tcpNoDelay) {
		this.tcpNoDelay = tcpNoDelay;
	}

	/**
	 * @return the reuseAddress
	 */
	public boolean isReuseAddress() {
		return reuseAddress;
	}

	/**
	 * @param reuseAddress the reuseAddress to set
	 */
	public void setReuseAddress(boolean reuseAddress) {
		this.reuseAddress = reuseAddress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the corePoolSize
	 */
	public int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * @param corePoolSize the corePoolSize to set
	 */
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	/**
	 * @return the maxPoolSize
	 */
	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * @param maxPoolSize the maxPoolSize to set
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	/**
	 * @return the keepAliveTime
	 */
	public int getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * @param keepAliveTime the keepAliveTime to set
	 */
	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	/**
	 * @return the token
	 */
	public boolean getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(boolean token) {
		this.token = token;
	}

	/**
	 * @return the contextRoot
	 */
	public String getContextRoot() {
		return contextRoot;
	}

	/**
	 * @param contextRoot the contextRoot to set
	 */
	public void setContextRoot(String contextRoot) {
		this.contextRoot = contextRoot;
	}

	/**
	 * @return the server
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(Server server) {
		this.server = server;
	}

	/**
	 * @return the providerBeans
	 */
	public List<ProviderBean> getProviderBeans() {
		return providerBeans;
	}

	/**
	 * @param providerBeans the providerBeans to set
	 */
	public void setProviderBeans(List<ProviderBean> providerBeans) {
		this.providerBeans = providerBeans;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}
}
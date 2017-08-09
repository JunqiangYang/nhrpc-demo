package org.jsets.rpc.conf;

/**
 * @ClassName: SerivceConfig
 * @Description: 配置
 * @author wangjie
 *
 */ 
public class SerivceConfig {
	
	public boolean tcpNoDelay= true;//启用 禁用Nagle算法
	public boolean reuseAddress= true;//是否允许重用Socket所绑定的本地地址
	public int port= 8081;//端口
	public int corePoolSize= 4;//核心进程数
	public int maxPoolSize= 100;//最大进程数
	public int keepAliveTime= 300;//连接保存时间
	public boolean token= false;//权限TOKEN
	public String contextRoot= "nhrpc";//rpc 访问上下文
	
	private static SerivceConfig serivceConfig = null;
	
	
	private SerivceConfig(){}
	
	private SerivceConfig(String contextRoot,int port,int corePoolSize,int maxPoolSize,int keepAliveTime,boolean tcpNoDelay
			,boolean reuseAddress,boolean token){
		this.contextRoot = contextRoot;
		this.port  = port;
		this.corePoolSize = corePoolSize;
		this.maxPoolSize = maxPoolSize;
		this.keepAliveTime = keepAliveTime;
		this.tcpNoDelay = tcpNoDelay;
		this.reuseAddress = reuseAddress;
		this.token = token;
	}
	
	
	public static SerivceConfig insSerivceConfig(){
		if(serivceConfig==null){
			serivceConfig = new SerivceConfig();
		}
		return serivceConfig;
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
}
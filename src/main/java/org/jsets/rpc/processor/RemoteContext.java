package org.jsets.rpc.processor;

public class RemoteContext {
	
	private String clientIp;
	private String user;
	private String password;
	
	private static final ThreadLocal<RemoteContext> LOCAL = new ThreadLocal<RemoteContext>() {
		@Override
		protected RemoteContext initialValue() {
			return new RemoteContext();
		}
	};
	
	/**
	 * get context.
	 * 
	 * @return context
	 */
	public static RemoteContext getRemoteContext() {
	    return LOCAL.get();
	}
	
	/**
	 * remove context.
	 * 
	 * @see com.alibaba.dubbo.rpc.filter.ContextFilter
	 */
	public static void removeRemoteContext() {
	    LOCAL.remove();
	}

	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
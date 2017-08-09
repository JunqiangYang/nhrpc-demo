package org.jsets.rpc.spring;

public class ProviderBean {
	
	private String beanId;
	private String interfaze;
	private String ref;
	private boolean overload = true;
	private boolean asyn = false;
	
	/**
	 * @return the interfaze
	 */
	public String getInterfaze() {
		return interfaze;
	}
	/**
	 * @param interfaze the interfaze to set
	 */
	public void setInterfaze(String interfaze) {
		this.interfaze = interfaze;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the beanId
	 */
	public String getBeanId() {
		return beanId;
	}
	/**
	 * @param beanId the beanId to set
	 */
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	/**
	 * @return the overload
	 */
	public boolean isOverload() {
		return overload;
	}
	/**
	 * @param overload the overload to set
	 */
	public void setOverload(boolean overload) {
		this.overload = overload;
	}
	/**
	 * @return the asyn
	 */
	public boolean isAsyn() {
		return asyn;
	}
	/**
	 * @param asyn the asyn to set
	 */
	public void setAsyn(boolean asyn) {
		this.asyn = asyn;
	}	
	
	
}
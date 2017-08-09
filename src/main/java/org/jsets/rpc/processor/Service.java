package org.jsets.rpc.processor;

import java.io.Serializable;

/**
 * @ClassName: Service
 * @Description: 服务
 * @author wangjie
 * @date 2015年7月11日 下午20:31:02
 */ 
public class Service implements Serializable{

	private static final long serialVersionUID = 2351769180636491630L;
	
	protected String id;
	protected String name;
	protected Class<?> typeClass;
	protected Class<?> providerClass;
	private boolean overload =false;
	protected Object provider;
	protected boolean asyn = false;
	

	public Service(){}
	
	public Service(String name,Class<?> typeClass,Class<?> providerClass){
		this.name = name;
		this.typeClass = typeClass;
		this.providerClass = providerClass;		
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the typeClass
	 */
	public Class<?> getTypeClass() {
		return typeClass;
	}

	/**
	 * @param typeClass the typeClass to set
	 */
	public void setTypeClass(Class<?> typeClass) {
		this.typeClass = typeClass;
	}

	/**
	 * @return the providerClass
	 */
	public Class<?> getProviderClass() {
		return providerClass;
	}

	/**
	 * @param providerClass the providerClass to set
	 */
	public void setProviderClass(Class<?> providerClass) {
		this.providerClass = providerClass;
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
	 * @return the provider
	 */
	public Object getProvider() {
		if(this.provider==null){
			try {
				this.provider = this.getProviderClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return this.provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(Object provider) {
		this.provider = provider;
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
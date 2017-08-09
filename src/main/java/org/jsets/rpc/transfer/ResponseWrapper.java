package org.jsets.rpc.transfer;

import java.io.Serializable;

/**
 * @ClassName: Response
 * @Description: RESPONSE 包装
 * @author wangjie
 * @date 2016年7月4日 上午10:36:39
 *
 */
public class ResponseWrapper implements Serializable{

	private long id = 0;
	private Object result;
	private String errorMsg;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}

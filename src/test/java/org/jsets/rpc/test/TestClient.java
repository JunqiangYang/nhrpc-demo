package org.jsets.rpc.test;

import com.caucho.hessian.client.HessianProxyFactory;

public class TestClient {

	public static void main(String[] args) throws Exception {
		HessianProxyFactory proxyFactory = new HessianProxyFactory();
		HelloWord helloWord = (HelloWord) proxyFactory.create(HelloWord.class, "http://localhost:8081/test/helloWord");
		
		String ret = helloWord.hello("word!!");
		System.out.println(ret);
	}

}
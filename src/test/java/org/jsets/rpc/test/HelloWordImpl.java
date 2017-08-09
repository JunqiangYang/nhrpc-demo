package org.jsets.rpc.test;

public class HelloWordImpl implements HelloWord {

	@Override
	public String hello(String name) {
		return "hello:"+name;
	}

}
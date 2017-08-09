package org.jsets.rpc.test;

import org.jsets.rpc.processor.Service;
import org.jsets.rpc.transfer.RpcTransfer;

public class TestServer {

	public static void main(String[] args) {
		//导出服务
		RpcTransfer.addService(new Service("helloWord",HelloWord.class,HelloWordImpl.class));
		//启动服务
		RpcTransfer.startServer(8081, "test");
	}

}

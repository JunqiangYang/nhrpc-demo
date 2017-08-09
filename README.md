 **nhrpc**  是一个简单高效的RPC框架，基于netty3.7和hessian4.0构建。


> 1：编码方式发布服务
```
>         //导出服务，可添加多个服务
>         RpcTransfer.addService(new Service("helloWord",HelloWord.class,HelloWordImpl.class));
>         //启动服务
>         RpcTransfer.startServer(8081, "test"); 
```
> 2：使用SPRING配置发布服务
>  
```
<bean id="helloWordImpl" class="org.jsets.rpc.test.HelloWordImpl"></bean>
>     <nhrpc:providers id="nhrpcServer" port="8081" contextRoot="test">
>         <!-- 服务，可发布多个服务 -->
>         <nhrpc:provider id="helloWord" interface="org.jsets.rpc.test.HelloWord" ref="helloWordImpl"/>
>     </nhrpc:providers>
```
> 3：编码方式调用服务
```
>         HessianProxyFactory proxyFactory = new HessianProxyFactory();
>         HelloWord helloWord = (HelloWord) proxyFactory.create(HelloWord.class, "http://localhost:8081/test/helloWord");
>         String ret = helloWord.hello("word!!");
```
> 4：使用spring配置调用服务
```
>       <nhrpc:reference id="helloWord" interface="org.jsets.rpc.test.HelloWord" 
>                      url="http://localhost:8081/test/helloWord"></nhrpc:reference>
```



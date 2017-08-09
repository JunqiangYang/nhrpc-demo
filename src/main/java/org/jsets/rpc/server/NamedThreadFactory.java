package org.jsets.rpc.server;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: NamedThreadFactory
 * @Description: 可命名线程工厂
 * @author wangjie
 * @date 2016年8月11日 下午3:31:28
 *
 */ 
public class NamedThreadFactory implements ThreadFactory {

	static final AtomicInteger poolNumber = new AtomicInteger(1);

	final AtomicInteger threadNumber = new AtomicInteger(1);
	final ThreadGroup group;
	final String namePrefix;
	final boolean isDaemon;

	public NamedThreadFactory() {
		this("pool");
	}

	public NamedThreadFactory(String name) {
		this(name, false);
	}

	public NamedThreadFactory(String preffix, boolean daemon) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
				.getThreadGroup();
		namePrefix = preffix + "-" + poolNumber.getAndIncrement() + "-thread-";
		isDaemon = daemon;
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix
				+ threadNumber.getAndIncrement(), 0);
		t.setDaemon(isDaemon);
		if (t.getPriority() != Thread.NORM_PRIORITY) {
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}

}

package com.chen.pub.lock;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RLock;

public class RedissonLockTest {

	/**
	 * 使用ip地址和端口创建Redisson
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public Redisson getRedisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1" + ":" + "6379");
		Redisson redisson = (Redisson) Redisson.create(config);
		System.out.println("成功连接Redis Server 服务器");
		return redisson;
	}

	public RLock getLock(String key) {
		return getRedisson().getLock(key);
	}
}

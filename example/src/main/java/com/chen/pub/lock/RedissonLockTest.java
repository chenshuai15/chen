package com.chen.pub.lock;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RLock;

public class RedissonLockTest {

	/**
	 * ʹ��ip��ַ�Ͷ˿ڴ���Redisson
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public Redisson getRedisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1" + ":" + "6379");
		Redisson redisson = (Redisson) Redisson.create(config);
		System.out.println("�ɹ�����Redis Server ������");
		return redisson;
	}

	public RLock getLock(String key) {
		return getRedisson().getLock(key);
	}
}

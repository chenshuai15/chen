package com.chen.pub.lock;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RLock;

public class KeyLock {

	private Config redissonConfig;

	private static KeyLock keyLock = new KeyLock();

	public static KeyLock getInstance() {
		return keyLock;
	}

	public KeyLock() {
		this.redissonConfig = new Config();
		redissonConfig.useSingleServer().setAddress("127.0.0.1:6379");
	}

	private ThreadLocal<Redisson> redissonLocal = new ThreadLocal<Redisson>();

	public RLock getLock(String key) {
		Redisson redis = getRedisson();
		RLock lock = redis.getLock(key);

		return lock;
	}

	private Redisson getRedisson() {
		if (redissonLocal.get() == null) {
			Redisson redisson = (Redisson) Redisson.create(redissonConfig);
			redissonLocal.set(redisson);
		}

		return redissonLocal.get();
	}

	public void shutdown() {
		if (redissonLocal.get() != null) {
			redissonLocal.get().shutdown();
			redissonLocal.remove();
		}
	}
}

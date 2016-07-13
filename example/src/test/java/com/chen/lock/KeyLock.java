package com.chen.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;


public class KeyLock<K> {
	// 保存所有锁定的KEY及其信号量
	private final ConcurrentMap<String, Semaphore> map = new ConcurrentHashMap<String, Semaphore>();
	// 保存每个线程锁定的KEY及其锁定计数
	private final ThreadLocal<Map<String, LockInfo>> local = new ThreadLocal<Map<String, LockInfo>>() {
		@Override
		protected Map<String, LockInfo> initialValue() {
			return new HashMap<String, LockInfo>();
		}
	};

	/**
	 * 锁定key，其他等待此key的线程将进入等待，直到调用{@link #unlock(K)}
	 * 使用hashcode和equals来判断key是否相同，因此key必须实现{@link #hashCode()}和
	 * {@link #equals(Object)}方法
	 * 
	 * @param key
	 */
	public void lock(String key) {
		if (key == null)
			return;
		LockInfo info = local.get().get(key);
		if (info == null) {
			Semaphore current = new Semaphore(1);
			current.acquireUninterruptibly();
			Semaphore previous = map.put(key, current);
			if (previous != null)
				previous.acquireUninterruptibly();
			local.get().put(key, new LockInfo(current));
		} else {
			info.lockCount++;
		}
	}

	/**
	 * 释放key，唤醒其他等待此key的线程
	 * 
	 * @param key
	 */
	public void unlock(String key) {
		if (key == null)
			return;
		LockInfo info = local.get().get(key);
		if (info != null && --info.lockCount == 0) {
			info.current.release();
			map.remove(key, info.current);
			local.get().remove(key);
		}
	}

	private static class LockInfo {
		private final Semaphore current;
		private int lockCount;

		private LockInfo(Semaphore current) {
			this.current = current;
			this.lockCount = 1;
		}
	}
}
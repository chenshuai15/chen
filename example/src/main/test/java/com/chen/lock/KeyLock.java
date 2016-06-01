package com.chen.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;


public class KeyLock<K> {
	// ��������������KEY�����ź���
	private final ConcurrentMap<String, Semaphore> map = new ConcurrentHashMap<String, Semaphore>();
	// ����ÿ���߳�������KEY������������
	private final ThreadLocal<Map<String, LockInfo>> local = new ThreadLocal<Map<String, LockInfo>>() {
		@Override
		protected Map<String, LockInfo> initialValue() {
			return new HashMap<String, LockInfo>();
		}
	};

	/**
	 * ����key�������ȴ���key���߳̽�����ȴ���ֱ������{@link #unlock(K)}
	 * ʹ��hashcode��equals���ж�key�Ƿ���ͬ�����key����ʵ��{@link #hashCode()}��
	 * {@link #equals(Object)}����
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
	 * �ͷ�key�����������ȴ���key���߳�
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
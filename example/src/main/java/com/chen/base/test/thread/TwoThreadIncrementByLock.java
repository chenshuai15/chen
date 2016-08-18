package com.chen.base.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过重入锁来进行模拟两个线程轮流加一的工作<br>
 * Condition相当于wait、notify
 * @author heyi
 *
 */
public class TwoThreadIncrementByLock {
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	int i = 0;

	public static void main(String[] args) {

		final TwoThreadIncrementByLock obj = new TwoThreadIncrementByLock();

		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					obj.incrementNum();
				}
			}
		});

		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					obj.incrementNum();
				}
			}
		});

		a.start();
		b.start();
	}

	private void incrementNum() {
		lock.lock();
		try {
			i++;
			System.out.println(Thread.currentThread().getId() + ":" + i);
			condition.signal();
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

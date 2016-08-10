package com.chen.base.test.thread;

/**
 * 线程间通过wait notify进行通信
 * <p>
 * 
 * 例子中实现功能为对一个整数 2个线程依次进行累加
 * @author heyi
 *
 */
public class TestNotifyWait {
	
	int i = 0;
	
	public synchronized void increment(){
		System.out.println("thread:" + Thread.currentThread().getName() + " i:" + i ++);
		try {
			this.notify();
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		final TestNotifyWait num = new TestNotifyWait();
		
		Thread threadA = new Thread("A"){
			@Override
			public void run() {
				for(int i = 0; i < 50; i ++){
					num.increment();
				}
			}
			
		};
		
		Thread threadB = new Thread("B") {
			@Override
			public void run() {
				for(int i = 0; i < 50; i ++){
					num.increment();
				}
			}
		};
		
		threadA.start();
		threadB.start();
	}
	
}

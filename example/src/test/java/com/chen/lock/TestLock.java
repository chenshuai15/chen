package com.chen.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestLock {
	public static final KeyLock<String> lock = new KeyLock<String>();
	
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		for(int i = 0; i < 10; i ++){
			pool.submit(new KockRunner());
		}
		
		pool.shutdown();
//		while(!pool.isTerminated()){
//			System.out.println("»¹Ã»½áÊø");
//		}
	}
	
}

class KockRunner implements Runnable{

	public void run() {
		transfer();
	}
	
	public void transfer() {
		TestLock.lock.lock("testid");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			TestLock.lock.unlock("testid");
		}
	}
}


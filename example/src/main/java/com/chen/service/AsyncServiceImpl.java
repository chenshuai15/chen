package com.chen.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncServiceImpl implements IAsyncService {

	@Override
	@Async
	public void testAsync() {
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}

}

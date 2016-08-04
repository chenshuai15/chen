package com.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PrintTask {
	
	@Transactional
	@Scheduled(cron = "0 * * * * *")
	public void printT(){
		System.out.println("task execute!");
	}
}

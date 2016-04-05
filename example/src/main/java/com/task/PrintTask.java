package com.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintTask {
	
	@Scheduled(cron = "0 * * * * *")
	public void printT(){
		System.out.println("task execute!");
	}
}

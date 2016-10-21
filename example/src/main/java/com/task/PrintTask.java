package com.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PrintTask {
	
	AtomicInteger i = new AtomicInteger(0);
	
	@Transactional
	@Scheduled(cron = "0/20 * * * * *")
	public void printT(){
		System.out.println("printT:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
//	
//	@Scheduled(cron = "0,30,55 * * * * *")
//	public void printA(){
//		System.out.println("printA:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
	
	
//	@Scheduled(cron = "40-59/10 * * * * *")
//	public void printB(){
//		System.out.println("printB" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
	
//	@Scheduled(cron = "0 46 17 ? * WED")
//	public void printC(){
//		System.out.println("printC:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	}
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void printB() throws InterruptedException{
		if(i.get() == 1 || i.get() == 2){
//			Thread.sleep((long)(1000*60*1.5));//睡眠3分钟
		}
		
		System.out.println("任务执行，序号：" + i.getAndIncrement() + " date :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}

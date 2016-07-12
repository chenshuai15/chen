package com.chen.quartz.task;

import org.springframework.stereotype.Component;

@Component("taskPojo")
public class TaskPojo {
	
	public void run(){
		System.out.println("TaskPojo run ...");
	}
}

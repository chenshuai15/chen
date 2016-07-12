package com.chen.quartz.task;

import javax.sql.DataSource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component("taskPojo")
public class TaskPojo extends QuartzJobBean{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		//用来处理自动注入问题
		// Process @Autowired injection for the given target object, based on the current web application context. 
	    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		System.out.println("TaskPojo run ...");
	}
}

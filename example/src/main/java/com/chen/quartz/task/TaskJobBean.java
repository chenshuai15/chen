package com.chen.quartz.task;

import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.chen.quartz.task.dao.QuartzDao;

public class TaskJobBean extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(TaskJobBean.class);

	@Autowired
	private QuartzDao quartzDao;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// 用来处理自动注入问题
		// Process @Autowired injection for the given target object, based on
		// the current web application context.
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		List<Map<String, Object>> triggers = quartzDao.getQrtzTriggers();
		if (triggers != null) {
			for (Map<String, Object> trigger : triggers) {
				logger.info(trigger.get("trigger_name") + "|" + trigger.get("next_fire_time") + "|");
			}
		}

		logger.info("TaskJobBean run ...");
	}

}

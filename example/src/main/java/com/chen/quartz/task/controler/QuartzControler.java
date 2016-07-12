package com.chen.quartz.task.controler;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.quartz.task.service.SchedulerService;

@Controller
public class QuartzControler {
	
	@Autowired
	private SchedulerService scheduler;

	public String start(ServletRequest request) {
		return "success";
	}

	@RequestMapping("/quartz/pauseTrigger.do")
	@ResponseBody
	public String pauseTrigger(ServletRequest request) {
		String name = request.getParameter("triggerName");
		String group = request.getParameter("triggerGroup");

		scheduler.pauseTrigger(name, group);

		return "success";
	}

	@RequestMapping("/quartz/resumeTrigger.do")
	@ResponseBody
	public String resumeTrigger(ServletRequest request) {
		String name = request.getParameter("triggerName");
		String group = request.getParameter("triggerGroup");

		scheduler.resumeTrigger(name, group);

		return "success";
	}

	public String removeTrigdger(ServletRequest request) {
		return "success";
	}
}

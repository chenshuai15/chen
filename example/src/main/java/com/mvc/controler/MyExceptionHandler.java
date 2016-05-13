package com.mvc.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * �쳣����
 * @author heyi
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler
	public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response , Throwable ex){
		ModelAndView model = new ModelAndView();
		model.setViewName("/errorpages/error");
		model.addObject("exp",ex);
		model.addObject(ex);//��ָ�����ֵ�����£�����������shortName������ȡ���� runtimeException
		return model;
	}
}

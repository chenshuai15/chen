package com.mvc.controler;

import java.util.concurrent.TimeUnit;

import org.redisson.core.RLock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chen.pub.lock.KeyLock;
import com.mvc.vo.User;

@Controller
public class MainControler {
	@RequestMapping("/main.do")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/login");
		return view;
	}
	
	@RequestMapping("/login.do")
	public ModelAndView login(User user){
		ModelAndView view = new ModelAndView();
		view.setViewName("/loginSuccess");
		view.addObject(user);
		return view;
	}
	
	@RequestMapping("/testExceptionHandler.do")
	public ModelAndView testExceptionHandler(int i) {
		if(i == 0){
			throw new RuntimeException("±¨´í");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/loginSuccess");
		return view;
	}
	
	@RequestMapping("/testParams.do")
	@ResponseBody
	public String testParams(@RequestParam(value = "userName", required = true,defaultValue = "ÀîÃ÷") String name){
		
		return name;
	}
	
	@RequestMapping("/testLock.do")
	@ResponseBody
	public String testLock() {
		RLock lock = KeyLock.getInstance().getLock("pid_add");
		try {
			if (lock.tryLock(10, TimeUnit.SECONDS)) {
				System.out.println("get lock");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try{
				lock.unlock();
			}catch(Exception ex){
				//³Ôµô
			}
			KeyLock.getInstance().shutdown();
		}
		return "success";
	}
}

package com.mvc.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}

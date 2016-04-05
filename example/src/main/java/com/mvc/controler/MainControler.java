package com.mvc.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
package com.mvc.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainControler {
	@RequestMapping("/main.do")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView();
		
		return view;
	}
	
	@RequestMapping("/str.do")
	@ResponseBody
	public String stringUrl(){
		return "test string";
	}
}

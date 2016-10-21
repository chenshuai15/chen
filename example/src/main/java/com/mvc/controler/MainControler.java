package com.mvc.controler;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.redisson.core.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import com.chen.acmq.consumer.SpringConsumer;
import com.chen.acmq.producter.SpringProducer;
import com.chen.pub.lock.KeyLock;
import com.chen.service.IAsyncService;
import com.mvc.vo.User;

@Controller
public class MainControler {
	
	@Autowired
	private SpringConsumer consumer;
	
	@Autowired
	private SpringProducer producter;
	
	@Autowired
	private LongTimeAsyncCallService longTimeAsyncCallService;
	
	@Autowired
	IAsyncService asyncService;
	
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
			throw new RuntimeException("抛出错误");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/loginSuccess");
		return view;
	}
	
	@RequestMapping("/testParams.do")
	@ResponseBody
	public String testParams(@RequestParam(value = "userName", required = true,defaultValue = "aa") String name){
		
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
			}
			KeyLock.getInstance().shutdown();
		}
		return "success";
	}
	
	@RequestMapping("/testMQ.do")
	@ResponseBody
	public String testMQ() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		String msg = request.getParameter("msg");
		
		producter.send(msg);
//		consumer.recive();
		
		return "success";
	}
	
	@RequestMapping(value = "/asynctask", method = RequestMethod.GET)
	public DeferredResult<ModelAndView> asyncTask() {
		DeferredResult<ModelAndView> deferredResult = new DeferredResult<ModelAndView>();
		System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
		longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
			@Override
			public void callback(Object result) {
				System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
				ModelAndView mav = new ModelAndView("remotecalltask");
				mav.addObject("result", result);
				deferredResult.setResult(mav);
			}
		});
		return deferredResult;
	}
	
	@RequestMapping("/testAsync.do")
	public String testAsync() {
		asyncService.testAsync();
		return "success";
	}
}

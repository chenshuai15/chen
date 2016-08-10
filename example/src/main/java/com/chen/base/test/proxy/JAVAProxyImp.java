package com.chen.base.test.proxy;

public class JAVAProxyImp implements IJAVAProxyA {

	@Override
	public String sayHello() {
		System.out.println("I'm real obj!!");
		return null;
	}

}

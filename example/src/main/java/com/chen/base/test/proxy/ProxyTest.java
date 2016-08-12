package com.chen.base.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;

/**
 * Java的动态代理
 * <p>
 * <li>必须实现接口
 * <li>关键类有 Proxy和InvocationHandler，Proxy用来创建代理类， InvocationHandler用来执行代理逻辑
 * 
 * @author heyi
 *
 */
public class ProxyTest {

	public static void main(String[] args) {
		IJAVAProxyA realObj = new JAVAProxyImp();

		ProxyInvokeHandler handler = new ProxyInvokeHandler(realObj);

		IJAVAProxyA proxy = (IJAVAProxyA) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), realObj.getClass().getInterfaces(), handler);

		proxy.sayHello();
		
		
		//cglibtest
		 Enhancer enhancer = new Enhancer();
	     enhancer.setSuperclass(JAVAProxyImp.class);
	     enhancer.setCallback( new CglibJAVAProxy() );
	     JAVAProxyImp my = (JAVAProxyImp)enhancer.create();
	     my.sayHello();
	}

}

class ProxyInvokeHandler implements InvocationHandler {

	IJAVAProxyA realObj;

	public ProxyInvokeHandler(IJAVAProxyA realObj) {
		this.realObj = realObj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		method.invoke(realObj, args);
		return null;
	}

}

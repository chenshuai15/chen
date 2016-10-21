package com.chen.test;

import java.util.Random;

public class Test {
	
	@org.junit.Test
	public void testRandom(){
		for(int i = 0;i < 100;i ++){
			Random rm = new Random();
			System.out.println(rm.nextInt(100));
		}
	}
}

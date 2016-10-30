package com.mvc.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		unsafeAdd(new ArrayList<String>());
		
	}
	
	static void unsafeAdd(List<?> list ){
		list.add(null);
//		list.add("aaa"); 报错，无限制的通配符类型 不允许add除null以外的任何元素
		
		List a = new ArrayList();
		a.add(Integer.valueOf(1));
		a.add("sss");
		
		//报错
//		if(a instanceof List<String>)
//			List<?> b = a;
//		}
		
		if(a instanceof List<?>){
			List<?> b = a;
		}
	}

}

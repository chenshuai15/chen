package com.mvc.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		unsafeAdd(new ArrayList<String>());
		
	}
	
	static void unsafeAdd(List<?> list ){
		list.add(null);
//		list.add("aaa"); ���������Ƶ�ͨ������� ������add��null������κ�Ԫ��
		
		List a = new ArrayList();
		a.add(Integer.valueOf(1));
		a.add("sss");
		
		//����
//		if(a instanceof List<String>)
//			List<?> b = a;
//		}
		
		if(a instanceof List<?>){
			List<?> b = a;
		}
	}

}

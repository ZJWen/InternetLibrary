package com.itxc;

import com.itxc.model.Products;

public class Test {

	public static void main(String[] args) throws Exception {
		String className="com.itxc.model.Products";
		//所有类 加载到内存中 变成该类一个class对象
		Class<?> clz = Class.forName(className);
		Object newInstance = clz.newInstance();//new 
		System.out.println(newInstance);
		
		//Products p  = new Products();
	}

}

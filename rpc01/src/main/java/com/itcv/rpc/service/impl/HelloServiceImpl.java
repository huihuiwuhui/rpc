package com.itcv.rpc.service.impl;

import com.itcv.rpc.service.HelloService;

public class HelloServiceImpl implements HelloService {

	public String sayHello(String name) {
		return "hello " + name;
	}

}

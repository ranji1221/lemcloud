package org.ranji.lemon.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;

//-- 注册为Dubbo服务
@Service(version="1.0.0")
public class HelloService implements IHelloService{
	
	//-- 测试业务方法
	public String getMessage(){
		return "hello dubbo";
	}
}
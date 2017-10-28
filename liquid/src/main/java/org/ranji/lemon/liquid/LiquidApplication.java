package org.ranji.lemon.liquid;

import java.util.concurrent.TimeUnit;

import org.ranji.lemon.core.CoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

public class LiquidApplication extends CoreApplication{
	public static void main(String[] args) {
		SpringApplication.run(LiquidApplication.class, args);  
	}
	
	//-- 自己的应用的服务器设置
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.setPort(8081);
	    factory.setContextPath("/liquid");
	    factory.setSessionTimeout(60, TimeUnit.MINUTES);
	    return factory;
	}
}

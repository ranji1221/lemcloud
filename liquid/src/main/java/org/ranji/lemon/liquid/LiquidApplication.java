package org.ranji.lemon.liquid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.ranji.lemon.core.CoreApplication;
import org.ranji.lemon.liquid.filter.PagerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
	//-- 分页过滤
	 @Bean
    public FilterRegistrationBean  filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        PagerFilter pagerFilter = new PagerFilter();
        registrationBean.setFilter(pagerFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}

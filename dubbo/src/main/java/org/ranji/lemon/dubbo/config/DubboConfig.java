package org.ranji.lemon.dubbo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

//@Configuration
//@PropertySource("classpath:config/dubbo.properties")
public class DubboConfig {
	
	@Value("${dubbo.application.name}")
	private String applicationName;
	
	@Value("${dubbo.registry.protocol}")
	private String protocol;
	
	@Value("${dubbo.registry.address}")
	private String registryAddress;
	
	@Value("${dubbo.protocol.name}")
	private String protocolName;
	
	@Value("${dubbo.protocol.port}")
	private int protocolPort;
	
	@Value("${dubbo.annotation.package}")
	private String packageName;
	
	
	@Bean //-- 扫描服务提供包
    public AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(packageName);
        return annotationBean;
    }
	
    @Bean //-- 注入dubbo应用上下文
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }
    
    /**
     * 注入dubbo注册中心配置,基于zookeeper
     * 
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(protocol);
        registry.setAddress(registryAddress);
        return registry;
    }

    /**
     * 默认基于dubbo协议提供服务
     * 
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        protocolConfig.setThreads(200);
        System.out.println("默认protocolConfig：" + protocolConfig.hashCode());
        return protocolConfig;
    }
    
    /**
     * dubbo服务提供
     * 
     * @param applicationConfig
     * @param registryConfig
     * @param protocolConfig
     * @return
     */
    @Bean(name="defaultProvider")
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setApplication(applicationConfig());
        providerConfig.setRegistry(registryConfig());
        providerConfig.setProtocol(protocolConfig());
        return providerConfig;
    }

}

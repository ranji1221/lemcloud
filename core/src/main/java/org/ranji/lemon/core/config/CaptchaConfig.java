package org.ranji.lemon.core.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class CaptchaConfig {
	
	@Autowired
	private Environment env;
	
	@Bean(name="captchaProducer")
	public DefaultKaptcha getKaptchaBean(){
		DefaultKaptcha defaultKaptcha=new DefaultKaptcha(); 
		Properties properties=new Properties(); 
		properties.setProperty("kaptcha.border", env.getProperty("kaptcha.border").equals("true")? "yes":"no");
		properties.setProperty("kaptcha.border.color", env.getProperty("kaptcha.borderColor")); 
		properties.setProperty("kaptcha.textproducer.font.color", env.getProperty("kaptcha.textproducerFontColor")); 
		properties.setProperty("kaptcha.image.width", env.getProperty("kaptcha.imageWidth")); 
		properties.setProperty("kaptcha.image.height", env.getProperty("kaptcha.imageHeight")); 
		properties.setProperty("kaptcha.session.key", env.getProperty("kaptcha.sessionKey")); 
		properties.setProperty("kaptcha.textproducer.char.length", env.getProperty("kaptcha.textproducerCharLength"));
		properties.setProperty("kaptcha.textproducer.font.names", env.getProperty("kaptcha.textproducerFontNames"));   
		Config config=new Config(properties); 
		defaultKaptcha.setConfig(config); 
		return defaultKaptcha; 

	}
}

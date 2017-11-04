package org.ranji.lemon.jersey.jms;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("producer")
public class Producer {
	
	 @Autowired
	 private AmqpTemplate rabbitTemplate;
	
	public void sendMessage(){
		String context = "hello " + new Date();
        rabbitTemplate.convertAndSend("hello", context);
	}
}

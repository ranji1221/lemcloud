package org.ranji.lemon.jersey.jms;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello") 
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
   @RabbitHandler
   public void receiveQueue(String text) {  
       System.out.println("Consumer收到的报文为:"+text);  
   }  
}

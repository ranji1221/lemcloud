package org.ranji.lemon.jersey.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class LemSessionListener extends SessionListenerAdapter{
	@Override  //-- 会话创建时触发 
    public void onStart(Session session) { 
        System.out.println("会话创建：" + session.getId());  
    }  
	
	@Override  //-- 会话过期时触发  
    public void onExpiration(Session session) {
        System.out.println("会话过期：" + session.getId());  
    }  
	
	@Override  //-- 退出会话过期时触发  
    public void onStop(Session session) {
        System.out.println("会话停止：" + session.getId());  
    }    
}

package org.ranji.lemon.jersey.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.ranji.lemon.core.cache.RedisCacheManager;
import org.ranji.lemon.core.persist.impl.RedisSessionDao;
import org.ranji.lemon.jersey.security.LemSessionListener;
import org.ranji.lemon.jersey.security.SystemRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	
	@Bean("redisSessionDao")
	public RedisSessionDao redisSessionDao(){
		return new RedisSessionDao();
	}
	
	@Bean("redisCacheManager")
	public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }
	
	@Bean(name = "sessionIdCookie")
	public SimpleCookie getSessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie("lemsid");
		cookie.setHttpOnly(true);	//-- 安全的考虑
		cookie.setMaxAge(-1);		//-- 仅存在与浏览器进程，浏览器关闭则失效
		return cookie;
	}
	
	@Bean("sessionManager")
    public SessionManager sessionManager(@Qualifier("sessionIdCookie") SimpleCookie simpleCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //-- 1.设置监听器
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new LemSessionListener());
        sessionManager.setSessionListeners(listeners);
        
        //-- 2.设置URL回写为false
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        
        //-- 3.设置Cookie
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        
        //-- 4.默认是30分钟，其实不设置也默认三十分钟
        //-- 这里给设置仅是为了说明如何设置session的过时时间，单位是毫秒，可以根据需要设置long值
        sessionManager.setGlobalSessionTimeout(DefaultWebSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT);
        
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setSessionDAO(redisSessionDao());
        return sessionManager;
    }
	
	//-- 配置密码比较器
	@Bean(name="credentialsMatcher")
	public CredentialsMatcher credentialsMatcher(){
		return new PasswordMatcher();
	}
		
	//-- 配置自定义权限认证授权器
	@Bean(name="systemRealm")
	public SystemRealm systemRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher){
		SystemRealm systemRealm = new SystemRealm();
		systemRealm.setCredentialsMatcher(credentialsMatcher);
		//systemRealm.setCacheManager(redisCacheManager);
		return systemRealm;
	}
	
	//-- 配置核心安全事务管理器
	@Bean(name="securityManager")
	public SecurityManager securitManager(@Qualifier("systemRealm") SystemRealm systemRealm, @Qualifier("sessionManager") SessionManager sessionManager){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setSessionManager(sessionManager);
		//manager.setCacheManager(redisCacheManager);
		manager.setRealm(systemRealm);
		return manager;
	}
	
	@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/home");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/common/**", "anon"); 
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/druid/**","anon");
        filterChainDefinitionMap.put("/captcha","anon");
        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
	
	
	@Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}

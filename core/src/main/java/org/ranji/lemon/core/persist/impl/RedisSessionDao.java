package org.ranji.lemon.core.persist.impl;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO {
	private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
	
	//-- session 在 redis过期时间为30分钟30*60
	private static int expireTime = 1800;
	private static String prefix = "lemon-shiro-session:";
	
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	//-- 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.debug("创建session:{}", session.getId());
        redisTemplate.opsForValue().set(prefix + sessionId.toString(), session);
        return sessionId;
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId) {  
    	logger.debug("获取session:{}", sessionId);
        Session session = (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
        return session;
    }
    
    
    //-- 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        logger.debug("更新session:{}", session.getId());
        String key = prefix + session.getId().toString();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }
    
    //-- 删除session
    @Override
    protected void doDelete(Session session) {
        logger.debug("删除session:{}", session.getId());
        super.doDelete(session);
        redisTemplate.delete(prefix + session.getId().toString());
    }
}

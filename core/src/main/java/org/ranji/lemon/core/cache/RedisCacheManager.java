package org.ranji.lemon.core.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 实现shiro的CacheManager
 * @author RanJi
 *
 */
public class RedisCacheManager implements CacheManager{
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new ShiroCache<K, V>(name, (RedisTemplate<K, V>) redisTemplate);
	}
	
	public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}

package com.wondersgroup.demo.util.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis缓存抽象类，实现了Redis的几个基本操作
 * @param <K>
 * @param <V>
 */
public class RedisUtil<K, V> {

	@Resource
	protected RedisTemplate<K, V> redisTemplate;

	public RedisTemplate<K, V> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void set(final K key, final V value, final long expiredTime) {
		BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);
		if (expiredTime <= 0) {
			valueOper.set(value);
		} else {
			valueOper.set(value, expiredTime, TimeUnit.SECONDS);
		}
	}
	
	public Set<K> keys(K pattern){
		Set<K> keys = redisTemplate.keys(pattern);
		return keys;
	}
	
	public void set(final K key, final V value) {
		this.set(key, value, -1);
	}

	public V get(final K key) {
		BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);
		return valueOper.get();
	}

	public void del(K key) {
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
	}

	public boolean hasKey(K key) {
		return redisTemplate.hasKey(key);
	}

	public Boolean check(K key, V value) {
		Boolean flag = false;
		if (redisTemplate.hasKey(key)) {
			if (value.equals(get(key))) {
				flag = true;
			}
		}
		return flag;
	}
}
package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
	@Autowired
	RedisConfig redisConfig;
	
	@Bean  //将JedisPool加载到springboot容器中
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig poolconfig = new JedisPoolConfig();
		poolconfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		poolconfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		poolconfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
		JedisPool jp = new JedisPool(poolconfig,redisConfig.getHost(),redisConfig.getPort(),
				redisConfig.getTimeout()*1000,redisConfig.getPassword(),0);
		return jp;
	}

}

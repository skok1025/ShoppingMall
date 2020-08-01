package com.cafe24.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Controller
@RequestMapping("/redis")
public class RedisController {

	@ResponseBody
	@GetMapping("/get")
	public String redisGet(String key) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		JedisPool pool = new JedisPool(jedisPoolConfig, "192.168.123.105", 6379, 3000, "redis");
		
		// Jedis풀 생성(JedisPoolConfig, host, port, timeout, password)
		Jedis jedis = pool.getResource();// thread, db pool처럼 필요할 때마다 getResource()로 받아서 쓰고 다 쓰면 close로 닫아야 한다.
		
		String value = jedis.get(key);
		jedis.close();
		return value;

	}
}

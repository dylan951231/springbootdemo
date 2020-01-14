package com.xu.springbootdemo.controller;

import com.xu.springbootdemo.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTest {

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/redis/{key}/{value}")
    public void test(@PathVariable String key,@PathVariable String value){
      redisUtils.set(key,value,30);
        try {
            System.out.println( redisUtils.getExpire(key));
            Thread.sleep(5000);
            System.out.println(redisUtils.get(key));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

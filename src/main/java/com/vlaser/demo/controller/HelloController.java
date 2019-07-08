package com.vlaser.demo.controller;

import com.vlaser.demo.domain.Info;
import com.vlaser.demo.service.InfoService;
import com.vlaser.demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class HelloController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InfoService infoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/")
    public String index() {
        Info info = new Info();
        info.setIp(Util.getIpAddr(request));
        info.setTime(new Date());
        infoService.add(info);
        stringRedisTemplate.opsForValue().set(Util.dateFormat(info.getTime()), info.getIp());
        return "Hello Spring Boot 2.0! version 3" + "    Time: " + Util.dateFormat(info.getTime()) + "    IP: " + info.getIp();
    }
}

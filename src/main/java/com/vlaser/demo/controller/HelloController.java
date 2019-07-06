package com.vlaser.demo.controller;

import com.vlaser.demo.domain.Info;
import com.vlaser.demo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class HelloController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InfoService infoService;

    @RequestMapping("/")
    public String index() {
        Info info = new Info();
        info.setIp(Util.getIpAddr(request));
        info.setTime(new Date());
        infoService.add(info);
        return "Hello Spring Boot 2.0!" + "Time: " + info.getTime() + "IP: " + info.getIp();
    }
}

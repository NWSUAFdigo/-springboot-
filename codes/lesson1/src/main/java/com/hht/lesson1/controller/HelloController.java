package com.hht.lesson1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "hello world";
    }

    /**
     * 需要添加tomcat-embed-jasper和jstl两个包, 同时使用maven的spring-boot:run指令运行程序才能访问jsp资源
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/rest")
    @ResponseBody
    public String rest() {
        return "<html><body>hello</body></html>";
    }
}

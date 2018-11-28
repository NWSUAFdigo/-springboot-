package com.hht.lesson3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlRestController {
    // 使用GetMapping和PostMapper来替代RequestMapping
    @GetMapping("/html/get")
    @PostMapping("/html/post")
    public String html() {
        return "<html><body><h3>hello world</h3></body></html>";
    }
}

package com.hht.lesson3.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

// RestController注解内部包括了Controller和ResponseBody两个注解
@RestController
public class _1_HtmlRestController {

    // 使用GetMapping和PostMapper来替代RequestMapping
    // @GetMapping("/html/get")
    @PostMapping("/html/post")
    public String html() {
        return "<html><body><h3>hello world</h3></body></html>";
    }

    // REST路径变量接收
    @GetMapping("/html/get/{message}")
    public String htmlPathVariabel(@PathVariable String message) {
        return message;
    }

    // REST路径变量接收(多个路径变量)
    @GetMapping("/html/get/{sender}/{message}")
    public String htmlPathVariabel(@PathVariable String sender, @PathVariable String message) {
        return sender + ": " + message;
    }

    // 请求参数校验是否必传, 默认必传
    @GetMapping("/html/get/paramRequired")
    public String htmlParamRequired(@RequestParam(name = "param", required = true) String param) {
        return "param: " + param;
    }

    // 请求参数的默认值, 只有在是否必传为false时才有效
    @GetMapping("/html/get/paramDefaultValue")
    public String htmlParamDefaultValue(@RequestParam(name = "param", required = false, defaultValue = "默认值") String param) {
        return "param: " + param;
    }

    // 通过HttpServletRequest获取参数
    @GetMapping("/html/get/paramInServletRequest")
    public String htmlParamInServletRequest(HttpServletRequest request) {
        String param = request.getParameter("param");
        return "param: " + param;
    }

    // 获取请求头相关的数据
    @GetMapping("/html/get/header")
    public String htmlGetHeader(@RequestHeader("User-Agent") String userAgent) {
        return userAgent;
    }

    // 向响应中添加内容
    @GetMapping("/html/get/putResponse")
    public ResponseEntity<String> htmlPutResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("MyHeadKey", Collections.singletonList("MyHeadValue"));

        return new ResponseEntity<>("<html><body>HTML ResponseEntity </body></html>", headers, HttpStatus.OK);
        /* ResponseEntity中包含了响应相关的元数据, 如响应头和响应状态码
         * 参考: ResponseEntityController
         */
    }

    // 通过HttpServletRespouse中添加内容
    @GetMapping("/html/get/putResponse2")
    public String htmlPutResponse(HttpServletResponse response) {
        response.addHeader("MyHeader", "MyHeaderValue");
        return "ok";
    }
}

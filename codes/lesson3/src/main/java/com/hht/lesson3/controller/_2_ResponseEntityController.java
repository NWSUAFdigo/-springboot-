package com.hht.lesson3.controller;

import com.hht.lesson3.exception.UserNotFoundException;
import com.hht.lesson3.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class _2_ResponseEntityController {

    /*
    ResponseEntity
        该类的多参构造器中包含三个参数
        1. body: 用于设置响应体中的数据
        2. headers: 用于设置响应头中的数据
        3. status: 用于设置返回的状态码

     该类支持泛型, 泛型类型即为响应体的数据类型
     headders类型为MultiValueMap, 其中HttpHeaders已经继承该类, 可以作为headers的数据类型
     status为状态码, 可以传入对应的数字或使用HttpStatus
     */

    // 向响应中添加内容
    @GetMapping(value = "/responseEntity/putHeader")
    public ResponseEntity<User> putHeader() {
        HttpHeaders headers = new HttpHeaders();
        // 添加响应内容
        headers.add("MyHeader", "MyHeaderValue");

        // 添加到响应实体
        return new ResponseEntity<>(new User("wudi", 18), headers, HttpStatus.OK);
    }

    /*
    示例:
        使用REST方式编写两个接口, 存入用户和读取用户.
        读取用户时, 如果用户存在返回200, 如果用户不存在返回404
     */

    private Map<String, User> userMap = new HashMap<>();

    @PostMapping("/responseEntity/user")
    public User saveUser(@RequestParam("name") String name, @RequestParam("age") int age) {
        User user = new User(name, age);
        userMap.put(name, user);
        return user;
    }

    @GetMapping("/responseEntity/user/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name) {
        User user = userMap.get(name);

        return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    // 查询用户方式2: 如果用户不存在, 则直接抛出异常
    @GetMapping("/responseEntity/user2/{name}")
    public ResponseEntity<User> getUser2(@PathVariable("name") String name) {
        User user = userMap.get(name);

        if (user == null) {
            throw new UserNotFoundException(name);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException e) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", HttpStatus.NOT_FOUND.value());
        ret.put("msg", "未找到名称为[" + e.getUserName() + "]的用户信息");

        return new ResponseEntity<>(ret, HttpStatus.NOT_FOUND);
    }
}

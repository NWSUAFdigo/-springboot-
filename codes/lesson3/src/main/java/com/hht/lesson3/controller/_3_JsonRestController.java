package com.hht.lesson3.controller;

import com.hht.lesson3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class _3_JsonRestController {

    private User user;

    // 使用AutoWired调用该方法, 初始化user的值
    @Autowired
    private void setUser() {
        user = new User("wudi", 18);
    }

    // 获取user数据
    @GetMapping("/json/user")
    public User user() {
        return user;
    }

    // 向user中设置名称
    @PutMapping("/json/user/name/{name}")
    public User setName(@PathVariable("name") String name) {
        user.setName(name);
        return user;
    }

    // 向user中设置年龄
    @PutMapping("/json/user/age/{age}")
    public User setAge(@PathVariable("age") int age) {
        user.setAge(age);
        return user;
    }

    // 返回HATEOAS的REST数据
    // 需要让相应的bean继承ResourceSupport
    @GetMapping(path = "/json/user2", produces = MediaType.APPLICATION_JSON_VALUE)
    public User user2() {
        // 添加link
        _3_JsonRestController controller = ControllerLinkBuilder.methodOn(_3_JsonRestController.class);

        Link link = ControllerLinkBuilder.linkTo(controller.setName(user.getName())).withSelfRel();
        user.add(link);

        return user;
    }
}

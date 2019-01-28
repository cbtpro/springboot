package com.lieqihezi.www.controller;

import com.lieqihezi.www.domain.User;
import com.lieqihezi.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userServices;

    @PostMapping(value = "/addUser")
    @ResponseBody
    public Map<?, ?> addUser(@RequestParam String name, @RequestParam Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        Map<?, ?> map = userServices.saveUser(user);
        return map;
    }

}

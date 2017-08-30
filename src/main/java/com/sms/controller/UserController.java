package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.domain.User;
import com.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by nanzhou on 2017/7/27.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findById")
    @ResponseBody
    public String getUserById(Long id) {

        User user = userService.getById(id);

        return JSON.toJSONString(user);

    }

    @RequestMapping("/toUserPage")
    public String toUserPage(Model model){

        List<User> list = userService.getList();
        model.addAttribute("list",list);
        return "/user";
    }

}

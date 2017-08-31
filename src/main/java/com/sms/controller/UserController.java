package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.domain.User;
import com.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import java.util.List;

/**
 * Created by nanzhou on 2017/7/27.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(Long id) {

        User user = userService.getById(id);

        return JSON.toJSONString(user);

    }

    @RequestMapping("/toUserPage")
    public String toUserPage(Model model) {

        List<User> list = userService.getList();
        model.addAttribute("list", list);
        return "/user";
    }

    @RequestMapping( value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String name, String password) throws Exception {

        MessageDigest digest;
        digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        password = Hex.encodeHexString(hash);
        User user = userService.login(name, password);
        boolean flag = null != user;

        return JSON.toJSONString(flag);
    }

    @RequestMapping("/toLoginPage")
    public String toLoginPage() {


        return "/loginPage";
    }
}

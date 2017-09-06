package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.domain.User;
import com.sms.service.RedisService;
import com.sms.service.UserService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by nanzhou on 2017/7/27.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisService redisService;

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

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(String name, String password, HttpSession session) throws Exception {


        MessageDigest digest;
        digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        password = Hex.encodeHexString(hash);
        User user = userService.login(name, password);
        boolean flag = null != user;

        if (null != user) {
            session.setAttribute("userSession", user);
            //redisService.add(name, 30 * 60, JSON.toJSONString(user));
        }
        return JSON.toJSONString(flag);
    }

    @RequestMapping("/toLoginPage")
    public String toLoginPage() {


        return "/loginPage";
    }

    @RequestMapping("/toSuccessPage")
    public String toSuccessPage(HttpSession session) {


        //String name =  session.getAttribute("userNameSession").toString();
        //String userJson = redisService.get(name);

        return "/success";
    }

}

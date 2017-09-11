package com.sms.contorller;

import com.alibaba.fastjson.JSON;
import com.sms.domain.User;
import com.sms.service.RedisService;
import com.sms.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.List;
import java.util.ListIterator;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by nanzhou on 2017/9/4.
 */
public class RedisTest extends BaseControllerTest {

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    protected WebApplicationContext wac;


    @Autowired
    private UserService userService;


    @Autowired
    private RedisService redisService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }


    @Test
    public void getKeys() {

        String content = redisService.get("nanzhou");
        System.out.println(content);
    }


    @Test
    public void setObj() {

        User user = userService.getById(1L);

        boolean flag = redisService.add("nanzhou", 30 * 60, JSON.toJSONString(user));

        System.out.println(flag);
    }
}

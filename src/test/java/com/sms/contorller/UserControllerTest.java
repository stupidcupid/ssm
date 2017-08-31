package com.sms.contorller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by nanzhou on 2017/8/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring-mvc-test.xml", "classpath:/spring-mybatis-test.xml"})
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserControllerTest {


    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    protected WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void findList() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/user/findById")
                .param("id", "1")
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String str = mvcResult.getResponse().getContentAsString();
        System.out.print(str);
    }

    @Test
    public void login() throws Exception{

        MvcResult mvcResult = mockMvc.perform(get("/user/login")
                .param("name", "starzou")
                .param("password","123456")
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String str = mvcResult.getResponse().getContentAsString();
        System.out.print(str);
    }
}

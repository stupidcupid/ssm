package com.sms.contorller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sms.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by nanzhou on 2017/8/31.
 */
public class UserControllerTest extends BaseControllerTest {


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
    public void login() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/user/login")
                .param("name", "nanzhou")
                .param("password", "123456")
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String str = mvcResult.getResponse().getContentAsString();
        System.out.print(str);
    }


    @Test
    public void convert() {


        String jsonUser = "{'createTime':'1405619948000','id':1,'password':'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','username':'nanzhou'}";
        JSONObject json = JSON.parseObject(jsonUser);

        User user = JSON.parseObject(jsonUser,User.class);

        System.out.println(user.toString());
       /* String createTime = json.getString("createTime");
        String id = json.getString("id");
        String password = json.getString("password");
        String username = json.getString("username");
        System.err.println(createTime + " " + id + " " + password + " " + username);*/
    }
}

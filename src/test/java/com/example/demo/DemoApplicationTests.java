package com.example.demo;

import com.example.demo.web.HelloController;
import com.example.demo.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.internal.creation.bytebuddy.MockAccess;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private MockMvc mvc;
    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new HelloController(),new UserController()).build();

    }

    @Test
    public void testUserController() throws Exception {
        //测试UserController
        RequestBuilder request=null;
        //get获取user列表，为空；
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
        //post 提交一个user；
        request = post("/users/")
                .param("id","1")
                .param("name","测试")
                .param("age","19");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
        //获取user列表，有才插入的数据
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试\",\"age\":19}]")));
        //put修改ID为1的user
        request = put("/users/1").param("name","测试大师").param("age","10");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
        //get获取ID为1的user
        request = get("/users/1");
        mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试大师\",\"age\":10}")));
        //deleteID为1的用户
        request = delete("/users/1");
        mvc.perform(request).andExpect(content().string(equalTo("success")));
        //getuser列表，user列表为空
        request = get("/users/");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void contextLoads() {
    }

}

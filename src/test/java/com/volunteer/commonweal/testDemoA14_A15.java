package com.volunteer.commonweal;


import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.ModifyPasswordData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.PersonalityData;
import com.volunteer.commonweal.repositories.homePageRepositories.UserRepository;
import com.volunteer.commonweal.services.homePageServices.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonwealApplication.class)
@SpringBootConfiguration
public class testDemoA14_A15 {
    private MockMvc mockMvc;
    private static MvcResult mvcResult;
    private static MockHttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private HashMap<String, Object> sessionAttr;
    private HttpHeaders httpHeaders = new HttpHeaders();

    @BeforeClass
    public static void Datainit(){
    }


    //superuser test15
    private  String passport1 = "adminuser1";//username or phone or email
    private  String password1 = "adminuser1";
    private LoginData uData1 = new LoginData();
    @Test
    public void logintestA15_1() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData1.passport = this.passport1;
        uData1.password = this.password1;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登录");
        System.out.println("passport:" + uData1.passport);
        System.out.println("password:" + uData1.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(true)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");

        System.out.println("getUserInfo验证接口测试");
        System.out.println("用户信息获取");
        try {
            mockMvc.perform(get("/user/userInfo")
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getUserInfo验证接口测试完毕");

        PersonalityData PD = new PersonalityData();
        PD.qq="401569798";
        System.out.println("修改已登录用户QQ");
        try {
            mockMvc.perform(post("/user/userInfo/Modify")
                    .contentType(contentType).content(json(PD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("修改用户QQ完成");
    }

    //user test15
    private  String passport2 = "icarus";//username or phone or email
    private  String password2 = "123456789";
    private LoginData uData2 = new LoginData();
    @Test
    public void logintestA15_2() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData2.passport = this.passport2;
        uData2.password = this.password2;
        System.out.println("userSignIn验证接口测试");
        System.out.println("用户登录");
        System.out.println("passport:" + uData2.passport);
        System.out.println("password:" + uData2.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(false)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");

        System.out.println("getUserInfo验证接口测试");
        System.out.println("用户信息获取");
        try {
            mockMvc.perform(get("/user/userInfo")
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getUserInfo验证接口测试完毕");

        PersonalityData PD = new PersonalityData();
        PD.qq="401569798";
        System.out.println("修改已登录用户QQ");
        try {
            mockMvc.perform(post("/user/userInfo/Modify")
                    .contentType(contentType).content(json(PD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("修改用户QQ完成");
    }

    //superuser test15

    @Test
    public void logintestA14_1() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData1.passport = this.passport1;
        uData1.password = this.password1;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登录");
        System.out.println("passport:" + uData1.passport);
        System.out.println("password:" + uData1.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(true)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");

        System.out.println("getUserInfo验证接口测试");
        System.out.println("用户信息获取");
        try {
            mockMvc.perform(get("/user/userInfo")
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getUserInfo验证接口测试完毕");

        PersonalityData PD = new PersonalityData();
        PD.description="测试的时候没什么好简介";
        System.out.println("修改已登录用户简介");
        try {
            mockMvc.perform(post("/user/userInfo/Modify")
                    .contentType(contentType).content(json(PD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("修改用户简介完成");
    }

    //user test14
    @Test
    public void logintestA14_2() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData2.passport = this.passport2;
        uData2.password = this.password2;
        System.out.println("userSignIn验证接口测试");
        System.out.println("用户登录");
        System.out.println("passport:" + uData2.passport);
        System.out.println("password:" + uData2.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(false)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");

        System.out.println("getUserInfo验证接口测试");
        System.out.println("用户信息获取");
        try {
            mockMvc.perform(get("/user/userInfo")
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getUserInfo验证接口测试完毕");

        PersonalityData PD = new PersonalityData();
        PD.description="测试的时候没什么好简介";
        System.out.println("修改已登录用户简介");
        try {
            mockMvc.perform(post("/user/userInfo/Modify")
                    .contentType(contentType).content(json(PD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("修改用户简介完成");
    }


    @After
    //用户登出
    public void userSignOut(){
        System.out.println("userSignOut验证接口测试");
        try {
            mockMvc.perform(put("/user/signOut")
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("userSignOut验证接口测试完毕");
    }

    //Json化
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

package com.volunteer.commonweal;


import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
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
public class testDemoA12 {
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
    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    }
    @BeforeClass
    public static void Datainit(){
    }


       //用户登陆测试A12_user
    private  String passportuser = "1114048173@qq.com";//username or phone or email
    private  String passworduser = "lx1114048173";
    private LoginData uDatauser = new LoginData();

    @Test
    public void logintestA12_user() throws Exception{
        uDatauser.passport = this.passportuser;
        uDatauser.password = this.passworduser;
        System.out.println("userSignIn验证接口测试");
        System.out.println("用户登录");
        System.out.println("passport:" + uDatauser.passport);
        System.out.println("password:" + uDatauser.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uDatauser)))
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
                    .andExpect(jsonPath("$.phone",is("13080922268")))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getUserInfo验证接口测试完毕");
    }


    //用户登陆测试A12_admin
    private  String passportadmin = "adminuser1";//username or phone or email
    private  String passwordadmin = "adminuser1";
    private LoginData uDataadmin = new LoginData();

    @Test
    public void logintestA12_admin() throws Exception{
        uDataadmin.passport = this.passportadmin;
        uDataadmin.password = this.passwordadmin;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登陆");
        System.out.println("passport:" + uDataadmin.passport);
        System.out.println("password:" + uDataadmin.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uDataadmin)))
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


//    //执行后注销用户
//    @After
//    public void removeUser() {
//    }

    //Json化
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

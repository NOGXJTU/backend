

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
public class testDemoA9_11 {
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


       //A9_user_username
    private  String passport9user = "PHYLLIS";//username or phone or email
    private  String password9user= "lx1114048173";
    private LoginData uData9user = new LoginData();

    @Test
    public void logintestA9_1() throws Exception{
        uData9user.passport = this.passport9user;
        uData9user.password = this.password9user;
        System.out.println("userSignIn验证接口测试");
        System.out.println("用户登录");
        System.out.println("passport:" + uData9user.passport);
        System.out.println("password:" + uData9user.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData9user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(false)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }

    //A9_admin_username
    private  String passport9admin = "adminuser1";//username or phone or email
    private  String password9admin= "adminuser1";
    private LoginData uData9admin = new LoginData();
    @Test
    public void logintestA9_2() throws Exception{
        uData9admin.passport = this.passport9admin;
        uData9admin.password = this.password9admin;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登录");
        System.out.println("passport:" + uData9admin.passport);
        System.out.println("password:" + uData9admin.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData9admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(true)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }


    //A10_user_email
    private  String passport10user = "1114048173@qq.com";//username or phone or email
    private  String password10user = "lx1114048173";
    private LoginData uData10user = new LoginData();

    @Test
    public void logintestA10_1() throws Exception{
        uData10user.passport = this.passport10user;
        uData10user.password = this.password10user;
        System.out.println("userSignIn验证接口测试");
        System.out.println("用户登录");
        System.out.println("passport:" + uData10user.passport);
        System.out.println("password:" + uData10user.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData10user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(false)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }

    //A10_admin_email
    private  String passport10admin = "401569798@qq.com";//username or phone or email
    private  String password10admin = "adminuser1";
    private LoginData uData10admin = new LoginData();

    @Test
    public void logintestA10_2() throws Exception{
        uData10admin.passport = this.passport10admin;
        uData10admin.password = this.password10admin;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登录");
        System.out.println("passport:" + uData10admin.passport);
        System.out.println("password:" + uData10admin.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData10admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(true)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }


    //A11_user_phone
    private  String passport11user = "13080922268";//username or phone or email
    private  String password11user = "lx1114048173";
    private LoginData uData11user = new LoginData();

    @Test
    public void logintestA11_1() throws Exception{
        uData11user.passport = this.passport11user;
        uData11user.password = this.password11user;
        System.out.println("userSignIn验证接口测试");
        System.out.println("用户登陆");
        System.out.println("passport:" + uData11user.passport);
        System.out.println("password:" + uData11user.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData11user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(false)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }

    //A11_admin_phone
    private  String passport11admin = "13572510000";//username or phone or email
    private  String password11admin = "adminuser1";
    private LoginData uData11admin = new LoginData();

    @Test
    public void logintestA11_2() throws Exception{
        uData11admin.passport = this.passport11admin;
        uData11admin.password = this.password11admin;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登陆");
        System.out.println("passport:" + uData11admin.passport);
        System.out.println("password:" + uData11admin.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData11admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(true)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }


    @Test
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


    //执行后注销用户
    @After
    public void removeUser() {
    }

    //Json化
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

package com.volunteer.commonweal;

import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.GenerateData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.ModifyData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.OnlyIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeModifyData;
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
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonwealApplication.class)
@SpringBootConfiguration
public class testDemoA66 {
    private MockMvc mockMvc;
    private static MvcResult mvcResult;
    private static MockHttpSession session;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private  String passport = "adminuser1";//username or phone or email
    private  String password = "adminuser1";
    private LoginData uData = new LoginData();
    //Admin login
    @Before
    public void  login() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData.passport = this.passport;
        uData.password = this.password;
        System.out.println("userSignIn验证接口测试");
        System.out.println("超级管理员登陆");
        System.out.println("passport:" + uData.passport);
        System.out.println("password:" + uData.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(true)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }

    //testA66_1 admin updates  college information
    @Test
    public void testA66_1() throws Exception{
        CollegeModifyData CMD = new CollegeModifyData();
        CMD.content="世界一流学府";
        CMD.id="";
        CMD.name="西安交通大学";
        CMD.type="985";
        System.out.println("updateCollege验证接口测试");
        try {
            mockMvc.perform(put("/school/school/{id}")
                    .contentType(contentType).content(json(CMD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updateCollege验证接口测试完毕");
    }

    //testA66_2 admin updates inexisted  college information
    @Test
    public void testA66_2() throws Exception{
        CollegeModifyData CMD = new CollegeModifyData();
        CMD.content="世界一流学府";
        CMD.id="";
        CMD.name="南京大学";
        CMD.type="985";
        System.out.println("updateCollege1验证接口测试");
        try {
            mockMvc.perform(put("/school/school/{id}")
                    .contentType(contentType).content(json(CMD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updateCollege1验证接口测试完毕");
    }

    //Admin logout
    @After
    public  void logout() throws  Exception{
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

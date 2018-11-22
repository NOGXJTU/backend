package com.volunteer.commonweal;

import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.GenerateData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.ModifyData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.OnlyIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeModifyData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.MajorRequestModel.MajorModifyData;
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
public class testDemoA71 {
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

    //testA71_1 admin updates  major information
    @Test
    public void testA71_1() throws Exception{
        MajorModifyData MMD = new MajorModifyData();
        MMD.content="ABCDE";
        MMD.id="";//用70的ID
        MMD.major="软件工程";
        MMD.name="图形学";
        MMD.type="985";

        System.out.println("updateMajor验证接口测试");
        try {
            mockMvc.perform(put("/subject/subject/{id}")
                    .contentType(contentType).content(json(MMD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updateMajor验证接口测试完毕");
    }

    //testA71_2 admin updates inexisted  major information
    @Test
    public void testA71_2() throws Exception{
        MajorModifyData MMD = new MajorModifyData();
        MMD.content="123456";
        MMD.id="";//用非法的ID
        MMD.major="软件工程";
        MMD.name="图形学";
        MMD.type="985";
        System.out.println("updateMajor1验证接口测试");
        try {
            mockMvc.perform(put("/subject/subject/{id}")
                    .contentType(contentType).content(json(MMD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("updateMajor1验证接口测试完毕");
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

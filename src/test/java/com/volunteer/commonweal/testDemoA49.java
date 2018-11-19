package com.volunteer.commonweal;


import com.volunteer.commonweal.models.requestModels.ActivityData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.DuplicateData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonwealApplication.class)
@SpringBootConfiguration
public class testDemoA49 {
    private MockMvc mockMvc;
    private static MvcResult mvcResult;
    private static MockHttpSession session;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private LoginData uData = new LoginData();

    @Before
    public void  login() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData.passport = "adminuser1";
        uData.password = "adminuser1";
        System.out.println("userSignIn验证接口测试");
        System.out.println("管理员登陆");
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
    //testA49_1 a duplicate activities
    @Test
    public void testA49_1() throws Exception{
        DuplicateData DD = new DuplicateData();
        DD.beginTime="20171119";
        DD.name="彭康之夜";
        System.out.println("isDuplicateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/duplicate")
                    .contentType(contentType).content(json(DD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.duplicateFlag",is(true)))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("isDuplicateActivity验证接口测试完毕");
    }

    //testA49_2 name different
    @Test
    public void testA49_2() throws Exception{
        DuplicateData DD = new DuplicateData();
        DD.beginTime = "20171119";
        DD.name = "彭康";
        System.out.println("isDuplicateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/duplicate")
                    .contentType(contentType).content(json(DD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.duplicateFlag",is(false)))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("isDuplicateActivity验证接口测试完毕");
    }

    //testA47_3 time different
    @Test
    public void testA49_3() throws Exception{
        DuplicateData DD = new DuplicateData();
        DD.beginTime = "20171120";
        DD.name = "彭康之夜";
        System.out.println("isDuplicateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/duplicate")
                    .contentType(contentType).content(json(DD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.duplicateFlag",is(false)))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("isDuplicateActivity验证接口测试完毕");
    }


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
    }

    //Json化
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
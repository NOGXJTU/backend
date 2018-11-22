package com.volunteer.commonweal;


import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.IdAndUserIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.IdAndUserListData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
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
public class testDemoA36 {
    private MockMvc mockMvc;
    private static MvcResult mvcResult;
    private static MockHttpSession session;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private  String passport = "icarus";//username or phone or email
    private  String password = "123456789";
    private LoginData uData = new LoginData();
    //leader login
    @Before
    public void  login() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData.passport = this.passport;
        uData.password = this.password;
        System.out.println("userSignIn验证接口测试");
        System.out.println("组长登陆");
        System.out.println("passport:" + uData.passport);
        System.out.println("password:" + uData.password);
        mvcResult = mockMvc.perform(post("/user/signIn")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superUser",is(false)))
                .andReturn();
        session = (MockHttpSession) mvcResult
                .getRequest().getSession();
        System.out.println("userSignIn验证接口测试完毕");
    }

    //testA36_1 team leader removes some team members from his/her activity, NOT including team leader
    @Test
    public void testA36_1() throws Exception{
        List<String> userIdGroup =  new LinkedList<String>();
        userIdGroup.add("5bed3847ccf2b952894b0083");
        userIdGroup.add("5beffe56822ed026a87e451f");
        IdAndUserListData IAULD = new IdAndUserListData();
        IAULD.activityId = "5bf024b1ccf2b97bc37e3035";
        IAULD.userIdGroup = userIdGroup;
        System.out.println("removeActivityMember1验证接口测试");
        try {
            mockMvc.perform(post("/activity/removeMember/Group")
                    .contentType(contentType).content(json(IAULD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("removeActivityMember1验证接口测试完毕");
    }

    //testA36_2 team leader removes some team members from his/her activity, including team leader
    @Test
    public void testA36_2() throws Exception{
        List<String> userIdGroup =  new LinkedList<String>();
        userIdGroup.add("5bed36d5ccf2b952894b0080");
        userIdGroup.add("5bee4a23ccf2b952894b0086");
        IdAndUserListData IAULD = new IdAndUserListData();
        IAULD.activityId = "5bf024b1ccf2b97bc37e3035";
        IAULD.userIdGroup = userIdGroup;
        System.out.println("removeActivityMember2验证接口测试");
        try {
            mockMvc.perform(post("/activity/removeMember/Group")
                    .contentType(contentType).content(json(IAULD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("removeActivityMember2验证接口测试完毕");
    }



    //Json化
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

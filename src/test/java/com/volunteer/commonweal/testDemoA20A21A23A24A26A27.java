package com.volunteer.commonweal;

import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.GenerateData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.ModifyData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.OnlyIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
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
public class testDemoA20A21A23A24A26A27 {
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

    //testA20 admin creates a new activity
    @Test
    public void testA20() throws Exception{
        GenerateData GD = new GenerateData();
        GD.type = "其他";
        GD.place = "北爱尔兰托尔斯忒联合大学";
        GD.ownerId = "5beb8f9bccf2b952894b007d";
        GD.name = "古希腊爱情与哲思";
        GD.description = "Only for test";
        GD.beginTime = "2017-7-17";
        GD.lasting = "2h";
        GD.picUrl = "stringcxvg";
        System.out.println("generateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/activity")
                    .contentType(contentType).content(json(GD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateActivity验证接口测试完毕");
    }

    //testA21_1 admin creates an exiting activity
    @Test
    public void testA21_1() throws Exception{
        GenerateData GD = new GenerateData();
        GD.type = "其他";
        GD.place = "Shanghai";
        GD.ownerId = "5beb8f9bccf2b952894b007d";
        GD.name = "Amazing China";
        GD.description = "test activities";
        GD.beginTime = "2018.6.14";
        GD.lasting = "2小时";
        GD.picUrl = "";
        System.out.println("generateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/generate")
                    .contentType(contentType).content(json(GD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateActivity验证接口测试完毕");
    }

    //testA21_2 admin creates a partly exiting activity
    @Test
    public void testA21_2() throws Exception{
        GenerateData GD = new GenerateData();
        GD.type = "其他";
        GD.place = "Beijing";//仅地点不同
        GD.ownerId = "5beb8f9bccf2b952894b007d";
        GD.name = "Amazing China";
        GD.description = "test activities";
        GD.beginTime = "2018.6.14";
        GD.lasting = "2小时";
        GD.picUrl = "";
        System.out.println("generateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/generate")
                    .contentType(contentType).content(json(GD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateActivity验证接口测试完毕");
    }

    //testA21_3 admin creates a partly exiting activity
    //Compare to testA20
    @Test
    public void testA21_3() throws Exception{
        GenerateData GD = new GenerateData();
        GD.type = "其他";
        GD.place = "北爱尔兰托尔斯忒联合大学";
        GD.ownerId = "5beb8f9bccf2b952894b007d";
        GD.name = "古希腊爱情与哲思Ⅱ";//这里不同
        GD.description = "Only for test";
        GD.beginTime = "2017-7-18";//这里不同
        GD.lasting = "2h";
        GD.picUrl = "";
        System.out.println("generateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/generate")
                    .contentType(contentType).content(json(GD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateActivity验证接口测试完毕");
    }

    //testA21_4 admin creates a partly exiting activity
    @Test
    public void testA21_4() throws Exception{
        GenerateData GD = new GenerateData();
        GD.type = "其他";
        GD.place = "Shanghai";
        GD.ownerId = "5beb8f9bccf2b952894b007d";
        GD.name = "Amazing China";
        GD.description = "test activities";
        GD.beginTime = "2018.7.17";//仅举办时间不同
        GD.lasting = "2小时";
        GD.picUrl = "";
        System.out.println("generateActivity验证接口测试");
        try {
            mockMvc.perform(post("/activity/generate")
                    .contentType(contentType).content(json(GD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateActivity验证接口测试完毕");
    }

    //testA23 admin deletes an exiting activity
    @Test
    public void testA23() throws Exception{
        System.out.println("deleteActivity验证接口测试");
        OnlyIdData OID = new OnlyIdData();
        OID.activityId = "5bf02946ccf2b97bc37e3037";
        try {
            mockMvc.perform(post("/activity/delete")
                    .contentType(contentType).content(json(OID))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("deleteActivity验证接口测试完毕");
    }

    //testA24 admin deletes a NOT exiting activity
    @Test
    public void testA24() throws Exception{
        System.out.println("deleteActivity验证接口测试");
        OnlyIdData OID = new OnlyIdData();
        OID.activityId = "77p95b49e54fc951548aa67b";
        try {
            mockMvc.perform(post("/activity/delete")
                    .contentType(contentType).content(json(OID))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("deleteActivity验证接口测试完毕");
    }

    //testA26 admin modifies an exiting activity
    @Test
    public void testA26() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "3天";
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "西安交通大学";
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_1 admin modifies a NOT exiting activity
    @Test
    public void testA27_1() throws Exception{
        System.out.println("deleteActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf0367112345678c37e3038";
        MD.beginTime = "2018.9.14";
        MD.description = "";
        MD.finished = true;
        MD.lasting = "2020.7.10";
        MD.name = "Bdmin";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "Ci'an";
        MD.type = "思想沙龙";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("deleteActivity验证接口测试完毕");
    }

    //testA27_2 admin modifies a partly exiting activity
    @Test
    public void testA27_2() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.6.14";//time
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "3天";
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "西安交通大学";
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_3 admin modifies a partly exiting activity
    @Test
    public void testA27_3() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = true;//modify here
        MD.lasting = "3天";
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "西安交通大学";
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_4 admin modifies a partly exiting activity
    @Test
    public void testA27_4() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "3天";
        MD.name = "美学初步入门Ⅱ";//modify here
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "西安交通大学";
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_5 admin modifies a partly exiting activity
    @Test
    public void testA27_5() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "2天";//modify here
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "西安交通大学";
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_6 admin modifies a partly exiting activity
    @Test
    public void testA27_6() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "3天";
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";//modify here
        MD.place = "西安交通大学";
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_7 admin modifies a partly exiting activity
    @Test
    public void testA27_7() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "3天";
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "北京大学";//modify here
        MD.type = "兴趣课";
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_8 admin modifies a partly exiting activity
    @Test
    public void testA27_8() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "2018.7.14";
        MD.description = "test activities";
        MD.finished = false;
        MD.lasting = "3天";
        MD.name = "美学初步入门";
        MD.ownerId = "5beb8f9bccf2b952894b007d";
        MD.place = "西安交通大学";
        MD.type = "文化课";//modify
        MD.picUrl = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
    }

    //testA27_9 admin modifies a NOT exiting activity
    @Test
    public void testA27_9() throws Exception{
        System.out.println("modifyActivity验证接口测试");
        ModifyData MD = new ModifyData();
        //信息为空
        MD.activityId = "5bf03671ccf2b97bc37e3038";
        MD.beginTime = "";
        MD.description = "";
        MD.finished = false;
        MD.lasting = "";
        MD.name = "";
        MD.ownerId = "";
        MD.place = "";
        MD.type = "";
        try {
            mockMvc.perform(post("/activity/modify")
                    .contentType(contentType).content(json(MD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyActivity验证接口测试完毕");
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

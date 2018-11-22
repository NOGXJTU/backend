package com.volunteer.commonweal;

import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.GenerateData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.ModifyData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.OnlyIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.KnowledgeRequestModel.KnowledgeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.MajorRequestModel.MajorGenerateData;
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
public class testDemoA75 {
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

    //testA75_1 admin creates a new Knowledge
    @Test
    public void testA75_1() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
       KGD.contributor="5bed3769ccf2b952894b0081";
       KGD.description="123456789";
       KGD.file="xasfsgfd";
       KGD.grade="十一年级";
       KGD.name="知识点1";
       KGD.subject="数学";
        System.out.println("generateKnowledge1验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge1验证接口测试完毕");
    }

    //testA75_2 admin creates a new Knowledge without contributor
    @Test
    public void testA75_2() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
        KGD.contributor=null;
        KGD.description="vsdvfb";
        KGD.file="nhdmdt";
        KGD.grade="十二年级";
        KGD.name="知识点2";
        KGD.subject="语文";
        System.out.println("generateKnowledge2验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge2验证接口测试完毕");
    }
    //testA75_3 admin creates a new Knowledge without description
    @Test
    public void testA75_3() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
        KGD.contributor="5bed3769ccf2b952894b0081";
        KGD.description=null;
        KGD.file="nhdmdt";
        KGD.grade="十二年级";
        KGD.name="知识点2";
        KGD.subject="语文";
        System.out.println("generateKnowledge3验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge3验证接口测试完毕");
    }

    //testA75_4 admin creates a new Knowledge without grade
    @Test
    public void testA75_4() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
        KGD.contributor="5bed3769ccf2b952894b0081";
        KGD.description="vsdvfb";
        KGD.file="nhdmdt";
        KGD.grade=null;
        KGD.name="知识点2";
        KGD.subject="语文";
        System.out.println("generateKnowledge4验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge4验证接口测试完毕");
    }

    //testA75_5 admin creates a new Knowledge without name
    @Test
    public void testA75_5() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
        KGD.contributor="5bed3769ccf2b952894b0081";
        KGD.description="vsdvfb";
        KGD.file="nhdmdt";
        KGD.grade="十二年级";
        KGD.name=null;
        KGD.subject="语文";
        System.out.println("generateKnowledge5验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge5验证接口测试完毕");
    }
    //testA75_6 admin creates a new Knowledge without subject
    @Test
    public void testA75_6() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
        KGD.contributor="5bed3769ccf2b952894b0081";
        KGD.description="vsdvfb";
        KGD.file="nhdmdt";
        KGD.grade="十二年级";
        KGD.name="知识点2";
        KGD.subject=null;
        System.out.println("generateKnowledge6验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge6验证接口测试完毕");
    }
    //testA75_7 admin creates a new Knowledge with illegal grade
    @Test
    public void testA75_7() throws Exception{
        KnowledgeGenerateData KGD = new KnowledgeGenerateData();
        KGD.contributor="5bed3769ccf2b952894b0081";
        KGD.description="vsdvfb";
        KGD.file="nhdmdt";
        KGD.grade="zndgn";
        KGD.name="知识点2";
        KGD.subject="语文";
        System.out.println("generateKnowledge7验证接口测试");
        try {
            mockMvc.perform(post("/point/point")
                    .contentType(contentType).content(json(KGD))
                    .session(session))
                    .andDo(print())
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("generateKnowledge7验证接口测试完毕");
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

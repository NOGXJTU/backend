//第一次发现隋霖的bug,在重复和不重复的情况下，返回值均为false
package com.volunteer.commonweal;


import com.volunteer.commonweal.models.ResponseModels.ActivityResponseModel.Duplicate;
import com.volunteer.commonweal.models.requestModels.UserData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.EmailData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.VerifyData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.*;
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
public class testDemoA2_A4 {
    private MockMvc mockMvc;
    private static MvcResult mvcResult;
    private static MockHttpSession session;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private  String email = "462060447@qq.com";
    private EmailData eData = new EmailData();
    private VerifyData vData = new VerifyData();
    private UserEmailData uData = new UserEmailData();
    private PhoneData pData = new PhoneData();
    private UsernameData unData =new UsernameData();

    //testA2 email duplicate_false
    @Test
    public void testA2_1() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData.email = this.email;
        System.out.println("duplicateemail验证接口测试");
        System.out.println(uData.email);
        mvcResult = mockMvc.perform(post("/user/duplicate/email")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFlag",is(false)))
                .andReturn();

        System.out.println("register验证接口测试完毕");

    }

    //testA2 email duplicate_true
    @Test
    public void testA2_2() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData.email = "buhuichongde@qq.com";
        System.out.println("duplicateemail验证接口测试");
        System.out.println(uData.email);
        mvcResult = mockMvc.perform(post("/user/duplicate/email")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFlag",is(true)))
                .andReturn();

        System.out.println("register验证接口测试完毕");

    }


    //testA3 phone duplicate_false
    @Test
    public void testA3_1() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        Duplicate duplicateUserData = new Duplicate();
        pData.phone= "13720533553";
        System.out.println("duplicatephone验证接口测试");
        System.out.println(pData.phone);
        mvcResult = mockMvc.perform(post("/user/duplicate/phone")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFlag",is(false)))
                .andReturn();

        System.out.println("register验证接口测试完毕");

    }

    //testA3 phone duplicate_true
    @Test
    public void testA3_2() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        Duplicate duplicateUserData = new Duplicate();
        pData.phone= "13520533553";
        System.out.println(pData.phone);
        System.out.println("duplicatephone验证接口测试");
        mvcResult = mockMvc.perform(post("/user/duplicate/phone")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFlag",is(true)))
                .andReturn();

        System.out.println("register验证接口测试完毕");

    }

    //testA4 username duplicate_fasle
    @Test
    public void testA4_1() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        unData.username = "icarus";
        System.out.println("duplicateemail验证接口测试");
        System.out.println(unData.username);
        mvcResult = mockMvc.perform(post("/user/duplicate/username")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFlag",is(false)))
                .andReturn();

        System.out.println("register验证接口测试完毕");

    }

    //testA4 username duplicate_true
    @Test
    public void testA4_2() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        unData.username = "buhuichongde";
        System.out.println("duplicateemail验证接口测试");
        System.out.println(unData.username);
        mvcResult = mockMvc.perform(post("/user/duplicate/username")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duplicateFlag",is(true)))
                .andReturn();

        System.out.println("register验证接口测试完毕");

    }

    //Json化
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

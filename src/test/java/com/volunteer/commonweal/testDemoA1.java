//此测试中先进行A1_1的测试,为邮箱验证码发送,后将1注释进行2,3进行注册测试
//        注意，每次测试须修改用户邮箱、验证码、用户名等不可重复的信息
package com.volunteer.commonweal;


import com.volunteer.commonweal.models.requestModels.UserData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.EmailData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.VerifyData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.LoginData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.ModifyPasswordData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.PersonalityData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.RegisterData;
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
public class testDemoA1 {
    private MockMvc mockMvc;
    private static MvcResult mvcResult;
    private static MockHttpSession session;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private  String email = "icarus@xjtuse.cn";
    private EmailData eData = new EmailData();
    private VerifyData vData = new VerifyData();
    private UserData uData = new UserData();

//    //testA1_1 send_token
//    @Test
//    public void testA1_1() throws Exception{
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
//        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//
//        eData.email = this.email;
//        System.out.println("sendToken验证接口测试");
//        System.out.println("email:" + eData.email);
//        mvcResult = mockMvc.perform(post("/email/sendToken")
//                .contentType(contentType).content(json(eData)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        System.out.println("sendToken验证接口测试完毕");
//
//    }

    //testA1_2 token_verify
    @Test
    public void testA1_2() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        vData.email = this.email;
        vData.token = "1BR34579";
        System.out.println("tokenVerify验证接口测试");
        mvcResult = mockMvc.perform(post("/email/tokenVerify")
                .contentType(contentType).content(json(vData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("tokenVerify验证接口测试完毕");

    }

    //testA1_3 register
    @Test
    public void testA1_3() throws Exception{
        this.mockMvc = webAppContextSetup(webApplicationContext).build();//加载上下文
        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        uData.email = this.email;
        uData.token = "1BR34579";
        uData.name= "夏燕赵赵";
        uData.password= "123456789";
        uData.phone = "18919010426";
        uData.qq = "123456798";
        uData.school = "西安交通大学";
        uData.username = "icaxyz";
        System.out.println("register验证接口测试");
        mvcResult = mockMvc.perform(post("/user/register")
                .contentType(contentType).content(json(uData)))
                .andDo(print())
                .andExpect(status().isOk())
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

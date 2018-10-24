package com.volunteer.commonweal.services.homePageServices;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.volunteer.commonweal.common.TokenGenerateUtils;
import com.volunteer.commonweal.constants.UIConst;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.exceptionModels.EmailException;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerifyTokenService {
    private SimpleDBService simpleDBService;
    private Config config;
    private TokenVertifyService tokenVertifyService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmailVerifyTokenService(SimpleDBService simpleDBService, Config config, TokenVertifyService tokenVertifyService){
        this.simpleDBService = simpleDBService;
        this.config = config;
        this.tokenVertifyService = tokenVertifyService;
    }
    public EmailVerifyTokenService(){

    }
    public int sendEmailVerifyToken(String userEmail, String content, String title) throws EmailException{
        IClientProfile profile = DefaultProfile.getProfile(UIConst.REGIONID, UIConst.ACCESSKEYID, UIConst.SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setVersion(UIConst.VERSION);// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName(UIConst.ACCOUNTNAME);
            request.setFromAlias(UIConst.ALIAS);
            request.setAddressType(1); //1表示定向发送 0是随机发送 本次只使用定向发送
            request.setTagName(UIConst.TAGNAME); //新的邮件标签请在阿里云添加邮件标签后再使用
            request.setReplyToAddress(true); //必须要是true
            request.setToAddress(userEmail);  //到达地址
            request.setSubject(title);
            request.setHtmlBody(content);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            logger.info("邮件发送成功:\t收件地址: "+userEmail+"\t邮件标题: "+title+"\t邮件内容: "+content);
            return UIConst.SEND_SUCCESS;
        } catch (ServerException e) {
            logger.error("邮件发送失败(ServerException):\t收件地址: "+userEmail+"\t邮件标题: "+title+"\t邮件内容: "+content);
            e.printStackTrace();
            return UIConst.SEND_SERVEREXCEPTION;
        }
        catch (ClientException e) {
            logger.error("邮件发送失败(ClientException):\t收件地址: "+userEmail+"\t邮件标题: "+title+"\t邮件内容: "+content);
            e.printStackTrace();
            return UIConst.SEND_CLIENTEXCEPTION;
        }

    }
    public boolean verifyToken(String email, String token) throws AuthException{
        return tokenVertifyService.verifyToken(email, token);
    }

    public String generateToken(int length){
        return TokenGenerateUtils.getRandonString(length);
    }
}

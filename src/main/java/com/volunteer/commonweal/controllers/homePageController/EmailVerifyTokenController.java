package com.volunteer.commonweal.controllers.homePageController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.UIConst;
import com.volunteer.commonweal.models.ResponseModels.EmailResponseModel.IsTokenRightData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.exceptionModels.BaseException;
import com.volunteer.commonweal.models.exceptionModels.EmailException;
import com.volunteer.commonweal.models.implementModels.homePageModels.EmailVerifyToken;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.EmailData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.ForgetData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.EmailRequestModels.VerifyData;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import com.volunteer.commonweal.services.homePageServices.EmailVerifyTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(description = "邮箱相关接口信息")
@RestController
public class EmailVerifyTokenController {
    private Config config;
    private SimpleDBService simpleDBService;
    private EmailVerifyTokenService emailVerifyTokenService;

    @Autowired
    public EmailVerifyTokenController(Config config, SimpleDBService simpleDBService,
                                      EmailVerifyTokenService emailVerifyTokenService){
        this.config = config;
        this.simpleDBService = simpleDBService;
        this.emailVerifyTokenService = emailVerifyTokenService;
    }


    @ApiOperation(value = "忘记密码流程的密码修改",notes = "传入邮箱(email),密码(password),验证码(token).成功时候返回状态200,返回成功与否信息,失败的时候返回状态和错误信息")
    @RequestMapping(value = "/user/forget/passwordModify",method = RequestMethod.POST)
    public ResponseEntity forgetUserPasswordModify(@RequestBody ForgetData userData) throws BaseException {
        String email = userData.email;
        String password = userData.newPassword;
        String token = userData.token;
        Optional<User> userFound = simpleDBService.findOneUserByEmail(email);
        if(!userFound.isPresent()){
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        if(Objects.isNull(email)||
                Objects.isNull(token)||
                Objects.isNull(password)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if (!ParamConstraintUtils.isEmailValid(email)||
                !ParamConstraintUtils.isPasswordValid(password)){
            throw new AuthException(1014, config.getExceptionsMap().get(101));
        }
        IsTokenRightData emailData = new IsTokenRightData();
        Optional<EmailVerifyToken> emailFound = simpleDBService.findEmailVerifyTokenByEmail(email);
        if(emailFound.isPresent()){
            EmailVerifyToken emailVerifyToken = emailFound.get();
            if(token.equals(emailVerifyToken.getToken())&&
                    !ParamConstraintUtils.isTimeExceed(emailVerifyToken.getTimeStamp())){
                emailVerifyToken.setTimeStamp(new Long(0));
                simpleDBService.saveEmailVerifyToken(emailVerifyToken);
                emailData.isTokenCorrect = true;
                userFound.get().setPassword(password);
                simpleDBService.saveUser(userFound.get());
                return new ResponseEntity(emailData, HttpStatus.OK);
            }else{
                emailData.isTokenCorrect = false;
                return new ResponseEntity(emailData, HttpStatus.BAD_REQUEST);
            }
        }else {
            throw new AuthException(103, config.getExceptionsMap().get(103));
        }
    }


    @ApiOperation(value = "发送邮箱验证码", notes = "传入邮箱(email),成功时返回状态200,失败时返回状态以及错误信息")
    @RequestMapping(value = "/email/sendToken", method = RequestMethod.POST)
    public ResponseEntity sendToken(@RequestBody EmailData data) throws EmailException,BaseException {
        String email = data.email;
        if(Objects.isNull(email)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isEmailValid(email)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        String token = emailVerifyTokenService.generateToken(UIConst.TOKEN_LENGTH);
        int success = emailVerifyTokenService.sendEmailVerifyToken(email, token, UIConst.EMAIL_TITLE);
        if(success == UIConst.SEND_SERVEREXCEPTION){
            throw new EmailException();
        }else if(success == UIConst.SEND_CLIENTEXCEPTION){
            throw new EmailException();
        }
        Optional<EmailVerifyToken> emailVerifyToken = simpleDBService.findEmailVerifyTokenByEmail(email);
        if(emailVerifyToken.isPresent()){
            EmailVerifyToken emailFound = emailVerifyToken.get();
            emailFound.setTimeStamp(System.currentTimeMillis() / 1000);
            emailFound.setToken(token);
            simpleDBService.saveEmailVerifyToken(emailFound);
            return new ResponseEntity(HttpStatus.OK);
        }else {
            EmailVerifyToken emailNew = new EmailVerifyToken();
            emailNew.setEmail(email);
            emailNew.setToken(token);
            emailNew.setTimeStamp(System.currentTimeMillis() / 1000);
            simpleDBService.insertEmailVerifyToken(emailNew);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
    @ApiOperation(value = "验证邮箱与验证码是否正确", notes = "传入邮箱(email),验证码(token),成功时返回状态200,失败时返回状态以及错误信息")
    @RequestMapping(value = "/email/tokenVerify", method = RequestMethod.POST)
    public ResponseEntity tokenVerify(@RequestBody VerifyData data) throws BaseException{
        String email = data.email;
        String token = data.token;
        if(Objects.isNull(email)||
                Objects.isNull(token)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if (!ParamConstraintUtils.isEmailValid(email)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        IsTokenRightData emailData = new IsTokenRightData();
        Optional<EmailVerifyToken> emailFound = simpleDBService.findEmailVerifyTokenByEmail(email);
        if(emailFound.isPresent()){
            EmailVerifyToken emailVerifyToken = emailFound.get();
            if(token.equals(emailVerifyToken.getToken())&&
                    !ParamConstraintUtils.isTimeExceed(emailVerifyToken.getTimeStamp())){
                emailVerifyToken.setTimeStamp(new Long(0));
                simpleDBService.saveEmailVerifyToken(emailVerifyToken);
                emailData.isTokenCorrect = true;
                return new ResponseEntity(emailData, HttpStatus.OK);
            }else{
                emailData.isTokenCorrect = false;
                return new ResponseEntity(emailData, HttpStatus.OK);
            }
        }else {
            throw new AuthException(103, config.getExceptionsMap().get(103));
        }
    }

}

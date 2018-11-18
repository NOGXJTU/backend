package com.volunteer.commonweal.controllers.homePageController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ModelDataConst;
import com.volunteer.commonweal.constants.UIConst;
import com.volunteer.commonweal.models.ResponseModels.ActivityResponseModel.Duplicate;
import com.volunteer.commonweal.models.ResponseModels.UserResponseModel.IsSuperUserData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.exceptionModels.BaseException;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.UserRequestModels.*;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import com.volunteer.commonweal.services.homePageServices.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Api(description = "用户验证接口信息")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private SimpleDBService simpleDBService;
    private UserService userService;
    private Config config;

    @Autowired
    public UserController(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }
    @ApiOperation(value = "用户注册", notes = "传入用户的用户名(username),密码(password),真实姓名(name),邮箱(email),手机号(phone),qq号(qq),学校(school)" +
            ".成功时候返回状态200同时返回该用户的所有信息,失败时候返回状态并且附有错误信息. 因为通过接口验证过各种信息的错误,所以缺失参数和参数重复默认为恶意访问,将返回参数缺失错误")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity userRegister(@RequestBody RegisterData userData) throws BaseException{
        String username = userData.username;
        String password = userData.password;
        String name = userData.name;
        String email = userData.email;
        String qq = userData.qq;
        String phone = userData.phone;
        String school = userData.school;
        String token = userData.token;
        //必要参数为空报错
        if (Objects.isNull(username)||
                Objects.isNull(password)||
                Objects.isNull(name)||
                Objects.isNull(email)||
                Objects.isNull(qq)||
                Objects.isNull(phone)||
                Objects.isNull(school)||
                Objects.isNull(token)
                ){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //参数无效
        if (!ParamConstraintUtils.isUsernameValid(username)||
                !ParamConstraintUtils.isPasswordValid(password)||
                !ParamConstraintUtils.isNameValid(name)||
                !ParamConstraintUtils.isEmailValid(email)||
                !ParamConstraintUtils.isPhoneValid(phone)||
                !ParamConstraintUtils.isQQValid(qq)||
                !ParamConstraintUtils.isSchoolValid(school)||
                !ParamConstraintUtils.isTokenValid(token)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        //唯一参数重复
        if (isUsernameDuplicate(username)||
                isEmailDuplicate(email)||
                isPhoneDuplicate(phone)){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        //验证邮箱
        boolean vertify = userService.verifyToken(email, token);
        if(!vertify){
            throw new AuthException(1033, config.getExceptionsMap().get(1033));
        }
        //注册用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setQq(qq);
        user.setPhone(phone);
        user.setSchool(school);
        user.setDescription(ModelDataConst.DEFAULT_DESCRIPTION);
        user.setAvatar(ModelDataConst.DEFAULT_AVATAR);
        user.setSuperUser(false);
        return new ResponseEntity(simpleDBService.insertUser(user), HttpStatus.OK);
    }

    @ApiOperation(value = "用户登陆", notes = "传入用户的通行证(passport)和密码(password).成功时返回状态200和用户信息,失败时返回状态和错误信息")
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public ResponseEntity userSignIn(@RequestBody LoginData userData, HttpSession session) throws BaseException{
        String passport = userData.passport;
        String password = userData.password;
        if(Objects.isNull(passport)||
                Objects.isNull(password)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<User> user;
        if(ParamConstraintUtils.isEmail(passport)) {
            user = simpleDBService.findOneUserByEmail(passport);
        }
        else if(ParamConstraintUtils.isPhone(passport)) {
            user = simpleDBService.findOneUserByPhone(passport);
        }
        else {
            user = simpleDBService.findOneUserByUsername(passport);
        }
        if(!user.isPresent()) {
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        if(!user.get().isRightPassword(password)){
            throw new AuthException(1032, config.getExceptionsMap().get(1032));
        }
        session.setAttribute(UIConst.SESSION_USER_ID, user.get().getId());
        System.out.println(session.getAttribute("userId"));
        return new ResponseEntity(user, HttpStatus.OK);
    }

//
    @ApiOperation(value = "用户名是否重复", notes = "传入用户名(username).返回状态200,不重复时候返回vertifyStatus为true,重复的时候返回vertifyStatus为false")
    @RequestMapping(value = "/duplicate/username", method = RequestMethod.POST)
    public ResponseEntity responseIsUsernameDuplicate(@RequestBody UsernameData userData){
        String username = userData.username;
        Duplicate duplicateUserData = new Duplicate();
        if(isUsernameDuplicate(username)){
            duplicateUserData.duplicateFlag = true;
        }else{
            duplicateUserData.duplicateFlag = false;
        }
        return new ResponseEntity(duplicateUserData,HttpStatus.OK);
    }

    @ApiOperation(value = "邮箱是否重复", notes = "传入邮箱(email).返回状态200,不重复时候返回vertifyStatus为true,重复的时候返回vertifyStatus为false")
    @RequestMapping(value = "/duplicate/email", method = RequestMethod.POST)
    public ResponseEntity responseIsEmailDuplicate(@RequestBody UserEmailData userData){
        String email = userData.email;
        Duplicate duplicateUserData = new Duplicate();
        if(isEmailDuplicate(email)){
            duplicateUserData.duplicateFlag = true;
        }else{
            duplicateUserData.duplicateFlag = false;
        }
        return new ResponseEntity(duplicateUserData,HttpStatus.OK);
    }

    @ApiOperation(value = "手机号是否重复", notes = "传入手机号(phone).返回状态200,不重复时候返回vertifyStatus为true,重复的时候返回vertifyStatus为false")
    @RequestMapping(value = "/duplicate/phone", method = RequestMethod.POST)
    public ResponseEntity responseIsPhoneDuplicate(@RequestBody PhoneData userData){
        String phone = userData.phone;
        Duplicate duplicateUserData = new Duplicate();
        if(isPhoneDuplicate(phone)){
            duplicateUserData.duplicateFlag = true;
        }else{
            duplicateUserData.duplicateFlag = false;
        }
        return new ResponseEntity(duplicateUserData,HttpStatus.OK);
    }

    @ApiOperation(value = "获取当前登陆的用户信息", notes = "无参数传入.成功时返回状态200,返回当前登陆的用户信息")
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public ResponseEntity getCurrentUserInfo(HttpSession session) throws BaseException {
        Optional<String> userId = userService.getUserIdFromSession(session);
        if(userId.isPresent()) {
            return new ResponseEntity(simpleDBService.findOneUserById(userId.get()), HttpStatus.OK);
        }
        else{
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
    }


    @ApiOperation(value = "获取指定Id的用户的信息", notes = "传入指定的用户的用户Id(userId).成功时返回状态200,返回指定的用户的信息")
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public ResponseEntity getUserInfo(@RequestBody UserIdData userData, HttpSession session) throws BaseException{
        String userId = userData.userId;
        if(Objects.isNull(userId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<User> user = simpleDBService.findOneUserById(userId);
        if(user.isPresent()){
            return new ResponseEntity(user, HttpStatus.OK);
        }else{
            throw new AuthException(1042, config.getExceptionsMap().get(1042));
        }
    }

    @ApiOperation(value = "批量获取用户信息", notes = "传入用户Id列表(userIdGroup).成功时候返回状态200,返回用户信息列表")
    @RequestMapping(value = "/userInfoGroup",method = RequestMethod.POST)
    public ResponseEntity gerUserInfoGroup(@RequestBody UserIdGroupData userData) throws BaseException{
        List<String> userIdGroup = userData.userIdGroup;
        if(Objects.isNull(userIdGroup)||userIdGroup.size() == 0){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Stream<User> userGroup = simpleDBService.findUserByIdIn(userIdGroup);
        return new ResponseEntity(userGroup, HttpStatus.OK);
    }

    @ApiOperation(value = "用户登出", notes = "无参数传入,当前用户登出")
    @RequestMapping(value = "/signOut",method = RequestMethod.PUT)
    public ResponseEntity userSignOut(HttpSession session){
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "验证已经登录用户的密码(用来修改用户密码)", notes = "传入用户通行证(passport)和用户密码(password)以及新的密码.成功时候返回状态200,不会返回用户信息(更不会返回新的密码),失败返回状态")
    @RequestMapping(value = "/passwordModify",method = RequestMethod.POST)
    public ResponseEntity userPasswordModify(@RequestBody ModifyPasswordData userData, HttpSession session) throws BaseException{
        String passport = userData.passport;
        String password = userData.password;
        String newPassword = userData.newPassword;
        if(!Objects.isNotNull(passport, password, newPassword)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<String> userId = userService.getUserIdFromSession(session);
        if(!userId.isPresent()){
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
        Optional<User> user = simpleDBService.findOneUserById(userId.get());
        Optional<User> userFound;
        User userNew;
        if(!user.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(ParamConstraintUtils.isEmail(passport)){
            userFound = simpleDBService.findOneUserByEmail(passport);
        }else if(ParamConstraintUtils.isPhone(passport)){
            userFound = simpleDBService.findOneUserByPhone(passport);
        }else{
            userFound = simpleDBService.findOneUserByUsername(passport);
        }
        if(userFound.isPresent()){
//            修改密码的用户不是当前用户
            if(!userService.isSameUser(user, userFound)){
                throw new AuthException(1022, config.getExceptionsMap().get(1022));
            }
            if(!userFound.get().isRightPassword(password)){
                throw new AuthException(1032, config.getExceptionsMap().get(1032));
            }else {
                userNew = userFound.get();
                userNew.setPassword(newPassword);
                return new ResponseEntity(simpleDBService.saveUser(userNew), HttpStatus.OK);
            }
        }else{
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
    }


    @ApiOperation(value = "修改用户信息",notes = "传入允许修改的参数(头像avatar,个性宣言description,QQ).成功时返回状态200,返回新的用户信息,失败的时候")
    @RequestMapping(value = "/userInfo/Modify", method = RequestMethod.POST)
    public ResponseEntity userInfoModify(@RequestBody PersonalityData userData, HttpSession session) throws BaseException{
        Optional<String> userId = userService.getUserIdFromSession(session);
        if (!userId.isPresent()){
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
        Optional<User> userFound = simpleDBService.findOneUserById(userId.get());
        userFound.ifPresent(user -> {
            user.setAvatar(userData.avatar);
            user.setDescription(userData.description);
            user.setQq(userData.qq);
        });
        return new ResponseEntity(simpleDBService.saveUser(userFound.get()), HttpStatus.OK);
    }

    @ApiOperation(value = "是否为超级管理员",notes = "无需传递任何参数.成功时返回状态200,返回是否为超级管理员Flag")
    @RequestMapping(value = "/isSuperUser", method = RequestMethod.POST)
    public ResponseEntity responseIsSuperUser(HttpSession session){
        IsSuperUserData userData = new IsSuperUserData();
        if(!userService.isSuperUser(session)){
            userData.isSuperUser = true;
            return new ResponseEntity(userData, HttpStatus.OK);
        }
        userData.isSuperUser = false;
        return new ResponseEntity(userData, HttpStatus.OK);
    }
    private boolean isUsernameDuplicate(String username){
        return simpleDBService.findOneUserByUsername(username).isPresent();
    }
    private boolean isEmailDuplicate(String email){
        return simpleDBService.findOneUserByEmail(email).isPresent();
    }
    private boolean isPhoneDuplicate(String phone){
       return simpleDBService.findOneUserByPhone(phone).isPresent();
    }
}
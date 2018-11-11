package com.volunteer.commonweal.controllers.homePageController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.homePageModels.Organization;
import com.volunteer.commonweal.models.implementModels.homePageModels.OrganizationFoundation;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.OrganizationRequestModels.ApplyOrganizationFoundationData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.OrganizationRequestModels.OrganizationFoundationIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.OrganizationRequestModels.UpdateOrganizationData;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.dataBaseServices.UpdateDBService;
import com.volunteer.commonweal.services.env.Config;
import com.volunteer.commonweal.services.homePageServices.ActivityService;
import com.volunteer.commonweal.services.homePageServices.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(description = "组织加入申请接口信息")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private SimpleDBService simpleDBService;
    private OrganizationService organizationService;
    private Config config;
    private UpdateDBService updateDBService;

    @Autowired
    public OrganizationController(SimpleDBService simpleDBService,
                                  OrganizationService organizationService,
                                  Config config,
                                  UpdateDBService updateDBService){
        this.simpleDBService = simpleDBService;
        this.organizationService = organizationService;
        this.config = config;
        this.updateDBService = updateDBService;
    }

//  组织建立请求相关接口

    //申请发送
    @ApiOperation(value = "发送组织建立申请", notes = "")
    @RequestMapping(value = "/foundation/apply", method = RequestMethod.POST)
    public ResponseEntity applyOrganizationFoundation(@RequestBody ApplyOrganizationFoundationData data, HttpSession session) throws AuthException {
        String name = data.name;
        String leaderId = data.leaderId;
        String description = data.description;
        String location = data.location;
        String applyDescription = data.applyDescription;
        //检查参数是否传入
        if(!Objects.isNotNull(name, leaderId, location, applyDescription)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }

        //检查参数的格式是否符合要求
        if(!ParamConstraintUtils.isDescriptionValid(description)||
                !ParamConstraintUtils.isActivityDescriptionValid(applyDescription)||
                !ParamConstraintUtils.isActivityNameValid(name)){
            throw new AuthException(101, config.getExceptionsMap().get(101));
        }

        Optional<User> leaderFound = simpleDBService.findOneUserById(leaderId);
        if(!leaderFound.isPresent()){
            throw new AuthException(1042, config.getExceptionsMap().get(1042));
        }
        //检查是否该名称组织已经存在或者该名称组织请求已经存在
        if(organizationService.isOrganizationDuplicate(name)||
                organizationService.isOrganizationFoundationDuplicate(name)){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }

        //进行创建
        OrganizationFoundation organizationFoundation = new OrganizationFoundation();
        organizationFoundation.setName(name);
        organizationFoundation.setLeaderId(leaderId);
        organizationFoundation.setDescription(description);
        organizationFoundation.setStatus(0);
        organizationFoundation.setApplyDescription(applyDescription);
        return new ResponseEntity(simpleDBService.insertOrganizationFoundation(organizationFoundation), HttpStatus.OK);
    }

    //同意申请(同意后即创建组织)
    @ApiOperation(value = "同意组织申请", notes = "")
    @RequestMapping(value = "/foundation/agree", method = RequestMethod.POST)
    public ResponseEntity agreeOrganizationFoundation(@RequestBody OrganizationFoundationIdData data, HttpSession session) throws AuthException {
        String organizationFoundationId = data.organizationFoundationId;
        if(Objects.isNull(organizationFoundationId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //管理员身份验证
        if(!organizationService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<OrganizationFoundation> organizationFoundationFound = simpleDBService.findOneOrganizationFoundationById(organizationFoundationId);
        if(!organizationFoundationFound.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }
        //同意申请
        OrganizationFoundation organizationFoundation = organizationFoundationFound.get();
        organizationFoundation.setStatus(1);
        simpleDBService.saveOrganizationFoundation(organizationFoundation);
        //同意后进行组织的创建
        Organization organization = new Organization();
        organization.setName(organizationFoundation.getName());
        organization.setLeaderId(organizationFoundation.getLeaderId());
        organization.setDescription(organizationFoundation.getDescription());
        organization.setLocation(organizationFoundation.getLocation());
        List<String> users = new ArrayList<String>();
        users.add(organizationFoundation.getLeaderId());
        organization.setUsers(users);

        return new ResponseEntity(simpleDBService.saveOrganization(organization), HttpStatus.OK);
    }

    //拒绝申请
    @ApiOperation(value = "拒绝组织申请", notes = "")
    @RequestMapping(value = "/foundation/agree", method = RequestMethod.POST)
    public ResponseEntity refuseOrganizationFoundation(@RequestBody OrganizationFoundationIdData data, HttpSession session) throws AuthException {
        String organizationFoundationId = data.organizationFoundationId;
        if(Objects.isNull(organizationFoundationId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //管理员身份验证
        if(!organizationService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<OrganizationFoundation> organizationFoundationFound = simpleDBService.findOneOrganizationFoundationById(organizationFoundationId);
        if(!organizationFoundationFound.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }

        OrganizationFoundation organizationFoundation = organizationFoundationFound.get();
        organizationFoundation.setStatus(2);
        simpleDBService.saveOrganizationFoundation(organizationFoundation);
        return new ResponseEntity(HttpStatus.OK);
    }

    //获取指定组织的申请
    //同样申请时只能是管理员才能看到
    @ApiOperation(value = "获取指定Id的申请", notes = "")
    @RequestMapping(value = "/foundation/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllOrganizationFoundationWithOrganizationId(@PathVariable String id, HttpSession session) throws AuthException {
        if(Objects.isNull(id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //管理员身份验证
        if(!organizationService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        //进行组织信息的获取
        Optional<OrganizationFoundation> organizationFoundationFound = simpleDBService.findOneOrganizationFoundationById(id);
        //没有该组织
        if(!organizationFoundationFound.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }
        return new ResponseEntity(organizationFoundationFound.get(), HttpStatus.OK);
    }

    //获取指定状态的所有申请
    //同样申请时只能是管理员才能看到
    @ApiOperation(value = "获取指定状态的所有申请", notes = "")
    @RequestMapping(value = "/foundation/getAllAppWithStatus", method = RequestMethod.POST)
    public ResponseEntity agreeOrganizationApply(@RequestParam Integer status, HttpSession session) throws AuthException {
        //参数空缺
        if(Objects.isNull(status)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //参数格式不正确
        if(status != 0 && status != 1 && status != 2){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        //管理员身份验证
        if(!organizationService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        return new ResponseEntity(simpleDBService.findOrganizationFoundationByStatus(status), HttpStatus.OK);
    }

//  组织信息相关接口

    //获取单个组织信息
    @ApiOperation(value = "获取单个组织的信息", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrganizationMessage(@PathVariable String id, HttpSession session) throws AuthException {
        //参数空缺
        if(Objects.isNull(id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<Organization> organizationFound = simpleDBService.findOneOrganizationById(id);
        if(!organizationFound.isPresent()){
            throw new AuthException(1048, config.getExceptionsMap().get(1048));
        }
        return new ResponseEntity(organizationFound.get(), HttpStatus.OK);
    }

    //获取所有组织信息
    @ApiOperation(value = "获取所有组织的信息", notes = "")
    @RequestMapping(value = "/getAllMsg", method = RequestMethod.GET)
    public ResponseEntity getAllOrganizationMessage(){
        return new ResponseEntity(simpleDBService.findAllOrganization(), HttpStatus.OK);
    }

    //修改组织信息(权限是什么???) 不包括删除组织成员
    @ApiOperation(value = "修改组织的信息", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrganization(@PathVariable String id, @RequestBody UpdateOrganizationData data, HttpSession session) throws AuthException {
        //参数缺失
        String description = data.description;
        String location = data.location;
        if(!Objects.isNotNull(description, location, id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //参数格式判断
        if(!ParamConstraintUtils.isDescriptionValid(description)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        //组织存在判断
        Optional<Organization> organizationFound = simpleDBService.findOneOrganizationById(id);
        if(!organizationFound.isPresent()){
            throw new AuthException(1048, config.getExceptionsMap().get(1048));
        }
        Organization organization = organizationFound.get();
        //权限判断
        if(!organizationService.isSuperUser(session)
                || !organizationService.isOrganizationLeader(session, organization)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }

        organization.setLocation(location);
        organization.setDescription(description);
        return new ResponseEntity(simpleDBService.saveOrganization(organization), HttpStatus.OK);
    }

    //删除组织中成员
    @ApiOperation(value = "删除单个组织成员", notes = "")
    @RequestMapping(value = "/delete/{organizationId}/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrganization(@PathVariable String organizationId, @PathVariable String userId, HttpSession session) throws AuthException {
        //参数缺失
        if(!Objects.isNotNull(organizationId, userId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //组织存在判断
        Optional<Organization> organizationFound = simpleDBService.findOneOrganizationById(organizationId);
        if(!organizationFound.isPresent()){
            throw new AuthException(1048, config.getExceptionsMap().get(1048));
        }
        Organization organization = organizationFound.get();

        //权限判断
        if(!organizationService.isSuperUser(session)
                || !organizationService.isOrganizationLeader(session, organization)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }

        //用户删除开始
        List<String> users = organization.getUsers();
        if(!users.contains(userId)){
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        users.remove(userId);
        updateDBService.removeUserOrganization(userId, organizationId);
        return new ResponseEntity(simpleDBService.saveOrganization(organization), HttpStatus.OK);
    }

    //删除组织(权限是什么???)
    @ApiOperation(value = "删除单个组织", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrganization(@PathVariable String id, HttpSession session) throws AuthException {
        //参数缺失
        if(Objects.isNull(id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //管理员身份验证
        if(!organizationService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteOneOrganizationById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

//  申请加入组织相关接口

//    //申请发送
//    @ApiOperation(value = "申请加入组织", notes = "")
//    @RequestMapping(value = "/join/apply", method = RequestMethod.POST)
//    public ResponseEntity applyOrganizationApply(){
//
//    }
//
//    //申请通过
//    @ApiOperation(value = "通过加入组织申请", notes = "")
//    @RequestMapping(value = "/join/agree", method = RequestMethod.POST)
//    public ResponseEntity agreeOrganizationApply(){
//
//    }
//
//    //申请拒绝
//    @ApiOperation(value = "拒绝加入组织申请", notes = "")
//    @RequestMapping(value = "/join/refuse", method = RequestMethod.POST)
//    public ResponseEntity refuseOrganizationApply(){
//
//    }
//
//
//    //退出组织
//    @ApiOperation(value = "退出组织", notes = "")
//    @RequestMapping(value = "/apply/quit", method = RequestMethod.POST)
//    public ResponseEntity quitOrganization(){
//
//    }
//
//    //查看单条申请信息
//    @ApiOperation(value = "查看单条申请信息", notes = "")
//    @RequestMapping(value = "/apply/", method = RequestMethod.GET)
//    public ResponseEntity getOrganizationApplyMsg(){
//
//    }
//
//    //查看多条申请信息(通过组织Id进行查看)
//    @ApiOperation(value = "查看多条申请信息", notes = "")
//    @RequestMapping(value = "/apply/getAllAppWithId", method = RequestMethod.GET)
//    public ResponseEntity getAllOrganizationApplyMsgWithOrganizationId(){
//
//    }


}

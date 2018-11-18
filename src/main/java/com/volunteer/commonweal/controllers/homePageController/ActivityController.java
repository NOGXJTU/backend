package com.volunteer.commonweal.controllers.homePageController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ManageMentConst;
import com.volunteer.commonweal.constants.ModelDataConst;
import com.volunteer.commonweal.models.ResponseModels.ActivityResponseModel.Duplicate;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.ActivityConfigData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.exceptionModels.BaseException;
import com.volunteer.commonweal.models.implementModels.homePageModels.Activity;
import com.volunteer.commonweal.models.implementModels.homePageModels.ActivityApply;
import com.volunteer.commonweal.models.implementModels.homePageModels.Organization;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.*;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.dataBaseServices.UpdateDBService;
import com.volunteer.commonweal.services.env.Config;
import com.volunteer.commonweal.services.homePageServices.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Api(description = "活动接口信息")
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    private SimpleDBService simpleDBService;
    private ActivityService activityService;
    private Config config;
    private UpdateDBService updateDBService;

    @Autowired
    ActivityController(SimpleDBService simpleDBService, ActivityService activityService, Config config, UpdateDBService updateDBService){
        this.simpleDBService = simpleDBService;
        this.activityService = activityService;
        this.config = config;
        this.updateDBService = updateDBService;
    }

//  下面是ActivityApply相关的操作
    @ApiOperation(value = "活动请求发送(用户Id)", notes = "")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseEntity applyActivityApply(@RequestBody ApplyActivityApplyData data, HttpSession session) throws AuthException {
        String organizationId = data.organizationId; //可以空吗? 暂时设定为不可为空
        String name = data.name;
        String ownerId = data.ownerId;
        String description = data.description;
        String applyDescription = data.applyDescription;
        String place = data.place;
        String beginTime = data.beginTime;
        String lasting = data.lasting;
        String type = data.type;
        String picUrl = data.picUrl; //可以为空
        if(!Objects.isNotNull(name, ownerId, description, applyDescription
        , place, beginTime, lasting, type)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<User> ownerFound = simpleDBService.findOneUserById(ownerId);
        if (!ownerFound.isPresent()){
            throw new AuthException(1042, config.getExceptionsMap().get(1042));
        }

        //格式要正确
        if (!ParamConstraintUtils.isActivityNameValid(name)||
                !ParamConstraintUtils.isActivityDescriptionValid(description)||
                !ParamConstraintUtils.isActivityTypeValid(type)||
                !ParamConstraintUtils.isActivityDescriptionValid(applyDescription)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        //活动名称不能是现在已经存在的，也不能是申请中已经存在的
        if (activityService.isActivityDuplicate(name, beginTime)
                ||activityService.isActivityApplyDuplicate(name)) {
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }

        User owner = ownerFound.get();
        //owner的组织中必须包含这个活动的组织
        if(!owner.getOrganizations().contains(organizationId)){
            throw new AuthException(1048, config.getExceptionsMap().get(1048));
        }
        ActivityApply activityApply = new ActivityApply();
        activityApply.setOrganizationId(organizationId);
        activityApply.setName(name);
        activityApply.setOwnerId(owner.getId());
        activityApply.setDescription(description);
        activityApply.setPlace(place);
        activityApply.setBeginTime(beginTime);
        activityApply.setLasting(lasting);
        activityApply.setType(type);
        activityApply.setPicUrl(picUrl);
        activityApply.setStatus(0);
        activityApply.setDescription(description);
        return new ResponseEntity(simpleDBService.insertActivityApply(activityApply), HttpStatus.OK);
    }
    @ApiOperation(value = "活动请求发送(手机号)", notes = "")
    @RequestMapping(value = "/phoneApply", method = RequestMethod.POST)
    public ResponseEntity applyActivityApplyByPhone(@RequestBody ApplyActivityApplyByPhoneData data, HttpSession session) throws AuthException {
        String organizationId = data.organizationId; //可以空吗? 暂时设定为不可为空
        String name = data.name;
        String ownerPhone = data.ownerPhone;
        String description = data.description;
        String applyDescription = data.applyDescription;
        String place = data.place;
        String beginTime = data.beginTime;
        String lasting = data.lasting;
        String type = data.type;
        String picUrl = data.picUrl; //可以为空
        if(!Objects.isNotNull(name, ownerPhone, description, applyDescription
                , place, beginTime, lasting, type)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<User> ownerFound = simpleDBService.findOneUserByPhone(ownerPhone);
        if (!ownerFound.isPresent()){
            throw new AuthException(1042, config.getExceptionsMap().get(1042));
        }

        //格式要正确
        if (!ParamConstraintUtils.isActivityNameValid(name)||
                !ParamConstraintUtils.isActivityDescriptionValid(description)||
                !ParamConstraintUtils.isActivityTypeValid(type)||
                !ParamConstraintUtils.isActivityDescriptionValid(applyDescription)||
                !ParamConstraintUtils.isPhone(ownerPhone)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        //活动名称不能是现在已经存在的，也不能是申请中已经存在的
        if (activityService.isActivityDuplicate(name, beginTime)
                ||activityService.isActivityApplyDuplicate(name)) {
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }

        User owner = ownerFound.get();
        //owner的组织中必须包含这个活动的组织
        if(!owner.getOrganizations().contains(organizationId)){
            throw new AuthException(1048, config.getExceptionsMap().get(1048));
        }
        ActivityApply activityApply = new ActivityApply();
        activityApply.setOrganizationId(organizationId);
        activityApply.setName(name);
        activityApply.setOwnerId(owner.getId());
        activityApply.setDescription(description);
        activityApply.setPlace(place);
        activityApply.setBeginTime(beginTime);
        activityApply.setLasting(lasting);
        activityApply.setType(type);
        activityApply.setPicUrl(picUrl);
        activityApply.setStatus(0);
        activityApply.setDescription(description);
        return new ResponseEntity(simpleDBService.insertActivityApply(activityApply), HttpStatus.OK);
    }
    @ApiOperation(value = "拒绝活动申请", notes = "")
    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    public ResponseEntity refuseActivityApply(@RequestBody ActivityApplyIdData data, HttpSession session) throws AuthException {
        String activityApplyId = data.activityApplyId;
        if(Objects.isNull(activityApplyId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //管理员身份验证
        if(!activityService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<ActivityApply> activityApplyFound = simpleDBService.findOneActivityApplyById(activityApplyId);
        if(!activityApplyFound.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }

        ActivityApply activityApply = activityApplyFound.get();
        activityApply.setStatus(2);
        return new ResponseEntity(simpleDBService.saveActivityApply(activityApply), HttpStatus.OK);
    }

    @ApiOperation(value = "同意活动申请", notes = "")
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    public ResponseEntity agreeActivityApply(@RequestBody ActivityApplyIdData data, HttpSession session) throws AuthException {
        String activityApplyId = data.activityApplyId;
        if(Objects.isNull(activityApplyId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //管理员身份验证
        if(!activityService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<ActivityApply> activityApplyFound = simpleDBService.findOneActivityApplyById(activityApplyId);
        if(!activityApplyFound.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }

        ActivityApply activityApply = activityApplyFound.get();
        activityApply.setStatus(1);
        return new ResponseEntity(simpleDBService.saveActivityApply(activityApply), HttpStatus.OK);
    }


//  下面是Activity相关的操作

    @ApiOperation(value = "批量获取指定活动Id的活动信息", notes = "传入活动IdList(activityIdList),成功时返回状态200返回活动详细信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public ResponseEntity getBatchActivity(@RequestBody ActivityIdListData data) throws BaseException {
        List<String> activityIdList = data.activityIdList;
        Stream<Activity> activityStream = simpleDBService.findAllActivityById(activityIdList);
        if(Objects.isNull(activityStream)){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }
        return new ResponseEntity(activityStream, HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定活动Id的活动信息", notes = "传入活动Id(activityId),成功时返回状态200返回活动详细信息,失败时返回状态以及错误信息")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity getActivity(@RequestBody OnlyIdData data) throws BaseException {
        String activityId = data.activityId;
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        return new ResponseEntity(activityFound.get(), HttpStatus.OK);
    }

//    目前返回全部的信息,并购且没有设置任何限制的访问权限
    @ApiOperation(value = "获取所有指定状态的活动的信息", notes = "成功时返回状态200以及所有活动的部分信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAllActivity(@RequestParam Boolean finished, HttpSession session){
        return new ResponseEntity(simpleDBService.findAllActivityByFinished(finished), HttpStatus.OK);
    }


    //TODO:用事务改写
    @ApiOperation(value = "生成活动", notes = "传入除了活动成员Id和活动状态之外的所有其他参数,成功时返回状态200,并且返回活动信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ResponseEntity generateActivity(@RequestBody GenerateData data, HttpSession session) throws BaseException {
        //必须是超级管理员才能调用
        List<String> users = new ArrayList<>();
        String name = data.name;
        String ownerId = data.ownerId;
        String description = data.description;
        String place = data.place;
        Boolean finished = ModelDataConst.DEFAULT_FINISHED;
        String beginTime = data.beginTime;
        String lasting = data.lasting;
        String type = data.type;
        String picUrl = data.picUrl;
        String organizationId = data.organizationId;
        Optional<User> owner = simpleDBService.findOneUserByUserId(ownerId);
        if (!Objects.isNotNull(name, ownerId, description, place, beginTime, lasting, type, picUrl, organizationId)) {
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!owner.isPresent()){
            throw new AuthException(1042, config.getExceptionsMap().get(1042));
        }
        if (!ParamConstraintUtils.isActivityNameValid(name)||
                !ParamConstraintUtils.isActivityDescriptionValid(description)||
                !ParamConstraintUtils.isActivityTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        if (!activityService.isSuperUser(session)) {
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if (activityService.isActivityDuplicate(name, beginTime)) {
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        //组织是否存在的验证
        Optional<Organization> organizationFound = simpleDBService.findOneOrganizationById(organizationId);
        if(!organizationFound.isPresent()){
            throw new AuthException(1048, config.getExceptionsMap().get(1048));
        }
        User ownerFound = owner.get();
        users.add(ownerId);
        Activity activity = new Activity();
        activity.setUsers(users);
        activity.setName(name);
        activity.setOwnerId(ownerId);
        activity.setDescription(description);
        activity.setPlace(place);
        activity.setFinished(finished);
        activity.setBeginTime(beginTime);
        activity.setLasting(lasting);
        activity.setType(type);
        activity.setPicUrl(picUrl);
        activity.setOrganizationId(organizationId);
        activity = simpleDBService.insertActivity(activity);
        //保存修改user和修改organization
        ownerFound.getActivitiesId().add(activity.getId());
        simpleDBService.saveUser(ownerFound);
        Organization organization = organizationFound.get();
        organization.getActivities().add(activity.getId());
        simpleDBService.saveOrganization(organization);
        return new ResponseEntity(activity, HttpStatus.OK);
    }

    @ApiOperation(value = "删除指定活动", notes = "传入活动Id(activityId),成功时返回状态200,失败时返回状态以及错误信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity deleteActivity(@RequestBody OnlyIdData data, HttpSession session) throws BaseException{
        String activityId = data.activityId;
        if(Objects.isNull(activityId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!activityService.isSuperUser(session) && !activityService.isGroupOwner(session, activityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        //开始清除成员中的活动列表
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        Stream<User> userStream = simpleDBService.findUserByIdIn(activityFound.get().getUsers());
        userStream.forEach(user -> user.getActivitiesId().remove(activityId));
        simpleDBService.saveUsers(userStream);
        simpleDBService.deleteActivityByActivityId(activityId);
        Organization organization = simpleDBService.findOneOrganizationById(activityFound.get().getOrganizationId()).get();
        organization.getActivities().remove(activityId);
        simpleDBService.saveOrganization(organization);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "修改指定活动信息", notes = "传入活动Id(activityId)以及活动的信息,成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity modifyActivity(@RequestBody ModifyData data, HttpSession session) throws BaseException{
        String activityId = data.activityId;
        String name = data.name;
        String ownerId = data.ownerId;
        String description = data.description;
        String place = data.place;
        Boolean finished = data.finished;
        String beginTime = data.beginTime;
        String lasting = data.lasting;
        String type = data.type;
        String picUrl = data.picUrl;
        if(!Objects.isNotNull(activityId, ownerId, name, description, place, finished, beginTime, lasting, type, picUrl)){
            throw new AuthException(1011,config.getExceptionsMap().get(1011));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        if(!activityService.isSuperUser(session) && !activityService.isGroupOwner(session, activityFound.get())){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if (!ParamConstraintUtils.isActivityNameValid(name)||
                !ParamConstraintUtils.isActivityDescriptionValid(description)||
                !ParamConstraintUtils.isActivityTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Activity activity = activityFound.get();
        String nameOld = activity.getName();
        String beginTimeOld = activity.getBeginTime();
        activity.setName(name);
        activity.setOwnerId(ownerId);
        activity.setDescription(description);
        activity.setPlace(place);
        activity.setFinished(finished);
        activity.setBeginTime(beginTime);
        activity.setLasting(lasting);
        activity.setType(type);
        activity.setPicUrl(picUrl);
        activityFound = simpleDBService.findOneActivityByNameAndBeginTime(name, beginTime);
        if(activityFound.isPresent() && name == nameOld && beginTimeOld == beginTime){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    @ApiOperation(value = "修改指定活动状态", notes = "传入活动Id(activityId),成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/status/modify", method = RequestMethod.POST)
    public ResponseEntity modifyStatusActivity(@RequestBody OnlyIdData data, HttpSession session) throws BaseException {
        String activityId = data.activityId;
        if(Objects.isNull(activityId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        if(!activityService.isSuperUser(session) && !activityService.isGroupOwner(session, activityFound.get())){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        activityFound.ifPresent(activity -> activity.setFinished(true));
        return new ResponseEntity(simpleDBService.saveActivity(activityFound.get()), HttpStatus.OK);
    }

    @ApiOperation(value = "转让组长权限", notes = "传入活动Id(activityId),新的组长的Id(newOwnerId),成功时返回状态200,并返回修改后活动的信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ResponseEntity transferActivityOwner(@RequestBody IdAndUserIdData data, HttpSession session) throws BaseException{
        String activityId = data.activityId;
        String newOwnerId = data.userId;
        if(!Objects.isNotNull(activityId, newOwnerId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        // 权限要求是组长本人
        if(!activityService.isGroupOwner(session, activityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Activity activity = activityFound.get();
        //必须是本活动的成员
        if(!activity.getUsers().contains(newOwnerId)){
            throw new AuthException(1045, config.getExceptionsMap().get(1045));
        }
        activity.setOwnerId(newOwnerId);
        return new ResponseEntity(simpleDBService.saveActivity(activityFound.get()), HttpStatus.OK);
    }

    @ApiOperation(value = "移除成员", notes = "传入活动Id(activityId),移除的成员的Id(userId),成功时返回状态200,并返回修改后活动的信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/removeMember", method = RequestMethod.POST)
    public ResponseEntity removeActivityMember(@RequestBody IdAndUserIdData data, HttpSession session) throws BaseException {
        String activityId = data.activityId;
        String userId = data.userId;
        if(!Objects.isNotNull(activityId, userId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!activityService.isGroupOwner(session, activityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        Activity activity = activityFound.get();
        List<String> userIdList = activity.getUsers();
        if(!userIdList.contains(userId)){
            throw new AuthException(1045, config.getExceptionsMap().get(1045));
        }
        userIdList.remove(userId);
        updateDBService.removeUserActivity(userId, activityId);
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    @ApiOperation(value = "批量移除成员", notes = "传入活动Id(activityId)以及移除成员列表(userIdGroup),成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/removeMember/Group", method = RequestMethod.POST)
    public ResponseEntity removeActivityMemberGroup(@RequestBody IdAndUserListData data, HttpSession session) throws BaseException {
        List<String> userIdGroup = data.userIdGroup;
        String activityId = data.activityId;
        if(!Objects.isNotNull(userIdGroup, activityId) || userIdGroup.size() == 0){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        if(!activityService.isGroupOwner(session, activityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Activity activity = activityFound.get();
        List<String> userIdList = activity.getUsers();
        userIdList.removeAll(userIdGroup);
        updateDBService.removeUserGroupActivity(userIdList, activityId);
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    //TODO:这个接口暂时没有什么必要,因为目前添加成员的途径只有通过申请
    @ApiOperation(value = "添加成员", notes = "传入活动Id(activityId)以及添加的成员的Id(userId),成功时返回状态200,并返回修改后的活动信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public ResponseEntity addActivityMember(@RequestBody IdAndUserIdData data, HttpSession session) throws BaseException {
        String activityId = data.activityId;
        String userId = data.userId;
        if(!Objects.isNotNull(activityId, userId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!activityService.isGroupOwner(session, activityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        Activity activity = activityFound.get();
        List<String> userIdList = activity.getUsers();
        userIdList.add(userId);
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    @ApiOperation(value = "主动退出活动", notes = "传入活动Id(activityId),成功时返回状态200,并返回修改后活动的信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/quit", method = RequestMethod.POST)
    public ResponseEntity quitActivity(@RequestBody OnlyIdData data, HttpSession session) throws BaseException {
        String activityId = data.activityId;
        if(Objects.isNull(data)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(activityService.isGroupOwner(session,activityId)){
            throw new AuthException(1023, config.getExceptionsMap().get(1023));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        String userId = activityService.getUserIdFromSession(session).get();
        Activity activity = activityFound.get();
        List<String> userIdList = activity.getUsers();
        if(!userIdList.contains(userId)){
            throw new AuthException(1045, config.getExceptionsMap().get(1045));
        }
        userIdList.remove(userId);
        updateDBService.removeUserActivity(userId, activityId);
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    @ApiOperation(value = "活动是否重复", notes = "传入活动名称(name)活动起始时间(beginTime),成功时返回状态200 以及返回是否重复的flag,失败时返回状态以及错误信息")
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    public ResponseEntity isDuplicateActivity(@RequestBody DuplicateData data) throws AuthException{
        String name = data.name;
        String beginTime = data.beginTime;
        if(!Objects.isNotNull(name, beginTime)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Duplicate activityData = new Duplicate();
        activityData.duplicateFlag = activityService.isActivityDuplicate(name, beginTime);
        return new ResponseEntity(activityData, HttpStatus.OK);
    }

    @ApiOperation(value = "活动结束上传资料", notes = "传入活动id(activityId),活动总结(comment),活动是否展示(show),活动图片集合(pictures)")
    @RequestMapping(value = "/finished/upload", method = RequestMethod.POST)
    public ResponseEntity uploadFinishedActivityMessage(@RequestBody FinishedUploadData data, HttpSession session) throws AuthException{
        String activityId = data.activityId;
        String commonet = data.comment;
        Boolean show = data.show;
        if(Objects.isNull(show)) show = true; //默认展示 如果没有传递这个参数
        List<String> pictures = data.pictures;
        //要求必要参数非空
        if(!Objects.isNotNull(activityId, commonet, pictures)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        //要求活动已经存在
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        //要求活动已经结束
        if(activityFound.get().getFinished() == false){
            throw new AuthException(1015, config.getExceptionsMap().get(1015));
        }
        //要求请求人是活动的超级管理员或者是活动的组长
        if(!activityService.isSuperUser(session) && !activityService.isGroupOwner(session, activityFound.get())){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        //进行信息修改
        Activity activity = activityFound.get();
        activity.setShow(show);
        activity.setComment(commonet);
        activity.setPictures(pictures);
        activity.setFinished(true);
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有展示活动(show=true且finished=true)", notes = "不需要传入任何参数")
    @RequestMapping(value = "finished/show", method = RequestMethod.GET)
    public ResponseEntity showAllFinishedAndShowActivityMessage(){
        return new ResponseEntity(simpleDBService.findAllActivityByShowAndFinished(true, true), HttpStatus.OK);
    }

    // 管理界面接口的实现
    @ApiOperation(value = "获取所有活动信息", notes = "")
    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public ResponseEntity managementActivityGetAll(HttpSession session) throws AuthException {
        if(!activityService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        ActivityConfigData data = new ActivityConfigData();
        data.data = simpleDBService.findAllActivity();
        data.total = data.data.size();
        data.perPage = ManageMentConst.PERPAGE_COUNT;
        data.page = (int) Math.ceil((double)data.total/(double)data.perPage);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    //todo:加一个根据组织获取活动信息???


    @ApiOperation(value = "获取活动views", notes = "")
    @RequestMapping(value = "/activity/view", method = RequestMethod.GET)
    public ResponseEntity managementAcitivityGetView(HttpSession session) throws AuthException {
        if(!activityService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  id: {cols: 3},\n" +
                "  type: {\n" +
                "    label: '种类', \n" +
                "    type: 'radiolist', cols: 3, options: [\n" +
                "      { text: '文化课', value: '文化课' },\n" +
                "      { text: '兴趣课', value: '兴趣课' },\n" +
                "      { text: '思想沙龙', value: '思想沙龙' },\n" +
                "      { text: '公益晚会', value: '公益晚会' },\n" +
                "      { text: '其他', value: '其他' },\n" +
                "    ], searchable: true, description: '请选择活动类型'\n" +
                "  },\n" +
                "  name: {label: '活动名称',  cols: 6, searchable: true },\n" +
                "  ownerId: {label: '组长ID',  cols: 3 },\n" +
                "  place: {label: '地点',  cols: 3 },\n" +
                "  finished: {label: '是否结束', type: 'switch', cols: 3 },\n" +
                "  presented: {label: '是否展示', type: 'switch', cols: 3 },\n" +
                "  picUrl: {label: '图片', type: 'image', cols: 6 , size: 10485760 },\n" +
                "  conclusion: {label: '总结', type: 'html', listable: false, cols: 6 },\n" +
                "  description: {label: '描述', type: 'html', listable: false, cols: 6 },\n" +
                "  beginTime: { label: '开始时间', type: 'date' },\n" +
                "  lasting: {label: '持续时间', cols: 3 }\n" +
                "  }";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取单条活动信息", notes = "")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    public ResponseEntity managementActivityGetOne(@PathVariable String id, HttpSession session) throws AuthException {
        if(!activityService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Activity> activity = simpleDBService.findOneActivityByActivityId(id);
        if(!activity.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        return new ResponseEntity(activity.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改单条活动信息", notes = "")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
    public ResponseEntity managementAcitityModifyOne(@PathVariable String id, @RequestBody NewModifyData data, HttpSession session) throws AuthException{
        String activityId = data.id;
        String name = data.name;
        String ownerId = data.ownerId;
        String description = data.description;
        String place = data.place;
        Boolean finished = data.finished;
        String beginTime = data.beginTime;
        String lasting = data.lasting;
        String type = data.type;
        String picUrl = data.picUrl;
        if(!Objects.isNotNull(activityId, ownerId, name, description, place, finished, beginTime, lasting, type, picUrl)){
            throw new AuthException(1011,config.getExceptionsMap().get(1011));
        }
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(id);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        if(!activityService.isSuperUser(session) && !activityService.isGroupOwner(session, activityFound.get())){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if (!ParamConstraintUtils.isActivityNameValid(name)||
                !ParamConstraintUtils.isActivityDescriptionValid(description)||
                !ParamConstraintUtils.isActivityTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Activity activity = activityFound.get();
        String nameOld = activity.getName();
        String beginTimeOld = activity.getBeginTime();
        activity.setName(name);
        activity.setOwnerId(ownerId);
        activity.setDescription(description);
        activity.setPlace(place);
        activity.setFinished(finished);
        activity.setBeginTime(beginTime);
        activity.setLasting(lasting);
        activity.setType(type);
        activity.setPicUrl(picUrl);
        activityFound = simpleDBService.findOneActivityByNameAndBeginTime(name, beginTime);
        if(activityFound.isPresent() && name == nameOld && beginTimeOld == beginTime){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        return new ResponseEntity(simpleDBService.saveActivity(activity), HttpStatus.OK);
    }

    @ApiOperation(value = "创建活动信息", notes = "")
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public ResponseEntity managementGenerateActivity(@RequestBody GenerateData data,HttpSession session) throws AuthException {
        //必须是超级管理员才能调用
        List<String> users = new ArrayList<>();
        String name = data.name;
        String ownerId = data.ownerId;
        String description = data.description;
        String place = data.place;
        Boolean finished = ModelDataConst.DEFAULT_FINISHED;
        String beginTime = data.beginTime;
        String lasting = data.lasting;
        String type = data.type;
        String picUrl = data.picUrl;
        Optional<User> owner = simpleDBService.findOneUserByUserId(ownerId);
        if(!owner.isPresent()){
            throw new AuthException(1042, config.getExceptionsMap().get(1042));
        }
        if (!Objects.isNotNull(name, ownerId, description, place, beginTime, lasting, type, picUrl)) {
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if (!ParamConstraintUtils.isActivityNameValid(name)||
                !ParamConstraintUtils.isActivityDescriptionValid(description)||
                !ParamConstraintUtils.isActivityTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        if (!activityService.isSuperUser(session)) {
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if (activityService.isActivityDuplicate(name, beginTime)) {
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        User ownerFound = owner.get();
        users.add(ownerId);
        Activity activity = new Activity();
        activity.setUsers(users);
        activity.setName(name);
        activity.setOwnerId(ownerId);
        activity.setDescription(description);
        activity.setPlace(place);
        activity.setFinished(finished);
        activity.setBeginTime(beginTime);
        activity.setLasting(lasting);
        activity.setType(type);
        activity.setPicUrl(picUrl);
        activity = simpleDBService.insertActivity(activity);
        ownerFound.getActivitiesId().add(activity.getId());
        return new ResponseEntity(simpleDBService.saveUser(ownerFound), HttpStatus.OK);
    }

    //todo:
    @ApiOperation(value = "删除活动", notes = "")
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity managementDeleteActivity(@PathVariable String id, HttpSession session) throws AuthException {
        if(Objects.isNull(id)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!activityService.isSuperUser(session) && !activityService.isGroupOwner(session, id)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteActivityByActivityId(id);
        //开始清除成员中的活动列表
        Optional<Activity> activityFound = simpleDBService.findOneActivityByActivityId(id);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        Stream<User> userStream = simpleDBService.findUserByIdIn(activityFound.get().getUsers());
        userStream.forEach(user -> user.getActivitiesId().remove(id));
        simpleDBService.saveUsers(userStream);

        Organization organization = simpleDBService.findOneOrganizationById(activityFound.get().getOrganizationId()).get();
        organization.getActivities().remove(id);
        simpleDBService.saveOrganization(organization);
        return new ResponseEntity(HttpStatus.OK);
        //TODO:可以考虑删掉对应活动的所有申请(但是没有必要，只是减少了数据库中的部分内容，同时审批记录就看不到了)
    }

    @ApiOperation(value = "活动grid", notes = "")
    @RequestMapping(value = "/activity/grid", method = RequestMethod.GET)
    public ResponseEntity managementGridGet(HttpSession session) throws AuthException {
        if (!activityService.isSuperUser(session)) {
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  searchModel: {},\n" +
                "  searchFields: {\n" +
                "    name: {label: '活动名称',  cols: 6, searchable: true }\n" +
                "  },\n" +
                "  fields: {\n" +
                "  id: {cols: 3},\n" +
                "  type: {\n" +
                "    label: '种类', \n" +
                "    type: 'radiolist', cols: 3, options: [\n" +
                "      { text: '文化课', value: '文化课' },\n" +
                "      { text: '兴趣课', value: '兴趣课' },\n" +
                "      { text: '思想沙龙', value: '思想沙龙' },\n" +
                "      { text: '公益晚会', value: '公益晚会' },\n" +
                "      { text: '其他', value: '其他' },\n" +
                "    ], searchable: true, description: '请选择活动类型'\n" +
                "  },\n" +
                "  name: {label: '活动名称',  cols: 6, searchable: true },\n" +
                "  ownerId: {label: '组长ID',  cols: 3 },\n" +
                "  place: {label: '地点',  cols: 3 },\n" +
                "  finished: {label: '是否结束', type: 'switch', cols: 3 },\n" +
                "  presented: {label: '是否展示', type: 'switch', cols: 3 },\n" +
                "  picUrl: {label: '图片', type: 'image', cols: 6 ,size: 10485760 },\n" +
                "  conclusion: {label: '总结', type: 'html', listable: false, cols: 6 },\n" +
                "  description: {label: '描述', type: 'html', listable: false, cols: 6 },\n" +
                "  beginTime: { label: '开始时间', type: 'date' },\n" +
                "  lasting: {label: '持续时间', cols: 3 }\n" +
                "}\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }
}

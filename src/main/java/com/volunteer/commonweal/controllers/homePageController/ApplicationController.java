package com.volunteer.commonweal.controllers.homePageController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ModelDataConst;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.exceptionModels.BaseException;
import com.volunteer.commonweal.models.implementModels.homePageModels.Activity;
import com.volunteer.commonweal.models.implementModels.homePageModels.Application;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ActivityRequestModels.OnlyIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ApplicationRequestModels.ActivityIdAndStatusData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ApplicationRequestModels.ApplicationIdData;
import com.volunteer.commonweal.models.requestModels.homePageRequestModels.ApplicationRequestModels.SendData;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.dataBaseServices.UpdateDBService;
import com.volunteer.commonweal.services.env.Config;
import com.volunteer.commonweal.services.homePageServices.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Api(description = "活动申请接口信息")
@RestController
@RequestMapping(value = "/application")
public class ApplicationController {
    private SimpleDBService simpleDBService;
    private ApplicationService applicationService;
    private Config config;
    private UpdateDBService updateDBService;

    @Autowired
    public ApplicationController(SimpleDBService simpleDBService, ApplicationService applicationService ,Config config, UpdateDBService updateDBService){
        this.simpleDBService = simpleDBService;
        this.applicationService = applicationService;
        this.config = config;
        this.updateDBService = updateDBService;
    }

    @ApiOperation(value = "发送活动加入申请", notes = "传入活动Id(activityId),申请理由(description),成功时返回状态200,失败时返回状态以及错误信息")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity applicationRegister(@RequestBody SendData data, HttpSession session) throws BaseException {
        String activityId = data.activityId;
        String description = data.description;
        Optional<String> userId = applicationService.getUserIdFromSession(session);
        if(Objects.isNull(activityId)||
                Objects.isNull(description)||
                !userId.isPresent()){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<User> userFound = simpleDBService.findOneUserById(userId.get());
        if(!userFound.isPresent()){
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        if(!ParamConstraintUtils.isDescriptionValid(description)){
            throw new AuthException(101, config.getExceptionsMap().get(101));
        }
        Optional<Activity> activity = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activity.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        if(userFound.get().getActivitiesId().contains(activityId)){
            throw new AuthException(1051, config.getExceptionsMap().get(1051));
        }
        Optional<Application> application = simpleDBService.findOneApplicationByUserIdAndActivityIdAndStatus(userId.get(), activityId, 0);
        if(application.isPresent()){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        Application applicationNew = new Application();
        applicationNew.setActivityId(activityId);
        applicationNew.setDescription(description);
        applicationNew.setUserId(userId.get());
        applicationNew.setStatus(ModelDataConst.DEFAULT_APPLICATION_STATUS);
        return new ResponseEntity(simpleDBService.insertApplication(applicationNew), HttpStatus.OK);
    }

    //TODO: 下面的方法涉及到了并发问题,建议最后用事务进行实现 -- 暂时没有解决
    @ApiOperation(value = "同意活动申请", notes = "传入申请Id(applicationId),成功时返回状态200,返回当前申请的信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ResponseEntity applicationConfirm(@RequestBody ApplicationIdData data, HttpSession session) throws BaseException{
        //只有组长才能处理/获得所有申请列表
        String applicationId = data.applicationId;
        if(!Objects.isNotNull(applicationId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<Application> application = simpleDBService.findOneApplicationByApplicationId(applicationId);
        Application applicationFound = application.get();
        if(!application.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }
        if(!applicationService.isGroupOwner(session, applicationFound.getActivityId())){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        applicationFound.setStatus(1);
        updateDBService.addUserActivity(applicationFound.getUserId(), applicationFound.getActivityId());
        return new ResponseEntity(simpleDBService.saveApplication(applicationFound), HttpStatus.OK);
    }

    @ApiOperation(value = "拒绝活动申请", notes = "传入申请Id(applicationId),成功时返回状态200,返回当前申请的信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    public ResponseEntity applicationRefuse(@RequestBody ApplicationIdData data, HttpSession session) throws BaseException{
        //只有组长才能处理/获得所有申请列表
        String applicationId = data.applicationId;
        if(!Objects.isNotNull(applicationId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        Optional<Application> application = simpleDBService.findOneApplicationByApplicationId(applicationId);
        Application applicationFound = application.get();
        if(!application.isPresent()){
            throw new AuthException(1044, config.getExceptionsMap().get(1044));
        }
        if(!applicationService.isGroupOwner(session, applicationFound.getActivityId())){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        applicationFound.setStatus(2);
        return new ResponseEntity(simpleDBService.saveApplication(applicationFound), HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定活动的所有申请", notes = "传入活动Id(activityId),成功时返回状态200并且返回所有该活动信息,失败时返回状态以及错误信息")
    @RequestMapping(value = "/getAllApp", method = RequestMethod.POST)
    public ResponseEntity getAllApplicationByActivityId(@RequestBody OnlyIdData data, HttpSession session) throws BaseException{
        String acitivityId = data.activityId;
        if (Objects.isNull(acitivityId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!applicationService.isGroupOwner(session, acitivityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        return new ResponseEntity(simpleDBService.findApplicationByActivityId(acitivityId), HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定活动的所有指定状态的申请", notes = "传入活动Id(activityId),申请状态(status),成功时返回状态200,失败时返回状态以及错误信息")
    @RequestMapping(value = "/getAllAppWithStatus", method = RequestMethod.POST)
    public ResponseEntity getAllApplicationByActivityIdAndStatus(@RequestBody ActivityIdAndStatusData data, HttpSession session) throws BaseException{
        String acitivityId = data.activityId;
        int status = data.status;
        if (Objects.isNull(acitivityId)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!applicationService.isGroupOwner(session, acitivityId)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        return new ResponseEntity(simpleDBService.findApplicationByActivityIdAndStatus(acitivityId, status), HttpStatus.OK);
    }

}

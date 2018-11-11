package com.volunteer.commonweal.controllers.homePageController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "组织加入申请接口信息")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

//  组织建立请求相关接口

    //申请发送
    @ApiOperation(value = "发送组织建立申请", notes = "")
    @RequestMapping(value = "/foundation/apply", method = RequestMethod.POST)
    public ResponseEntity applyOrganizationFoundation(){

    }

    //同意申请(同意后即创建组织)
    @ApiOperation(value = "同意组织申请", notes = "")
    @RequestMapping(value = "/foundation/agree", method = RequestMethod.POST)
    public ResponseEntity agreeOrganizationFoundation(){

    }

    //拒绝申请
    @ApiOperation(value = "拒绝组织申请", notes = "")
    @RequestMapping(value = "/foundation/agree", method = RequestMethod.POST)
    public ResponseEntity agreeOrganizationFoundation(){

    }

    //获取指定组织的申请
    @ApiOperation(value = "获取指定组织的所有申请", notes = "")
    @RequestMapping(value = "/foundation/", method = RequestMethod.POST)
    public ResponseEntity getAllFoundationWithOrganization(){

    }

    //获取指定状态的所有申请
    @ApiOperation(value = "获取指定状态的所有申请", notes = "")
    @RequestMapping(value = "/foundation/getAllAppWithStatus", method = RequestMethod.POST)
    public ResponseEntity agreeOrganizationApply(){

    }


//  组织信息相关接口

    //获取单个组织信息
    @ApiOperation(value = "获取单个组织的信息", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrganizationMessage(){

    }

    //获取所有组织信息
    @ApiOperation(value = "获取所有组织的信息", notes = "")
    @RequestMapping(value = "/getAllMsg", method = RequestMethod.GET)
    public ResponseEntity getAllOrganizationMessage(){

    }

    //修改组织信息(权限是什么???) 包不包括删除组织成员
    @ApiOperation(value = "修改组织的信息", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrganization(){

    }

    //删除组织(权限是什么???)
    @ApiOperation(value = "删除单个组织", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrganization(){

    }

//  申请加入组织相关接口

    //申请发送
    @ApiOperation(value = "申请加入组织", notes = "")
    @RequestMapping(value = "/join/apply", method = RequestMethod.POST)
    public ResponseEntity applyOrganizationApply(){

    }

    //申请通过
    @ApiOperation(value = "通过加入组织申请", notes = "")
    @RequestMapping(value = "/join/agree", method = RequestMethod.POST)
    public ResponseEntity agreeOrganizationApply(){

    }

    //申请拒绝
    @ApiOperation(value = "拒绝加入组织申请", notes = "")
    @RequestMapping(value = "/join/refuse", method = RequestMethod.POST)
    public ResponseEntity refuseOrganizationApply(){

    }


    //退出组织
    @ApiOperation(value = "退出组织", notes = "")
    @RequestMapping(value = "/apply/quit", method = RequestMethod.POST)
    public ResponseEntity quitOrganization(){

    }

    //查看单条申请信息
    @ApiOperation(value = "查看单条申请信息", notes = "")
    @RequestMapping(value = "/apply/", method = RequestMethod.GET)
    public ResponseEntity getOrganizationApplyMsg(){

    }

    //查看多条申请信息(通过组织Id进行查看)
    @ApiOperation(value = "查看多条申请信息", notes = "")
    @RequestMapping(value = "/apply/getAllAppWithId", method = RequestMethod.GET)
    public ResponseEntity getAllOrganizationApplyMsgWithOrganizationId(){

    }


}

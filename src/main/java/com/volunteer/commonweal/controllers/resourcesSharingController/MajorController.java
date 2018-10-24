package com.volunteer.commonweal.controllers.resourcesSharingController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ManageMentConst;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.MajorConfigData;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.QuestionConfigData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Major;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Question;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.MajorRequestModel.MajorGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.MajorRequestModel.MajorModifyData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.QuestionRequestModel.QuestionGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.QuestionRequestModel.QuestionModifyData;
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
import java.util.Optional;

@Api(description = "学科介绍接口信息")
@RestController
@RequestMapping(value = "/subject/subject")
public class MajorController {
    SimpleDBService simpleDBService;
    UserService userService;
    Config config;

    @Autowired
    public MajorController(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }

    @ApiOperation(value = "获取所有Major信息", notes = "")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity managementMajorGetAll(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        MajorConfigData data = new MajorConfigData();
        data.data = simpleDBService.findAllMajor();
        data.total = data.data.size();
        data.perPage = ManageMentConst.PERPAGE_COUNT;
        data.page = (int) Math.ceil((double)data.total/(double)data.perPage);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @ApiOperation(value = "获取Major的grid", notes = "")
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ResponseEntity managementMajorGetGrid(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  searchModel: {},\n" +
                "  searchFields: {\n" +
                "  },\n" +
                "  fields: {\n" +
                "    id: {cols: 3},\n" +
                "  name:{label: '学校名称', cols: 3 },\n" +
                "  type:{label: '学校类别', cols: 3 },\n" +
                "  content: {label: '内容', type: 'html', listable: false, cols: 6 },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' },\n" +
                "  major:{label: '专业', cols: 3 }\n" +
                "  }\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取Major的view", notes = "")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity managementMajorGetView(HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "    id: {cols: 3},\n" +
                "  name:{label: '学校名称', cols: 3 },\n" +
                "  type:{label: '学校类别', cols: 3 },\n" +
                "  content: {label: '内容', type: 'html', listable: false, cols: 6 },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' },\n" +
                "  major:{label: '专业', cols: 3 }\n" +
                "  }";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定id的Major", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity managementMajorGetCollegeById(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Major> major = simpleDBService.findOneMajorByMajorId(id);
        if(!major.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        return new ResponseEntity(major.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "更新指定id的Major", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity managementMajorUpdateCollegeById(@PathVariable String id, @RequestBody MajorModifyData data, HttpSession session) throws AuthException{
        String majorId = data.id;
        String name = data.name;
        String type = data.type;
        String major = data.major;
        String content = data.content;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(major, name, type, major, content)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Optional<Major> majorFound = simpleDBService.findOneMajorByMajorId(id);
        if(!majorFound.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        Major major1 = majorFound.get();
//        String oldName = experience.getName();
//        Optional<College> collegeFound2 = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound2.isPresent() && !(oldName == name)){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        major1.setName(name);
        major1.setType(type);
        major1.setMajor(major);
        major1.setContent(content);
        major1.updateUploadTime();
        return new ResponseEntity(simpleDBService.saveMajor(major1), HttpStatus.OK);
    }

    @ApiOperation(value = "创建新的的Major", notes = "")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity managementMajorGenerate(@RequestBody MajorGenerateData data, HttpSession session) throws AuthException {
        String name = data.name;
        String type = data.type;
        String major = data.major;
        String content = data.content;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(name, type, major, content)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
//        Optional<Experience> collegeFound = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound.isPresent()){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        Major major1 = new Major();
        major1.setName(name);
        major1.setType(type);
        major1.setMajor(major);
        major1.setContent(content);
        major1.updateUploadTime();
        return new ResponseEntity(simpleDBService.insertMajor(major1), HttpStatus.OK);
    }

    @ApiOperation(value = "删除指定id的Major", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity managementMajorDelete(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteOneMajorByMajorId(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

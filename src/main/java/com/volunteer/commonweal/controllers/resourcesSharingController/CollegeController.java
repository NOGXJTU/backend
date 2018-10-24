package com.volunteer.commonweal.controllers.resourcesSharingController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ManageMentConst;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.CollegeConfigData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.College;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeModifyData;
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

@Api(description = "大学介绍接口信息")
@RestController
@RequestMapping(value = "/school/school")
public class CollegeController {
    SimpleDBService simpleDBService;
    UserService userService;
    Config config;

    @Autowired
    public CollegeController(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }

    @ApiOperation(value = "获取所有college信息", notes = "")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity managementCollegeGetAll(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        CollegeConfigData data = new CollegeConfigData();
        data.data = simpleDBService.findAllCollege();
        data.total = data.data.size();
        data.perPage = ManageMentConst.PERPAGE_COUNT;
        data.page = (int) Math.ceil((double)data.total/(double)data.perPage);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @ApiOperation(value = "获取college的grid", notes = "")
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ResponseEntity managementCollegeGetGrid(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  searchModel: {},\n" +
                "  searchFields: {\n" +
                "  },\n" +
                "  fields: {\n" +
                "  id: {cols: 3},\n" +
                "  name:{label: '学校名称', cols: 3 },\n" +
                "  type:{label: '学校类别', cols: 3 },\n" +
                "  content: {label: '内容', type: 'html', listable: false, cols: 6 },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' }\n" +
                "  }\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取college的view", notes = "")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity managementCollegeGetView(HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  id: {cols: 3},\n" +
                "  name:{label: '学校名称', cols: 3 },\n" +
                "  type:{label: '学校类别', cols: 3 },\n" +
                "  content: {label: '内容', type: 'html', listable: false, cols: 6 },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' }\n" +
                "  }";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定id的college", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity managementCollegeGetCollegeById(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<College> college = simpleDBService.findOneCollegeByCollegeId(id);
        if(!college.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        return new ResponseEntity(college.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "更新指定id的college", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity managementCollegeUpdateCollegeById(@PathVariable String id, @RequestBody CollegeModifyData data, HttpSession session) throws AuthException{
        String collegeId = data.id;
        String name = data.name;
        String type = data.type;
        String content = data.content;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(collegeId, name, type, content)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Optional<College> collegeFound = simpleDBService.findOneCollegeByCollegeId(id);
        if(!collegeFound.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        College college = collegeFound.get();
        String oldName = college.getName();
        Optional<College> collegeFound2 = simpleDBService.findOneCollegeByName(name);
        if(collegeFound2.isPresent() && !(oldName == name)){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        college.setName(name);
        college.setType(type);
        college.setContent(content);
        college.updateUploadTime();
        return new ResponseEntity(simpleDBService.saveCollege(college), HttpStatus.OK);
    }

    @ApiOperation(value = "创建新的的college", notes = "")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity managementCollegeGenerate(@RequestBody CollegeGenerateData data, HttpSession session) throws AuthException {
        String name = data.name;
        String type = data.type;
        String content = data.content;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(name, type, content)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Optional<College> collegeFound = simpleDBService.findOneCollegeByName(name);
        if(collegeFound.isPresent()){
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        College college = new College();
        college.setContent(content);
        college.setName(name);
        college.setType(type);
        college.updateUploadTime();
        return new ResponseEntity(simpleDBService.insertCollege(college), HttpStatus.OK);
    }

    @ApiOperation(value = "删除指定id的college", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity managementCollegeDelete(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteOneCollegeByCollegeId(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}

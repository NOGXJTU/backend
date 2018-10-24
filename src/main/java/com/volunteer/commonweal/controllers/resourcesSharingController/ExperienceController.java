package com.volunteer.commonweal.controllers.resourcesSharingController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ManageMentConst;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.CollegeConfigData;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.ExperienceConfigData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.College;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Experience;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.CollegeRequestModel.CollegeModifyData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.ExperienceRequestModel.ExperienceGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.ExperienceRequestModel.ExperienceModifyData;
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

@Api(description = "学习经验接口信息")
@RestController
@RequestMapping(value = "/experience/experience")
public class ExperienceController {
    SimpleDBService simpleDBService;
    UserService userService;
    Config config;

    @Autowired
    public ExperienceController(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }

    @ApiOperation(value = "获取所有experience信息", notes = "")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity managementExperienceGetAll(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        ExperienceConfigData data = new ExperienceConfigData();
        data.data = simpleDBService.findAllExperience();
        data.total = data.data.size();
        data.perPage = ManageMentConst.PERPAGE_COUNT;
        data.page = (int) Math.ceil((double)data.total/(double)data.perPage);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @ApiOperation(value = "获取Experience的grid", notes = "")
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ResponseEntity managementExperienceGetGrid(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  searchModel: {},\n" +
                "  searchFields: {\n" +
                "  },\n" +
                "  fields: {\n" +
                "  id: {cols: 3},\n" +
                "  title:{label: '标题', cols: 3 },\n" +
                "  content: {label: '内容', type: 'html', listable: false, cols: 6 },\n" +
                "  contributor: {label: '分享人', cols: 3 },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' }\n" +
                "  }\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取Experience的view", notes = "")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity managementExperienceGetView(HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  id: {cols: 3},\n" +
                "  title:{label: '标题', cols: 3 },\n" +
                "  content: {label: '内容', type: 'html', listable: false, cols: 6 },\n" +
                "  contributor: {label: '分享人', cols: 3 },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' }\n" +
                "  }";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定id的Experience", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity managementExperienceGetCollegeById(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Experience> experience = simpleDBService.findOneExperienceByExperienceId(id);
        if(!experience.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        return new ResponseEntity(experience.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "更新指定id的Experience", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity managementExperienceUpdateCollegeById(@PathVariable String id, @RequestBody ExperienceModifyData data, HttpSession session) throws AuthException{
        String experienceId = data.id;
        String title = data.title;
        String contributor = data.contributor;
        String content = data.content;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(experienceId, title, contributor, content)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
//        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
//            throw new AuthException(1014, config.getExceptionsMap().get(1014));
//        }
        Optional<Experience> experienceFound = simpleDBService.findOneExperienceByExperienceId(id);
        if(!experienceFound.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        Experience experience = experienceFound.get();
//        String oldName = experience.getName();
//        Optional<College> collegeFound2 = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound2.isPresent() && !(oldName == name)){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        experience.setTitle(title);
        experience.setContributor(contributor);
        experience.setContent(content);
        experience.updateUploadTime();
        return new ResponseEntity(simpleDBService.saveExperience(experience), HttpStatus.OK);
    }

    @ApiOperation(value = "创建新的的Experience", notes = "")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity managementExperienceGenerate(@RequestBody ExperienceGenerateData data, HttpSession session) throws AuthException {
        String title = data.title;
        String contributor = data.contributor;
        String content = data.content;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(title, contributor, content)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
//        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
//            throw new AuthException(1014, config.getExceptionsMap().get(1014));
//        }
//        Optional<Experience> collegeFound = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound.isPresent()){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        Experience experience = new Experience();
        experience.setContent(content);
        experience.setTitle(title);
        experience.setContributor(contributor);
        experience.updateUploadTime();
        return new ResponseEntity(simpleDBService.insertExperience(experience), HttpStatus.OK);
    }

    @ApiOperation(value = "删除指定id的Experience", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity managementExperienceDelete(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteOneExperienceByExperienceId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

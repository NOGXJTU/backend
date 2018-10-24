package com.volunteer.commonweal.controllers.resourcesSharingController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ManageMentConst;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.GuideConfigData;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.KnowledgeConfigData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Guide;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Knowledge;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.GuideRequestModel.GuideGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.GuideRequestModel.GuideModifyData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.KnowledgeRequestModel.KnowledgeGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.KnowledgeRequestModel.KnowledgeModifyData;
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

@Api(description = "知识点接口信息")
@RestController
@RequestMapping(value = "/point/point")
public class KnowledgeController {
    SimpleDBService simpleDBService;
    UserService userService;
    Config config;

    @Autowired
    public KnowledgeController(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }

    @ApiOperation(value = "获取所有Knowledge信息", notes = "")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity managementKnowledgeGetAll(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        KnowledgeConfigData data = new KnowledgeConfigData();
        data.data = simpleDBService.findAllKnowledge();
        data.total = data.data.size();
        data.perPage = ManageMentConst.PERPAGE_COUNT;
        data.page = (int) Math.ceil((double)data.total/(double)data.perPage);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @ApiOperation(value = "获取Knowledge的grid", notes = "")
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ResponseEntity managementKnowledgeGetGrid(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  searchModel: {},\n" +
                "  searchFields: {\n" +
                "   name: {label: '名称', cols: 6, searchable: true },\n" +
                "   subject: {label: '学科', cols: 3, searchable: true }\n" +
                "  },\n" +
                "  fields: {\n" +
                "  id: {cols: 3},\n" +
                "  file: {label: '文件', type: 'file', size: 10485760},\n" +
                "  name: {label: '名称', cols: 6, searchable: true },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' },\n" +
                "  grade: {label: '年级', type: 'tree',options: [\n" +
                "  {text:'一年级',value:'一年级'},\n" +
                "  {text:'二年级',value:'二年级'},\n" +
                "  {text:'三年级',value:'三年级'},\n" +
                "  {text:'四年级',value:'四年级'},\n" +
                "  {text:'五年级',value:'五年级'},\n" +
                "  {text:'六年级',value:'六年级'},\n" +
                "  {text:'七年级',value:'七年级'},\n" +
                "  {text:'八年级',value:'八年级'},\n" +
                "  {text:'九年级',value:'九年级'},\n" +
                "  {text:'十年级',value:'十年级'},\n" +
                "  {text:'十一年级',value:'十一年级'},\n" +
                "  {text:'十二年级',value:'十二年级'}\n" +
                "] ,cols: 3, sortable: true },\n" +
                "  subject: {label: '学科', cols: 3, searchable: true },\n" +
                "  description: {label: '描述', type: 'html', listable: false, cols: 6 },\n" +
                "  contributor: {label: '上传人', cols: 3 }\n" +
                "  }\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取Knowledge的view", notes = "")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity managementKnowledgeGetView(HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  id: {cols: 3},\n" +
                "  file: {label: '文件', type: 'file', size: 10485760 },\n" +
                "  name: {label: '名称', cols: 6, searchable: true },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' },\n" +
                "  grade: {label: '年级', type: 'tree',options: [\n" +
                "  {text:'一年级',value:'一年级'},\n" +
                "  {text:'二年级',value:'二年级'},\n" +
                "  {text:'三年级',value:'三年级'},\n" +
                "  {text:'四年级',value:'四年级'},\n" +
                "  {text:'五年级',value:'五年级'},\n" +
                "  {text:'六年级',value:'六年级'},\n" +
                "  {text:'七年级',value:'七年级'},\n" +
                "  {text:'八年级',value:'八年级'},\n" +
                "  {text:'九年级',value:'九年级'},\n" +
                "  {text:'十年级',value:'十年级'},\n" +
                "  {text:'十一年级',value:'十一年级'},\n" +
                "  {text:'十二年级',value:'十二年级'}\n" +
                "] ,cols: 3, sortable: true },\n" +
                "  subject: {label: '学科', cols: 3, searchable: true },\n" +
                "  description: {label: '描述', type: 'html', listable: false, cols: 6 },\n" +
                "  contributor: {label: '上传人', cols: 3 },\n" +
                "  }";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定id的Knowledge", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity managementKnowledgeGetCollegeById(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<Knowledge> knowledge = simpleDBService.findOneKnowledgeByKnowledgeId(id);
        if(!knowledge.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        return new ResponseEntity(knowledge.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "更新指定id的Knowledge", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity managementKnowledgeUpdateCollegeById(@PathVariable String id, @RequestBody KnowledgeModifyData data, HttpSession session) throws AuthException{
        String knowledgeId = data.id;
        String name = data.name;
        String file = data.file;
        String grade = data.grade;
        String subject = data.subject;
        String description = data.description;
        String contributor = data.contributor;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(knowledgeId, name, grade, subject, description, contributor)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
//        if(!ParamConstraintUtils.isCollegeTypeValid(type)){
//            throw new AuthException(1014, config.getExceptionsMap().get(1014));
//        }
        if(!ParamConstraintUtils.isSubjectValid(grade, subject)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Optional<Knowledge> knowledgeFound = simpleDBService.findOneKnowledgeByKnowledgeId(id);
        if(!knowledgeFound.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        Knowledge knowledge = knowledgeFound.get();
//        String oldName = experience.getName();
//        Optional<College> collegeFound2 = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound2.isPresent() && !(oldName == name)){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        knowledge.setName(name);
        knowledge.setFile(file);
        knowledge.setGrade(grade);
        knowledge.setSubject(subject);
        knowledge.setDescription(description);
        knowledge.setContributor(contributor);
        knowledge.updateUploadTime();
        return new ResponseEntity(simpleDBService.saveKnowledge(knowledge), HttpStatus.OK);
    }

    @ApiOperation(value = "创建新的的Knowledge", notes = "")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity managementKnowledgeGenerate(@RequestBody KnowledgeGenerateData data, HttpSession session) throws AuthException {
        String name = data.name;
        String file = data.file;
        String grade = data.grade;
        String subject = data.subject;
        String description = data.description;
        String contributor = data.contributor;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(name, grade, subject, description, contributor)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isSubjectValid(grade, subject)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
//        Optional<Experience> collegeFound = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound.isPresent()){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        Knowledge knowledge = new Knowledge();
        knowledge.setName(name);
        knowledge.setFile(file);
        knowledge.setGrade(grade);
        knowledge.setSubject(subject);
        knowledge.setDescription(description);
        knowledge.setContributor(contributor);
        knowledge.updateUploadTime();
        return new ResponseEntity(simpleDBService.insertKnowledge(knowledge), HttpStatus.OK);
    }

    @ApiOperation(value = "删除指定id的Knowledge", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity managementKnowledgeDelete(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteOneKnowledgeByKnowledgeId(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

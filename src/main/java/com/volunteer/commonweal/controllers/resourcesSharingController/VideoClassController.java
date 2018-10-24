package com.volunteer.commonweal.controllers.resourcesSharingController;

import com.volunteer.commonweal.common.Objects;
import com.volunteer.commonweal.common.ParamConstraintUtils;
import com.volunteer.commonweal.constants.ManageMentConst;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.MajorConfigData;
import com.volunteer.commonweal.models.ResponseModels.ManagementModel.VideoClassConfigData;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Major;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.VideoClass;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.MajorRequestModel.MajorGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.MajorRequestModel.MajorModifyData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.VideoClassRequestModel.VideoClassGenerateData;
import com.volunteer.commonweal.models.requestModels.resourcesCharingRequestModels.VideoClassRequestModel.VideoClassModifyData;
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

@Api(description = "典型例题接口信息")
@RestController
@RequestMapping(value = "/example/example")
public class VideoClassController {
    SimpleDBService simpleDBService;
    UserService userService;
    Config config;

    @Autowired
    public VideoClassController(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }

    @ApiOperation(value = "获取所有VideoClass信息", notes = "")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity managementVideoClassGetAll(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        VideoClassConfigData data = new VideoClassConfigData();
        data.data = simpleDBService.findAllVideoClass();
        data.total = data.data.size();
        data.perPage = ManageMentConst.PERPAGE_COUNT;
        data.page = (int) Math.ceil((double)data.total/(double)data.perPage);
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @ApiOperation(value = "获取VideoClass的grid", notes = "")
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ResponseEntity managementVideoClassGetGrid(HttpSession session) throws AuthException {
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "  searchModel: {},\n" +
                "  searchFields: {\n" +
                "  },\n" +
                "  fields: {\n" +
                "    id: {cols: 3},\n" +
                "  file: {label: '文件', type: 'file', size: 104857600},\n" +
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
                "], cols: 3, sortable: true },\n" +
                "  subject: {label: '学科', cols: 3, searchable: true },\n" +
                "  description: {label: '描述', type: 'html', listable: false, cols: 6 },\n" +
                "  teacher: {label: '讲解人', cols: 3 },\n" +
                "  image: {label: '图片', type: 'image', cols: 6 }\n" +
                "}\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取VideoClass的view", notes = "")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity managementVideoClassGetView(HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        String returnString = "{\n" +
                "    id: {cols: 3},\n" +
                "  file: {label: '文件', type: 'file', size: 104857600},\n" +
                "  name: {label: '名称', cols: 6, searchable: true },\n" +
                "  uploadTime: { label: '上传日期', type: 'date' },\n" +
                "  grade: {label: '年级', type: 'tree', options: [\n" +
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
                "], cols: 3, sortable: true },\n" +
                "  subject: {label: '学科', cols: 3, searchable: true },\n" +
                "  description: {label: '描述', type: 'html', listable: false, cols: 6 },\n" +
                "  teacher: {label: '讲解人', cols: 3 },\n" +
                "  image: {label: '图片', type: 'image', cols: 6 }\n" +
                "}";
        return new ResponseEntity(returnString, HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定id的VideoClass", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity managementVideoClassGetCollegeById(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        Optional<VideoClass> videoClass = simpleDBService.findOneVideoClassByVideoClassId(id);
        if(!videoClass.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        return new ResponseEntity(videoClass.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "更新指定id的VideoClass", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity managementVideoClassUpdateCollegeById(@PathVariable String id, @RequestBody VideoClassModifyData data, HttpSession session) throws AuthException{
        String videoClassId = data.id;
        String name = data.name;
        String file = data.file;
        String image = data.image;
        String grade = data.grade;
        String subject = data.subject;
        String description = data.description;
        String teacher = data.teacher;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(videoClassId, name, image, grade, subject, description, teacher)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isGradeValid(grade) || !ParamConstraintUtils.isSubjectValid(grade, subject)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Optional<VideoClass> videoClassFound = simpleDBService.findOneVideoClassByVideoClassId(id);
        if(!videoClassFound.isPresent()){
            throw new AuthException(1047, config.getExceptionsMap().get(1047));
        }
        VideoClass videoClass = videoClassFound.get();
//        String oldName = experience.getName();
//        Optional<College> collegeFound2 = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound2.isPresent() && !(oldName == name)){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        videoClass.setName(name);
        videoClass.setFile(file);
        videoClass.setImage(image);
        videoClass.setGrade(grade);
        videoClass.setSubject(subject);
        videoClass.setDescription(description);
        videoClass.setTeacher(teacher);
        videoClass.updateUploadTime();
        return new ResponseEntity(simpleDBService.saveVideoClass(videoClass), HttpStatus.OK);
    }

    @ApiOperation(value = "创建新的的VideoClass", notes = "")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity managementVideoClassGenerate(@RequestBody VideoClassGenerateData data, HttpSession session) throws AuthException {
        String name = data.name;
        String file = data.file;
        String image = data.image;
        String grade = data.grade;
        String subject = data.subject;
        String description = data.description;
        String teacher = data.teacher;
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        if(!Objects.isNotNull(name, image, grade, subject, description, teacher)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!ParamConstraintUtils.isGradeValid(grade) || !ParamConstraintUtils.isSubjectValid(grade, subject)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
//        Optional<Experience> collegeFound = simpleDBService.findOneCollegeByName(name);
//        if(collegeFound.isPresent()){
//            throw new AuthException(105, config.getExceptionsMap().get(105));
//        }
        VideoClass videoClass = new VideoClass();
        videoClass.setName(name);
        videoClass.setFile(file);
        videoClass.setImage(image);
        videoClass.setGrade(grade);
        videoClass.setSubject(subject);
        videoClass.setDescription(description);
        videoClass.setTeacher(teacher);
        videoClass.updateUploadTime();
        return new ResponseEntity(simpleDBService.insertVideoClass(videoClass), HttpStatus.OK);
    }

    @ApiOperation(value = "删除指定id的VideoClass", notes = "")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity managementVideoClassDelete(@PathVariable String id, HttpSession session) throws AuthException{
        if(!userService.isSuperUser(session)){
            throw new AuthException(102, config.getExceptionsMap().get(102));
        }
        simpleDBService.deleteOneVideoClassByVideoClassId(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

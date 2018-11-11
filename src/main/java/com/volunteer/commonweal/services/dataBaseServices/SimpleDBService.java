package com.volunteer.commonweal.services.dataBaseServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.*;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.*;
import com.volunteer.commonweal.repositories.homePageRepositories.*;
import com.volunteer.commonweal.repositories.resourcesSharingServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

// 抽离出来的直接调用数据库操作接口Repository的操作
@Service
public class SimpleDBService {
    private ActivityRepository activityRepository;
    private ApplicationRepository applicationRepository;
    private EmailVerifyTokenRepository emailVerifyTokenRepository;
    private UserRepository userRepository;
    private CollegeRepository collegeRepository;
    private ExperienceRepository experienceRepository;
    private GuideRepository guideRepository;
    private KnowledgeRepository knowledgeRepository;
    private MajorRepository majorRepository;
    private QuestionRepository questionRepository;
    private VideoClassRepository videoClassRepository;
    private OrganizationRepository organizationRepository;
    private OrganizationApplyRepository organizationApplyRepository;
    private OrganizationFoundationRepository organizationFoundationRepository;
    private ActivityApplyRepository activityApplyRepository;

    @Autowired
    public SimpleDBService(ActivityRepository activityRepository, ApplicationRepository applicationRepository, EmailVerifyTokenRepository emailVerifyTokenRepository, UserRepository userRepository
        , CollegeRepository collegeRepository, ExperienceRepository experienceRepository, GuideRepository guideRepository, KnowledgeRepository knowledgeRepository, MajorRepository majorRepository
        , QuestionRepository questionRepository, VideoClassRepository videoClassRepository, OrganizationRepository organizationRepository, OrganizationApplyRepository organizationApplyRepository
        , OrganizationFoundationRepository organizationFoundationRepository, ActivityApplyRepository activityApplyRepository){
        this.activityRepository = activityRepository;
        this.applicationRepository = applicationRepository;
        this.emailVerifyTokenRepository = emailVerifyTokenRepository;
        this.userRepository = userRepository;
        this.collegeRepository = collegeRepository;
        this.experienceRepository = experienceRepository;
        this.guideRepository = guideRepository;
        this.knowledgeRepository = knowledgeRepository;
        this.majorRepository = majorRepository;
        this.questionRepository = questionRepository;
        this.videoClassRepository = videoClassRepository;
        this.organizationRepository = organizationRepository;
        this.organizationApplyRepository = organizationApplyRepository;
        this.organizationFoundationRepository = organizationFoundationRepository;
        this.activityApplyRepository = activityApplyRepository;
    }

    //activity的操作
    public Optional<Activity> findOneActivityByActivityId(String id){
        return activityRepository.findOneById(id);
    }
    public Page<Activity> findAllActivityByFinished(boolean finished, Pageable pageable){
        return activityRepository.findAllByFinished(finished, pageable);
    }
    public Stream<Activity> findAllActivityByFinished(boolean finished){
        return activityRepository.findAllByFinished(finished);
    }
    public Optional<Activity> findOneActivityByNameAndBeginTime(String name, String beginTime){
        return activityRepository.findOneByNameAndBeginTime(name, beginTime);
    }
    public Activity insertActivity(Activity activity){
        return activityRepository.insert(activity);
    }
    public void deleteActivityByActivityId(String id){
        activityRepository.deleteById(id);
    }
    public Activity saveActivity(Activity activity){
        return activityRepository.save(activity);
    }
    public Stream<Activity> findAllActivityByShowAndFinished(boolean show, boolean finished){
        return activityRepository.findAllByShowAndFinished(show, finished);
    }
    public List<Activity> findAllActivity(){
        return activityRepository.findAll();
    }
    public Stream<Activity> findActivityByOrganizationId(String organizationId){
        return activityRepository.findByOrganizationId(organizationId);
    }

    //application的操作
    public Optional<Application> findOneApplicationByApplicationId(String applicationId){
        return applicationRepository.findOneById(applicationId);
    }
    public Stream<Application> findApplicationByUserId(String userId){
        return applicationRepository.findByUserId(userId);
    }
    public Stream<Application> findApplicationByActivityId(String activityId){
        return applicationRepository.findByActivityId(activityId);
    }
    public Stream<Application> findApplicationByActivityIdAndStatus(String activityId,int status){
        return applicationRepository.findByActivityIdAndStatus(activityId, status);
    }
    public Application insertApplication(Application application){
        return applicationRepository.insert(application);
    }
    public Application saveApplication(Application application){
        return applicationRepository.save(application);
    }
    public Optional<Application> findOneApplicationByUserIdAndActivityIdAndStatus(String userId, String activityId, int status){
        return applicationRepository.findOneByUserIdAndActivityIdAndStatus(userId, activityId, status);
    }

    //emailverifytoken的操作
    public Optional<EmailVerifyToken> findEmailVerifyTokenByEmail(String email){
        return emailVerifyTokenRepository.findOneByEmail(email);
    }
    public EmailVerifyToken insertEmailVerifyToken(EmailVerifyToken emailVerifyToken){
        return emailVerifyTokenRepository.insert(emailVerifyToken);
    }
    public EmailVerifyToken saveEmailVerifyToken(EmailVerifyToken emailVerifyToken){
        return emailVerifyTokenRepository.save(emailVerifyToken);
    }

    //user的操作
    public Optional<User> findOneUserByUsername(String username){
        return userRepository.findOneByUsername(username);
    }
    public Optional<User> findOneUserByEmail(String email){
        return userRepository.findOneByEmail(email);
    }
    public Optional<User> findOneUserByPhone(String phone){
        return userRepository.findOneByPhone(phone);
    }
    public Stream<User> findUserByIdIn(Collection<String> idGroup){
        return userRepository.findByIdIn(idGroup);
    }
    public Stream<User> saveUsers(Stream<User> userStream){
        return userRepository.save(userStream);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public Optional<User> findOneUserByUserId(String id){
        return userRepository.findById(id);
    }
    public User insertUser(User user){
        return userRepository.insert(user);
    }
    public Optional<User> findOneUserById(String userId){
        return userRepository.findById(userId);
    }
    public Stream<User> findAllUserByActivitiesIdContaining(String activityId){
        return userRepository.findByActivitiesIdContaining(activityId);
    }

    //college的操作
    public Optional<College> findOneCollegeByCollegeId(String id){
        return collegeRepository.findById(id);
    }
    public List<College> findAllCollege(){
        return collegeRepository.findAll();
    }
    public College saveCollege(College college){
        return collegeRepository.save(college);
    }
    public Optional<College> findOneCollegeByName(String name){
        return collegeRepository.findOneByName(name);
    }
    public void deleteOneCollegeByCollegeId(String id){
        collegeRepository.deleteById(id);
    }
    public College insertCollege(College college) {
        return collegeRepository.insert(college);
    }

    //Experience的操作
    public Optional<Experience> findOneExperienceByExperienceId(String id){
        return experienceRepository.findById(id);
    }
    public List<Experience> findAllExperience(){
        return experienceRepository.findAll();
    }
    public Experience saveExperience(Experience experience){
        return experienceRepository.save(experience);
    }
    public void deleteOneExperienceByExperienceId(String id){
        experienceRepository.deleteById(id);
    }
    public Experience insertExperience(Experience experience){
        return experienceRepository.insert(experience);
    }

    //Guide的操作
    public Optional<Guide> findOneGuideByGuideId(String id){
        return guideRepository.findById(id);
    }
    public List<Guide> findAllGuide(){
        return guideRepository.findAll();
    }
    public Guide saveGuide(Guide guide){
        return guideRepository.save(guide);
    }
    public void deleteOneGuideByGuideId(String id){
        guideRepository.deleteById(id);
    }
    public Guide insertGuide(Guide guide){
        return guideRepository.insert(guide);
    }

    //Knowledge的操作
    public Optional<Knowledge> findOneKnowledgeByKnowledgeId(String id){
        return knowledgeRepository.findById(id);
    }
    public List<Knowledge> findAllKnowledge(){
        return knowledgeRepository.findAll();
    }
    public Knowledge saveKnowledge(Knowledge knowledge){
        return knowledgeRepository.save(knowledge);
    }
    public void deleteOneKnowledgeByKnowledgeId(String id){
        knowledgeRepository.deleteById(id);
    }
    public Knowledge insertKnowledge(Knowledge knowledge){
        return knowledgeRepository.insert(knowledge);
    }

    //Major的操作
    public Optional<Major> findOneMajorByMajorId(String id){
        return majorRepository.findById(id);
    }
    public List<Major> findAllMajor(){
        return majorRepository.findAll();
    }
    public Major saveMajor(Major major){
        return majorRepository.save(major);
    }
    public void deleteOneMajorByMajorId(String id){
        majorRepository.deleteById(id);
    }
    public Major insertMajor(Major major){
        return majorRepository.insert(major);
    }

    //Question的操作
    public Optional<Question> findOneQuestionByQuestionId(String id){
        return questionRepository.findById(id);
    }
    public List<Question> findAllQuestion(){
        return questionRepository.findAll();
    }
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }
    public void deleteOneQuestionByQuestionId(String id){
        questionRepository.deleteById(id);
    }
    public Question insertQuestion(Question question){
        return questionRepository.insert(question);
    }

    //VideoClass的操作
    public Optional<VideoClass> findOneVideoClassByVideoClassId(String id){
        return videoClassRepository.findById(id);
    }
    public List<VideoClass> findAllVideoClass(){
        return videoClassRepository.findAll();
    }
    public VideoClass saveVideoClass(VideoClass videoClass){
        return videoClassRepository.save(videoClass);
    }
    public void deleteOneVideoClassByVideoClassId(String id){
        videoClassRepository.deleteById(id);
    }
    public VideoClass insertVideoClass(VideoClass videoClass){
        return videoClassRepository.insert(videoClass);
    }

    //organization的操作
    public Optional<Organization> findOneOrganizationById(String id){
        return organizationRepository.findOneById(id);
    }
    public Optional<Organization> findOneOrganizationByName(String name){
        return organizationRepository.findOneByName(name);
    }
    public Stream<Organization> findOrganizationByLeaderId(String leaderId){
        return organizationRepository.findByLeaderId(leaderId);
    }
    public Organization insertOrganization(Organization organization){
        return organizationRepository.insert(organization);
    }
    public Organization saveOrganization(Organization organization){
        return organizationRepository.save(organization);
    }
    public List<Organization> findAllOrganization(){
        return organizationRepository.findAll();
    }
    public void deleteOneOrganizationById(String id){
        organizationRepository.deleteById(id);
    }

    //organizationApply的操作
    public Optional<OrganizationApply> findOneOrganizationApplyById(String id){
        return organizationApplyRepository.findOneById(id);
    }
    public Stream<OrganizationApply> findOrganizationApplyByOrganizationIdAndStatus(String organizationId, int status){
        return organizationApplyRepository.findByOrganizationIdAndStatus(organizationId, status);
    }
    public Stream<OrganizationApply> findOrganizationApplyByOrganizationId(String organizationId){
        return organizationApplyRepository.findByOrganizationId(organizationId);
    }
    public Optional<OrganizationApply> findOneOrganizationApplyByOrganizationIdAndUserIdAndStatus(String organizationId, String userId, int status){
        return organizationApplyRepository.findOneByOrganizationIdAndUserIdAndStatus(organizationId, userId, status);
    }
    public OrganizationApply saveOrganizationApply(OrganizationApply organizationApply){
        return organizationApplyRepository.save(organizationApply);
    }

    public OrganizationApply insertOrganizationApply(OrganizationApply organizationApply){
        return organizationApplyRepository.insert(organizationApply);
    }

    //organizationFoundation的操作
    public Optional<OrganizationFoundation> findOneOrganizationFoundationById(String id){
        return organizationFoundationRepository.findOneById(id);
    }
    public Stream<OrganizationFoundation> findOrganizationFoundationByLeaderId(String leaderId){
        return organizationFoundationRepository.findByLeaderId(leaderId);
    }
    public Optional<OrganizationFoundation> findOneOrganizationFoundationByName(String name){
        return organizationFoundationRepository.findOneByName(name);
    }
    public Stream<OrganizationFoundation> findOrganizationFoundationByStatus(int status){
        return organizationFoundationRepository.findByStatus(status);
    }
    public OrganizationFoundation saveOrganizationFoundation(OrganizationFoundation organizationFoundation){
        return organizationFoundationRepository.save(organizationFoundation);
    }
    public OrganizationFoundation insertOrganizationFoundation(OrganizationFoundation organizationFoundation){
        return organizationFoundationRepository.insert(organizationFoundation);
    }

    //activityApply的操作
    public Optional<ActivityApply> findOneActivityApplyById(String id){
        return activityApplyRepository.findOneById(id);
    }
    public Optional<ActivityApply> findOneActivityApplyByName(String name){
        return activityApplyRepository.findOneByName(name);
    }
    public Stream<ActivityApply> findActivityApplyByOwnerId(String ownerId){
        return activityApplyRepository.findByOwnerId(ownerId);
    }
    public ActivityApply saveActivityApply(ActivityApply activityApply){
        return activityApplyRepository.save(activityApply);
    }
    public ActivityApply insertActivityApply(ActivityApply activityApply){
        return activityApplyRepository.save(activityApply);
    }
}

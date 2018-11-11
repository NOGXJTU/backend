package com.volunteer.commonweal.services.dataBaseServices;


import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.homePageModels.Activity;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.repositories.homePageRepositories.ActivityRepository;
import com.volunteer.commonweal.repositories.homePageRepositories.ApplicationRepository;
import com.volunteer.commonweal.repositories.homePageRepositories.EmailVerifyTokenRepository;
import com.volunteer.commonweal.repositories.homePageRepositories.UserRepository;
import com.volunteer.commonweal.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UpdateDBService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    EmailVerifyTokenRepository emailVerifyTokenRepository;
    @Autowired
    Config config;

    public void removeUserOrganization(String userId, String organizationId) throws AuthException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        User userFound = user.get();
        List<String> organizationList = userFound.getOrganizations();
        organizationList.remove(organizationId);
        userRepository.save(userFound);
    }

    public void removeUserActivity(String userId, String activityId) throws AuthException{
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        User userFound = user.get();
        List<String> activityList = userFound.getActivitiesId();
        activityList.remove(activityId);
        userRepository.save(userFound);
    }
    public void removeUserGroupActivity(List<String> userIdGroup, String activityId) throws AuthException{
        Stream<User> userStream = userRepository.findByIdIn(userIdGroup);
//        DBObject command = new BasicDBObject();
//        command.put("update", );
        userStream.forEach(user -> {
            List<String> activityList = user.getActivitiesId();
            activityList.remove(activityId);
        });
        userRepository.save(userStream);
    }
    public void addUserActivity(String userId, String activityId) throws  AuthException{
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new AuthException(1031, config.getExceptionsMap().get(1031));
        }
        User userFound = user.get();
        List<String> activityList = userFound.getActivitiesId();
        if(!activityList.contains(activityId)){
            activityList.add(activityId);
            userRepository.save(userFound);
        }else {
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
        Optional<Activity> activityFound = activityRepository.findById(activityId);
        if(!activityFound.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        Activity activity = activityFound.get();
        List<String> userList = activity.getUsers();
        if(!userList.contains(userId)){
            userList.add(userId);
            activityRepository.save(activity);
        }else {
            throw new AuthException(105, config.getExceptionsMap().get(105));
        }
    }

}


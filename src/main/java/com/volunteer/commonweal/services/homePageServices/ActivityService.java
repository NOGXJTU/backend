package com.volunteer.commonweal.services.homePageServices;

import com.volunteer.commonweal.constants.UIConst;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.homePageModels.Activity;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class ActivityService {
    private SimpleDBService simpleDBService;
    private UserService userService;
    private Config config;

    @Autowired
    public ActivityService(SimpleDBService simpleDBService, UserService userService, Config config){
        this.simpleDBService = simpleDBService;
        this.userService = userService;
        this.config = config;
    }

    public boolean isSuperUser(HttpSession session){
        return userService.isSuperUser(session);
    }

    public boolean isGroupOwner(HttpSession session, String activityId) throws AuthException {
        Optional<String> userId = userService.getUserIdFromSession(session);
        if(!userId.isPresent()){
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
        Optional<Activity> activity = simpleDBService.findOneActivityByActivityId(activityId);
        if(!activity.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1043));
        }
        return activity.get().getOwnerId().equals(userId.get());
    }

    public boolean isGroupOwner(HttpSession session, Activity activity) throws AuthException{
        Optional<String> userId = userService.getUserIdFromSession(session);
        if(!userId.isPresent()){
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
        return activity.getOwnerId().equals(userId.get());
    }
    public boolean isActivityDuplicate(String name, String beginTime){
        return simpleDBService.findOneActivityByNameAndBeginTime(name, beginTime).isPresent();
    }

    public Optional<String> getUserIdFromSession(HttpSession session){
        return Optional.ofNullable((String)session.getAttribute(UIConst.SESSION_USER_ID));
    }

}

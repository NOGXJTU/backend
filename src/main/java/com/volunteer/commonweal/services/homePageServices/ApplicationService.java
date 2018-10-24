package com.volunteer.commonweal.services.homePageServices;

import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class ApplicationService {
    ActivityService activityService;
    private UserService userService;
    private Config config;

    @Autowired
    public ApplicationService(ActivityService activityRepository, UserService userService, Config config){
        this.activityService = activityRepository;
        this.userService = userService;
        this.config = config;
    }

    public boolean isGroupOwner(HttpSession session, String activityId) throws AuthException{
        return activityService.isGroupOwner(session, activityId);
    }

    public Optional<String> getUserIdFromSession(HttpSession session){
        return userService.getUserIdFromSession(session);
    }

}

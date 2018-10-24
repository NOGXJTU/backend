package com.volunteer.commonweal.services.homePageServices;

import com.volunteer.commonweal.constants.UIConst;
import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
public class UserService {
    private SimpleDBService simpleDBService;
    private Config config;
    private TokenVertifyService tokenVertifyService;

    @Autowired
    public UserService(SimpleDBService simpleDBService,
                       TokenVertifyService tokenVertifyService,
                       Config config) {
        this.simpleDBService = simpleDBService;
        this.tokenVertifyService = tokenVertifyService;
        this.config = config;
    }

    public Optional<String> getUserIdFromSession(HttpSession session){
        return Optional.ofNullable((String)session.getAttribute(UIConst.SESSION_USER_ID));
    }

    public boolean isSameUser(Optional<User> user1, Optional<User> user2){
        return user1.isPresent()&&
                user2.isPresent()&&
                user1.map(user -> user.getId()).isPresent()&&
                user2.map(user -> user.getId()).isPresent()&&
                user1.get().getId().equals(user2.get().getId());
    }

    public boolean isSuperUser(HttpSession session){
        Optional<String> userId = getUserIdFromSession(session);
        Optional<User> user;
        if(!userId.isPresent()){
            return false;
        }
        user = simpleDBService.findOneUserByUserId(userId.get());
        if(!user.isPresent()){
            return false;
        }
        System.out.println();
        return user.get().isSuperUser();
    }

    public boolean verifyToken(String email, String token) throws AuthException {
        return tokenVertifyService.verifyToken(email, token);
    }
}

package com.volunteer.commonweal.services.homePageServices;

import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.homePageModels.EmailVerifyToken;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenVertifyService {
    SimpleDBService simpleDBService;
    Config config;

    @Autowired
    public TokenVertifyService(SimpleDBService simpleDBService, Config config){
        this.simpleDBService = simpleDBService;
        this.config = config;
    }

    public boolean verifyToken(String email, String token) throws AuthException {
        Optional<EmailVerifyToken> emailVerifyTokenFound = simpleDBService.findEmailVerifyTokenByEmail(email);
        if(! emailVerifyTokenFound.isPresent()){
            throw new AuthException(1046, config.getExceptionsMap().get(1046));
        }
        return token.equals(emailVerifyTokenFound.get().getToken());
    }
}

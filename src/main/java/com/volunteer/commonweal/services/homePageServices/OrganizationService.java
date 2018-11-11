package com.volunteer.commonweal.services.homePageServices;

import com.volunteer.commonweal.models.exceptionModels.AuthException;
import com.volunteer.commonweal.models.implementModels.homePageModels.ActivityApply;
import com.volunteer.commonweal.models.implementModels.homePageModels.Organization;
import com.volunteer.commonweal.models.implementModels.homePageModels.OrganizationFoundation;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.services.dataBaseServices.SimpleDBService;
import com.volunteer.commonweal.services.env.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class OrganizationService {
    private SimpleDBService simpleDBService;
    private UserService userService;
    private Config config;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrganizationService(SimpleDBService simpleDBService,
                               Config config,
                               UserService userService){
        this.simpleDBService = simpleDBService;
        this.config = config;
        this.userService = userService;
    }

    public boolean isSuperUser(HttpSession session){
        return userService.isSuperUser(session);
    }

    public boolean isOrganizationLeader(HttpSession session, String organizationId) throws AuthException {
        Optional<String> userId = userService.getUserIdFromSession(session);
        if(!userId.isPresent()){
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
        Optional<Organization> organization = simpleDBService.findOneOrganizationById(organizationId);
        if(!organization.isPresent()){
            throw new AuthException(1043, config.getExceptionsMap().get(1048));
        }
        return organization.get().getLeaderId().equals(userId.get());
    }

    public boolean isOrganizationLeader(HttpSession session, Organization organization) throws AuthException{
        Optional<String> userId = userService.getUserIdFromSession(session);
        if(!userId.isPresent()){
            throw new AuthException(1021, config.getExceptionsMap().get(1021));
        }
        return organization.getLeaderId().equals(userId.get());
    }

    public boolean isOrganizationDuplicate(String name){
        return simpleDBService.findOneOrganizationByName(name).isPresent();
    }

    public boolean isOrganizationFoundationDuplicate(String name){
        Optional<OrganizationFoundation> organizationFoundationFound = simpleDBService.findOneOrganizationFoundationByName(name);
        if(!organizationFoundationFound.isPresent()){
            return false;
        }
        return (organizationFoundationFound.get().getStatus() == 0);
    }
}

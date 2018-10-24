package com.volunteer.commonweal.repositories.resourcesSharingServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.College;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CollegeRepository extends MongoRepository<College, String> {
    public Optional<College> findOneByName(String name);

}

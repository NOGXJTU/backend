package com.volunteer.commonweal.repositories.resourcesSharingServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExperienceRepository extends MongoRepository<Experience, String> {
}

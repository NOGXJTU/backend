package com.volunteer.commonweal.repositories.resourcesSharingServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Major;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MajorRepository extends MongoRepository<Major, String> {
}

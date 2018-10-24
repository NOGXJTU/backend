package com.volunteer.commonweal.repositories.resourcesSharingServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuideRepository extends MongoRepository<Guide, String> {
}

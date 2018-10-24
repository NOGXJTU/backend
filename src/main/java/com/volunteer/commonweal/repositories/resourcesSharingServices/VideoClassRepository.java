package com.volunteer.commonweal.repositories.resourcesSharingServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.VideoClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoClassRepository extends MongoRepository<VideoClass, String> {
}

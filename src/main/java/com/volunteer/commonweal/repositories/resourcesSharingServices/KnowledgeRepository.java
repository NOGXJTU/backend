package com.volunteer.commonweal.repositories.resourcesSharingServices;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Knowledge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KnowledgeRepository extends MongoRepository<Knowledge, String> {
}

package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ApplicationRepository extends MongoRepository<Application, String> {
    public Optional<Application> findOneById(String applicationId);
    public Stream<Application> findByUserId(String userId);
    public Stream<Application> findByActivityId(String activityId);
    public Stream<Application> findByActivityIdAndStatus(String activityId,int status);
    public Optional<Application> findOneByUserIdAndActivityIdAndStatus(String userId, String Activity, int status);
}

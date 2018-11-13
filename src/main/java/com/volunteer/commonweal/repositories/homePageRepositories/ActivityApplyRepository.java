package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.ActivityApply;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ActivityApplyRepository extends MongoRepository<ActivityApply, String> {
    public Optional<ActivityApply> findOneById(String id);
    public Optional<ActivityApply> findOneByName(String name);
    public Stream<ActivityApply> findByOwnerId(String ownerId);
//    public Optional<ActivityApply> findOneByNameAndStatus(String name, int status); //这个不需要因为name找到进行一下判断就可以了
}

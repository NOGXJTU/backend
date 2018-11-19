package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;


public interface ActivityRepository extends MongoRepository<Activity, String> {
    public Optional<Activity> findOneById(String id);
    public Stream<Activity> findAllById(Collection<String> idList);
    public Stream<Activity> findByOrganizationId(String organizationId);
    public Page<Activity> findAllByFinished(boolean finished, Pageable pageable);
    public Stream<Activity> findAllByFinished(boolean finished);
    public Optional<Activity> findOneByNameAndBeginTime(String name, String beginTime);
    public Stream<Activity> findAllByShowAndFinished(boolean show, boolean finished);
}

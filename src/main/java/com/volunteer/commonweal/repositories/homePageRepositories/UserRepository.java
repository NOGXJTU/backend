package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findOneByUsername(String username);
    public Optional<User> findOneByEmail(String email);
    public Optional<User> findOneByPhone(String phone);
    public Stream<User> findByIdIn(Collection<String> idGroup);
    public Stream<User> save(Stream<User> userStream);
    public Stream<User> findByActivitiesIdContaining(String activityId);
}

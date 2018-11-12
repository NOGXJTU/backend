package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
    public Optional<Organization> findOneById(String id);
    public Optional<Organization> findOneByName(String name);
    public Stream<Organization> findByLeaderId(String leaderId);

}

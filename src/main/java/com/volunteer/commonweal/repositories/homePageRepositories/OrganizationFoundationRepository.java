package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.OrganizationFoundation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrganizationFoundationRepository extends MongoRepository<OrganizationFoundation, String> {
    public Optional<OrganizationFoundation> findOneById(String id);
    public Stream<OrganizationFoundation> findByLeaderId(String leaderId);
    public Optional<OrganizationFoundation> findOneByName(String name);
    public Stream<OrganizationFoundation> findByStatus(int status);
}

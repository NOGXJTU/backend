package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.OrganizationApply;
import com.volunteer.commonweal.models.implementModels.homePageModels.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrganizationApplyRepository extends MongoRepository<OrganizationApply, String> {
    public Optional<OrganizationApply> findOneById(String id);
    public Stream<OrganizationApply> findByOrganizationIdAndStatus(String organizationId, int status);
    public Stream<OrganizationApply> findByOrganizationId(String organizationId);
    public Optional<OrganizationApply> findByOrganizationIdAndUserIdAndStatus(String organizationId, String userId, int status);
}

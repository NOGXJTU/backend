package com.volunteer.commonweal.repositories.homePageRepositories;

import com.volunteer.commonweal.models.implementModels.homePageModels.EmailVerifyToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailVerifyTokenRepository extends MongoRepository<EmailVerifyToken, String> {
    public Optional<EmailVerifyToken> findOneByEmail(String email);
}

package com.volunteer.commonweal.services.env;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoApp {
    @Autowired
    Config config;

    public @Bean
    MongoClient mongoClient() {
        return new MongoClient(config.getMongodb().getHost(), config.getMongodb().getPort());
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), config.getMongodb().getDatabase());
    }

}


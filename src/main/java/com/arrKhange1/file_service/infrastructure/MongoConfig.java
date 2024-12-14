package com.arrKhange1.file_service.infrastructure;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient, "test1");

    }
}

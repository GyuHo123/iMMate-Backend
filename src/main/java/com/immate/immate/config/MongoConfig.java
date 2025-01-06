package com.immate.immate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.immate.immate.domain.chat.repository")
public class MongoConfig {
} 
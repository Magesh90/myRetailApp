package com.myretailapp.mongodb.configuration

import com.mongodb.MongoClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = "com.myretailapp.mongodb.repositories")
class MongoDbConfiguration extends AbstractMongoConfiguration {

    @Value('${spring.data.mongodb.host}')
    String mongoDbIpAddress

    @Value('${spring.data.mongodb.port}')
    int mongoDbPortNumber

    @Value('${spring.data.mongodb.database}')
    String databaseName

    @Override
    @Bean
    MongoClient mongoClient() {
        new MongoClient(mongoDbIpAddress, mongoDbPortNumber)
    }

    @Override
    protected String getDatabaseName() {
        databaseName
    }
}

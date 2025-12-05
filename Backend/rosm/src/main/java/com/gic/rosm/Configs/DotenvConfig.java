package com.gic.rosm.Configs;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {

    @Bean(name = "mainDataSource")
    public DataSource mainDataSource() {

        String profile = System.getProperty("spring.profiles.active", "local");
        String dbUrl, dbUsername, dbPassword;
        if ("docker".equals(profile)) {
            Dotenv dotenv = Dotenv.configure().directory("./").load();
            dbUrl = dotenv.get("DOCKER_DB_URL");
            dbUsername = dotenv.get("DOCKER_DB_USERNAME");
            dbPassword = dotenv.get("DOCKER_DB_PASSWORD");
        } else {
            Dotenv dotenv = Dotenv.configure().directory("./rosm").load();
            dbUrl = dotenv.get("LOCAL_DB_URL");
            dbUsername = dotenv.get("LOCAL_DB_USERNAME");
            dbPassword = dotenv.get("LOCAL_DB_PASSWORD");
        }
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(dbUsername)
                .password(dbPassword)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    // @Bean(name = "mongoDataSource")
    // public MongoClient mongoDataSource() {
    //     Dotenv dotenv = Dotenv.load();

    //     String dbUrl = dotenv.get("MONGO_URL");        // example: mongodb://localhost:27017
    //     String dbName = dotenv.get("MONGO_DBNAME");    // example: restaurant

    //     ConnectionString connectionString = new ConnectionString(dbUrl + "/" + dbName);

    //     MongoClientSettings settings = MongoClientSettings.builder()
    //             .applyConnectionString(connectionString)
    //             .build();

    //     return MongoClients.create(settings);
    // }
}

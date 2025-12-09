package com.gic.rosm.Configs;

import javax.sql.DataSource;

import com.gic.rosm.Utility.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DotenvConfig {

    @Autowired
    private Environment env;

    private final Logger logger = Logger.getInstance();

    @Bean(name = "mainDataSource")
    public DataSource mainDataSource() {

        String[] profiles = env.getActiveProfiles();
        String profile = profiles.length > 0 ? profiles[0] : "local";
        logger.info("DotEnvConfig","Starting App with "+ profile);
        String dbUrl, dbUsername, dbPassword;
        if ("docker".equals(profile)) {
            Dotenv dotenv = Dotenv.configure().directory("./").load();
            dbUrl = dotenv.get("DOCKER_DB_URL");
            dbUsername = dotenv.get("DOCKER_DB_USERNAME");
            dbPassword = dotenv.get("DOCKER_DB_PASSWORD");
        } else if ("test".equals(profile)) {
            dbUrl = "jdbc:h2:mem:rosmdb;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
            dbUsername = "sa";
            dbPassword = "";

            return DataSourceBuilder.create()
                    .url(dbUrl)
                    .username(dbUsername)
                    .password(dbPassword)
                    .driverClassName("org.h2.Driver")
                    .build();

        } else {
            Dotenv dotenv = Dotenv.configure().directory("./").load();
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

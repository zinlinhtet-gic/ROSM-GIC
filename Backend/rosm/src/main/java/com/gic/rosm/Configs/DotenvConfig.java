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
        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String dbUrl = dotenv.get("DB_URL");
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");
        System.out.println("DB_URL: " + dbUrl);
        System.out.println("DB_USERNAME: " + dbUsername);
        System.out.println("DB_PASSWORD: " + dbPassword);
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

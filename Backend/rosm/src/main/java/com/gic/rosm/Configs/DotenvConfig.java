package com.gic.rosm.Configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DotenvConfig {

    @Bean(name = "mainDataSource")
    public DataSource mainDataSource() {
        Dotenv dotenv = Dotenv.load();

        String dbUrl = dotenv.get("DB_URL");
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");

        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(dbUsername)
                .password(dbPassword)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "mongoDataSource")
    public MongoClient mongoDataSource() {
        Dotenv dotenv = Dotenv.load();

        String dbUrl = dotenv.get("MONGO_URL");        // example: mongodb://localhost:27017
        String dbName = dotenv.get("MONGO_DBNAME");    // example: restaurant

        ConnectionString connectionString = new ConnectionString(dbUrl + "/" + dbName);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(settings);
    }
}

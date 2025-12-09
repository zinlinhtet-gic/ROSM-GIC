package com.gic.rosm.CommandLineRunners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;

@Component
public class DataLoader {

    @Autowired
    private Environment env;

    @Bean
    public CommandLineRunner loadTestData(DataSource dataSource) {
        return args -> {
            // Only load for test profile if you want
            String[] activeProfiles = env.getActiveProfiles();
            if (!Arrays.asList(activeProfiles).contains("test")) return;

            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("data.sql"));
            populator.setSeparator(";");
            populator.setSqlScriptEncoding("UTF-8");

            // Run after Hibernate has created tables
            populator.execute(dataSource);

            System.out.println("Test data loaded successfully!");
        };
    }
}

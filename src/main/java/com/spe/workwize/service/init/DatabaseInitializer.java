package com.spe.workwize.service.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatabaseInitializer {

    private final ResourceLoader resourceLoader;


    @Autowired
    public DatabaseInitializer(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    public void initializeDatabase(DataSource dataSource) {
        try{
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.addScript(resourceLoader.getResource("classpath:data.sql"));
            DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
            dataSourceInitializer.setDataSource(dataSource);
            dataSourceInitializer.setDatabasePopulator(databasePopulator);
            dataSourceInitializer.setEnabled(true);
            dataSourceInitializer.afterPropertiesSet();
        }
        catch(Exception e){
            System.out.println("e = " + e);
        }
    }
}
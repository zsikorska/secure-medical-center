package com.example.ua.secorgapp;

import com.example.ua.secorgapp.logger.Log4j;
import com.example.ua.secorgapp.logger.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.ua.secorgapp"})
public class SecOrgAppApplication {
    private Log4j logger = LoggerFactory.createLogger(SecOrgAppApplication.class.getName());
    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;
    @PostConstruct
    public void previewEnvironmentVariables(){
        logger.error(" ---- springDatasourceUrl: " + springDatasourceUrl);
    }


    public static void main(String[] args) {
        SpringApplication.run(SecOrgAppApplication.class, args);
    }

}

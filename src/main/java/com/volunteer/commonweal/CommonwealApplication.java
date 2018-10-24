package com.volunteer.commonweal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableSwagger2
public class CommonwealApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonwealApplication.class, args);
    }
}

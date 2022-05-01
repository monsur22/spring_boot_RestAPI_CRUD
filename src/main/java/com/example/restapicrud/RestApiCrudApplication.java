package com.example.restapicrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiCrudApplication.class, args);
    }

}

package com.sb.config;

import com.sb.controller.RestApiController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({HttpClientConfig.class, RestApiController.class})
public class ApplicationConfig {

    public static void main(String[] args) {
    SpringApplication.run(ApplicationConfig.class, args);
  }
}
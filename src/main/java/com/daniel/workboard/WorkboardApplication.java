package com.daniel.workboard;

import com.daniel.workboard.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class WorkboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(WorkboardApplication.class, args);
	}

}

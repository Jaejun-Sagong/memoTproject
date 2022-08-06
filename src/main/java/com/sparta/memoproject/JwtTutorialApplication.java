package com.sparta.memoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JwtTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTutorialApplication.class, args);
	}

}

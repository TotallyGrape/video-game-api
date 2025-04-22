package com.willowridge.videogame;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
public class VideogameApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
		SpringApplication.run(VideogameApplication.class, args);


	}
	@Configuration
	@EnableMethodSecurity // 👈 Needed for @PreAuthorize to work
	public class MethodSecurityConfig {
	}
}

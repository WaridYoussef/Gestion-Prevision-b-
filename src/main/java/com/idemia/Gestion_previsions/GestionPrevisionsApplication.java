package com.idemia.Gestion_previsions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionPrevisionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionPrevisionsApplication.class, args);
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	
	}
	
	@Bean
	public GestionPrevisionsContext gestionPrevisionsContext() {
		return new GestionPrevisionsContext();
	}
	
}

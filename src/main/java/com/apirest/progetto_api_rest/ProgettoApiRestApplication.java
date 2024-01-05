package com.apirest.progetto_api_rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgettoApiRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.print("update system");
	}
}

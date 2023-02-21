package com.nahuel.apirest;

import com.nahuel.apirest.entities.Business;
import com.nahuel.apirest.entities.User;
import com.nahuel.apirest.repository.BusinessRepository;
import com.nahuel.apirest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}

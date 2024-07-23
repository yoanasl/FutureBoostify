package com.futureboost.FutureBoostify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class FutureBoostifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutureBoostifyApplication.class, args);
	}

}

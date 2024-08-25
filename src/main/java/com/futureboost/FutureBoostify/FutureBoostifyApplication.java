package com.futureboost.FutureBoostify;

import com.futureboost.FutureBoostify.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class FutureBoostifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutureBoostifyApplication.class, args);
	}

}

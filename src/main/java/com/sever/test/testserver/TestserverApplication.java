package com.sever.test.testserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TestserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestserverApplication.class, args);
	}

}

package com.secretengine.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan("com.secretengine.service")
//@ComponentScan("com.secretengine.repository")

public class SecretEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretEngineApplication.class, args);
	}

}

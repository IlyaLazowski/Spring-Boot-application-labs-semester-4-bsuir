package com.frolichi.demo13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class Demo13Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo13Application.class, args);
	}
}

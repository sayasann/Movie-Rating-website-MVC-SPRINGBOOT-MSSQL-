package com.example_odev_1.odev_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.Entity;

@EntityScan(basePackages = {"com.example_odev_1.odev_1"})
@ComponentScan(basePackages = {"com.example_odev_1.odev_1"})
@EnableJpaRepositories(basePackages = {"com.example_odev_1.odev_1"})
@SpringBootApplication
public class Odev1Application {

	public static void main(String[] args) {
		SpringApplication.run(Odev1Application.class, args);
	}

}

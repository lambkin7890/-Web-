package com.rental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rental.dao") 
@SpringBootApplication
public class RentalSpringBootApplication{

	public static void main(String[] args) {
		SpringApplication.run(RentalSpringBootApplication.class, args);
	}

}

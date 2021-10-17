package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.bank.controller","com.bank.documents"})
public class PayPerformBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayPerformBackEndApplication.class, args);
	}

}

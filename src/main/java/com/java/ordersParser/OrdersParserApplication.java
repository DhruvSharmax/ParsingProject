package com.java.ordersParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.ordersParser.controller.ParseController;

@SpringBootApplication
public class OrdersParserApplication {
	
	@Autowired
	private static ParseController controller;
	
	public static void main(String[] args) {
		SpringApplication.run(OrdersParserApplication.class, args);
		controller.start(args);
	}
}

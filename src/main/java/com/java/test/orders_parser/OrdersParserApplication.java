package com.java.test.orders_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.test.orders_parser.controller.ParseController;

@SpringBootApplication
public class OrdersParserApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrdersParserApplication.class, args);
			ParseController.start(args);
	}
}

package com.java.ordersParser.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.ordersParser.Parsers.Parser;
import com.java.ordersParser.Parsers.ParserFactory;
import com.java.ordersParser.constant.Constants;
import com.java.ordersParser.model.OrderDetail;

@Controller
public class ParseController {
	@Autowired
	public static Parser parser;

	@Autowired
	private ParserFactory factory;

	public static List<OrderDetail> orders = new ArrayList<>();
	public volatile static int id;

	public void setDefaults(OrderDetail order, int number, String filename) {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		order.setId(++id);
		order.setFilename(filename);
		order.setResult(Constants.result);

		for (ConstraintViolation<OrderDetail> violation : validator.validate(order)) {
			order.setResult(violation.getMessage());
		}
		orders.add(order);
	}

	public void start(String[] files) {
		ExecutorService service = Executors.newFixedThreadPool(files.length);

		Arrays.stream(files).forEach(filename -> {
			service.submit(() -> factory.getParser(filename).parse(filename));
		});
		try {
			System.out.println(new ObjectMapper().writeValueAsString(orders));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

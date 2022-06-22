package com.java.test.orders_parser.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.test.orders_parser.Parsers.CsvParser;
import com.java.test.orders_parser.Parsers.FileParser;
import com.java.test.orders_parser.Parsers.JsonParser;
import com.java.test.orders_parser.Parsers.Parser;
import com.java.test.orders_parser.model.OrderDetail;

public class ParseController {
	public static Parser parser;
	public static List<OrderDetail> orders = new ArrayList<>();
	public volatile static int id;
	
	public static void setDefaults(OrderDetail order, int number, String filename){
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		order.setId(++id);
		order.setFilename(filename);
		order.setResult("Ok");
			
		for(ConstraintViolation<OrderDetail> violation:validator.validate(order)) {
			System.out.println("For file: "+filename+" and line number: "+number+" "+violation.getMessage());
			order.setResult("Not Ok");
		}
		orders.add(order);
	}
	
	public static void start(String[] args) {
		Arrays.stream(args).forEach(filename -> {
			String ext = filename.substring(filename.indexOf('.'));
			Path path = Paths.get(filename).toAbsolutePath();
			switch (ext) {
			case ".csv":
				parser = new CsvParser();
				break;
			case ".json":
				parser = new JsonParser();
				break;
			default:
				parser = new FileParser();
			}
			parser.parse(path);
		});
		
		try {
			System.out.println(new ObjectMapper().writeValueAsString(orders));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

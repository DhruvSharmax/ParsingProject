package com.java.ordersParser.Parsers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.ordersParser.Parsers.Parser;
import com.java.ordersParser.controller.ParseController;
import com.java.ordersParser.model.OrderDetail;

@Component
public class JsonParser implements Parser {

	@Autowired
	ParseController controller;

	@Override
	public void parseStrategy(Path path, BufferedReader reader) {
		ObjectMapper mapper = new ObjectMapper();
		String filename = path.getFileName().toString();
		try {
			List<OrderDetail> orders = mapper.readValue(Files.newBufferedReader(path, StandardCharsets.UTF_8),
					new TypeReference<List<OrderDetail>>() {
					});
			int number = 0;
			for (OrderDetail order : orders) {
				order.setLine(++number);
				controller.setDefaults(order, number, filename);
			}
		} catch (IOException e) {
			System.out.println("Invalid file format: " + filename);
		}
	}

}

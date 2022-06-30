package com.java.ordersParser.Parsers.impl;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.ordersParser.Parsers.Parser;
import com.java.ordersParser.controller.ParseController;
import com.java.ordersParser.model.OrderDetail;

@Component
public class FileParser implements Parser {

	@Autowired
	ParseController controller;
	
	@Override
	public void parseStrategy(Path path, BufferedReader reader) {
		try {
			List<String[]> lines = Files.lines(path)
					.map(x->x.split(","))
					.collect(Collectors.toList());
			
			int number = 0;
			for(String[] words:lines) {
				if(words.length<4) continue;
				OrderDetail order = new OrderDetail();
				order.setOrderId(words[0].trim());
				order.setAmount(words[1].trim());
				order.setCurrency(words[2].trim());
				order.setComment(words[3].trim());
				order.setLine(++number);
				controller.setDefaults(order, number, path.getFileName().toString());
			}
			
		} catch (Exception e) {
			System.out.println("Invalid file: "+path.getFileName().toString());
		}
	}
}

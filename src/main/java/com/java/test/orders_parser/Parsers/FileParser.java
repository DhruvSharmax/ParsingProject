package com.java.test.orders_parser.Parsers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.java.test.orders_parser.controller.ParseController;
import com.java.test.orders_parser.model.OrderDetail;

public class FileParser implements Parser {

	@Override
	public void parse(Path path) {
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
				ParseController.setDefaults(order, number, path.getFileName().toString());
			}
			
		} catch (Exception e) {
			System.out.println("Invalid file: "+path.getFileName().toString());
		}
	}

}

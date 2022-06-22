package com.java.test.orders_parser.Parsers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.test.orders_parser.controller.ParseController;
import com.java.test.orders_parser.model.OrderDetail;

public class JsonParser implements Parser {

	@Override
	public void parse(Path path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<OrderDetail> order = mapper.readValue(Files.newBufferedReader(path,StandardCharsets.UTF_8),
					new TypeReference<List<OrderDetail>>(){});
			int number = 0;
			for(OrderDetail o:order) {
				o.setLine(++number);
				ParseController.setDefaults(o, number, path.getFileName().toString());
			}
		} catch (IOException e) {
			System.out.println("Invalid file: "+path.getFileName().toString());
		}
	}

}

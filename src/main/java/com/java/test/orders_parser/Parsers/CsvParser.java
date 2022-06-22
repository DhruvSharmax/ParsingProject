package com.java.test.orders_parser.Parsers;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.java.test.orders_parser.controller.ParseController;
import com.java.test.orders_parser.model.OrderDetail;

public class CsvParser implements Parser {

	@Override
	public void parse(Path path) {
		try(BufferedReader reader = Files.newBufferedReader(path,StandardCharsets.UTF_8)){
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
			int number = 0;
			for(CSVRecord record : csvParser.getRecords()) {
				OrderDetail order = new OrderDetail();
				order.setOrderId(record.get("Order ID"));
				order.setAmount(record.get("amount"));
				order.setCurrency(record.get("currency"));
				order.setComment(record.get("comment"));
				order.setLine(++number);
				ParseController.setDefaults(order, number,path.getFileName().toString());
			}

		} catch(Exception e){
			System.out.println("Invalid file: "+path.getFileName().toString());
		}
	}

}

package com.java.ordersParser.Parsers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.ordersParser.Parsers.Parser;
import com.java.ordersParser.controller.ParseController;
import com.java.ordersParser.model.OrderDetail;

@Component
public class CsvParser implements Parser {

	@Autowired
	ParseController controller;

	@Override
	public void parseStrategy(Path path, BufferedReader reader) {
		try {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
			int number = 0;
			for (CSVRecord record : csvParser.getRecords()) {
				OrderDetail order = new OrderDetail();
				order.setOrderId(record.get("Order ID"));
				order.setAmount(record.get("amount"));
				order.setCurrency(record.get("currency"));
				order.setComment(record.get("comment"));
				order.setLine(++number);
				controller.setDefaults(order, number, path.getFileName().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

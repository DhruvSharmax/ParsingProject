package com.java.ordersParser.Parsers;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.ordersParser.Parsers.impl.CsvParser;
import com.java.ordersParser.Parsers.impl.FileParser;
import com.java.ordersParser.Parsers.impl.JsonParser;
import com.java.ordersParser.constant.Constants;

public class ParserFactory {

	@Autowired
	JsonParser jsonParser;

	@Autowired
	CsvParser csvParser;

	@Autowired
	FileParser anyFileParser;

	public Parser getParser(String file) {
		String ext = file.substring(file.lastIndexOf('.') + 1);
		if (ext.equalsIgnoreCase(Constants.CSV))
			return csvParser;
		else if (ext.equalsIgnoreCase(Constants.JSON))
			return jsonParser;
		else
			return anyFileParser;
	}
}
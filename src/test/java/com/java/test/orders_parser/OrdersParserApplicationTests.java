package com.java.test.orders_parser;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.ordersParser.Parsers.impl.CsvParser;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class OrdersParserApplicationTests {

	@InjectMocks
	private CsvParser parser;

	@org.junit.Test(expected = IOException.class)
	public void testInvalidFile() {
		parser.parse("src/test/resources/invalid.csv");
	}

	@Test
	public void testValidFile() {
		parser.parse("src/test/resources/orders1.csv");
	}

}

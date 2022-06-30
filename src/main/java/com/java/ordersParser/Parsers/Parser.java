package com.java.ordersParser.Parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface Parser {
	default void parse(String filename) {
		Path path = Paths.get(filename).toAbsolutePath();
		// Path path = Paths.get("C:/Users/erdhr/Desktop/"+filename);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			parseStrategy(path, reader);
		} catch (IOException ex) {
			System.err.println("File not found " + filename);
		}
	}
	
	void parseStrategy(Path path, BufferedReader reader);
}

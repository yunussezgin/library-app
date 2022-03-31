package com.optimizely.libraryapp.parser;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.util.CommonUtils;

@Component
public class BookDataParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDataParser.class);

	public List<Book> parseDataFromFile(String fileName) {
		List<Book> bookList = new ArrayList<>();
		File file = Paths.get("src", "main", "resources", "data", fileName).toFile();

		try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
			String[] values = csvReader.readNext();
			
			while((values = csvReader.readNext()) != null) {
				String[] fields = String.join(StringUtils.SPACE, values).split(";");
				Book book = new Book();
				book.setTitle(CommonUtils.getArrayValueWithDefault(fields, 0, StringUtils.EMPTY));
				book.setIsbnNumber(CommonUtils.getArrayValueWithDefault(fields, 1, StringUtils.EMPTY));
				String authorList = CommonUtils.getArrayValueWithDefault(fields, 2, StringUtils.EMPTY);
				String[] authors = authorList.split(StringUtils.SPACE);
				book.setAuthorList(Arrays.asList(authors));
				book.setShortDescription(CommonUtils.getArrayValueWithDefault(fields, 3, StringUtils.EMPTY));
				bookList.add(book);
			}
			
		} catch (Exception ex) {
			LOGGER.error("BookParser.parse exception occured! message:{}", ex.getMessage(), ex);
		}

		return bookList;
	}
}

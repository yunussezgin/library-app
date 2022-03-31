package com.optimizely.libraryapp.parser;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.optimizely.libraryapp.model.Author;
import com.optimizely.libraryapp.util.CommonUtils;

@Component
public class AuthorParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorParser.class);

	public List<Author> parse(String fileName) {
		List<Author> authorList = new ArrayList<>();
		File file = Paths.get("src", "main", "resources", "data", fileName).toFile();

		try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
			String[] values = csvReader.readNext();
			
			while((values = csvReader.readNext()) != null) {
				String[] fields = String.join(StringUtils.EMPTY, values).split(";");
				Author author = new Author();
				author.setEmailAddress(CommonUtils.getArrayValueWithDefault(fields, 0, StringUtils.EMPTY));
				author.setFirstName(CommonUtils.getArrayValueWithDefault(fields, 1, StringUtils.EMPTY));
				author.setLastName(CommonUtils.getArrayValueWithDefault(fields, 2, StringUtils.EMPTY));
				authorList.add(author);
			}
			
		} catch (Exception ex) {
			LOGGER.error("AuthorParser.parse exception occured! message:{}", ex.getMessage(), ex);
		}
	
		return authorList;
	}

}

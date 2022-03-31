package com.optimizely.libraryapp.parser;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.optimizely.libraryapp.model.Magazine;
import com.optimizely.libraryapp.util.CommonUtils;

@Component
public class MagazineDataParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(MagazineDataParser.class);

	@Cacheable(value = "magazine")
	public List<Magazine> parseDataFromFile(String fileName) {
		List<Magazine> magazineList = new ArrayList<>();
		InputStreamReader streamReader = CommonUtils.prepareFileReader(fileName);

		try (CSVReader csvReader = new CSVReader(streamReader)) {
			String[] values = csvReader.readNext();

			while ((values = csvReader.readNext()) != null) {
				String[] fields = String.join(StringUtils.SPACE, values).split(";");
				Magazine magazine = new Magazine();
				magazine.setTitle(CommonUtils.getArrayValueWithDefault(fields, 0, StringUtils.EMPTY));
				magazine.setIsbnNumber(CommonUtils.getArrayValueWithDefault(fields, 1, StringUtils.EMPTY));
				String authorList = CommonUtils.getArrayValueWithDefault(fields, 2, StringUtils.EMPTY);
				String[] authors = authorList.split(StringUtils.SPACE);
				magazine.setAuthorList(Arrays.asList(authors));
				String publicationDate = CommonUtils.getArrayValueWithDefault(fields, 3, StringUtils.EMPTY);
				magazine.setPublicationDate(CommonUtils.parseDateFromString(publicationDate));
				magazineList.add(magazine);
			}

		} catch (Exception ex) {
			LOGGER.error("MagazineParser.parse exception occured! message:{}", ex.getMessage(), ex);
		}

		return magazineList;
	}

}

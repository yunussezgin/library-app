package com.optimizely.libraryapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	public static String getArrayValueWithDefault(String[] array, int index, String defaultValue) {
		return array.length <= index ? defaultValue : array[index];
	}

	public static Date parseDateFromString(String strDate) {
		if (StringUtils.isEmpty(strDate))
			return null;

		Date date = null;

		try {
			date = new SimpleDateFormat(Constants.DATE_FORMAT).parse(strDate);
		} catch (ParseException e) {
			LOGGER.error("CommonUtils.parseDateFromString exception occured! message:{} date:{}", e.getMessage(), strDate, e);
		}

		return date;
	}

}

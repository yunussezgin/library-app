package com.optimizely.libraryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.optimizely.libraryapp.model.Magazine;
import com.optimizely.libraryapp.parser.MagazineParser;
import com.optimizely.libraryapp.service.MagazineService;
import com.optimizely.libraryapp.util.Constants;

@Service
public class MagazineServiceImpl implements MagazineService {

	private MagazineParser magazineParser;

	public MagazineServiceImpl(MagazineParser magazineParser) {
		this.magazineParser = magazineParser;
	}

	@Override
	public List<Magazine> readDataFromFile() {
		List<Magazine> magazineList = magazineParser.parse(Constants.MAGAZINE_FILE_NAME);
		return magazineList;
	}

}

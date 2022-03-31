package com.optimizely.libraryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.optimizely.libraryapp.model.Magazine;
import com.optimizely.libraryapp.parser.MagazineDataParser;
import com.optimizely.libraryapp.service.MagazineService;
import com.optimizely.libraryapp.util.Constants;

@Service
public class MagazineServiceImpl implements MagazineService {

	private MagazineDataParser magazineDataParser;

	public MagazineServiceImpl(MagazineDataParser magazineDataParser) {
		this.magazineDataParser = magazineDataParser;
	}

	@Override
	public List<Magazine> getAllMagazines() {
		List<Magazine> magazineList = magazineDataParser.parseDataFromFile(Constants.MAGAZINE_FILE_NAME);
		return magazineList;
	}

}

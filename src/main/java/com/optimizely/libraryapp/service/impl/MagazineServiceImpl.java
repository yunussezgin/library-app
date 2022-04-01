package com.optimizely.libraryapp.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public Magazine getMagazineByIsbn(String isbn) {
		List<Magazine> magazineList = getAllMagazines();
		return magazineList.stream().filter(m -> m.getIsbnNumber().equals(isbn)).findFirst().orElse(null);
	}

	@Override
	public List<Magazine> getMagazinesByAuthor(String author) {
		List<Magazine> magazineList = getAllMagazines();
		return magazineList.stream().filter(m -> m.getAuthorList().contains(author)).collect(Collectors.toList());
	}

	@Override
	public List<Magazine> getMagazinesByTitle(String title) {
		List<Magazine> magazineList = getAllMagazines();
		return magazineList.stream().filter(m -> m.getTitle().toUpperCase().contains(title.toUpperCase()))
				.sorted(Comparator.comparing(Magazine::getTitle)).collect(Collectors.toList());
	}

}

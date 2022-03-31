package com.optimizely.libraryapp.service;

import java.util.List;

import com.optimizely.libraryapp.model.Magazine;

public interface MagazineService {

	List<Magazine> getAllMagazines();

	Magazine getMagazineByIsbn(String isbn);
	
	List<Magazine> getMagazinesByAuthor(String author);

	List<Magazine> getMagazinesByTitle(String title);

}

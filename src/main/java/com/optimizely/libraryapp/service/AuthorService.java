package com.optimizely.libraryapp.service;

import java.util.List;

import com.optimizely.libraryapp.model.Author;

public interface AuthorService {
	
	List<Author> readDataFromFile();

}

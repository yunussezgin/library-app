package com.optimizely.libraryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.optimizely.libraryapp.model.Author;
import com.optimizely.libraryapp.parser.AuthorDataParser;
import com.optimizely.libraryapp.service.AuthorService;
import com.optimizely.libraryapp.util.Constants;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorDataParser authorParser;
	
	public AuthorServiceImpl(AuthorDataParser authorDataParser) {
		this.authorParser = authorDataParser;
	}

	public List<Author> getAllAuthors() {
		List<Author> authorList = authorParser.parseDataFromFile(Constants.AUTHOR_FILE_NAME);
		return authorList;
	}

}

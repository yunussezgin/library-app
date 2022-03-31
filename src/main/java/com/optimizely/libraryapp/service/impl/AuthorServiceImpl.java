package com.optimizely.libraryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.optimizely.libraryapp.model.Author;
import com.optimizely.libraryapp.parser.AuthorParser;
import com.optimizely.libraryapp.service.AuthorService;
import com.optimizely.libraryapp.util.Constants;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorParser authorParser;
	
	public AuthorServiceImpl(AuthorParser authorParser) {
		this.authorParser = authorParser;
	}

	public List<Author> readDataFromFile() {
		List<Author> authorList = authorParser.parse(Constants.AUTHOR_FILE_NAME);
		return authorList;
	}

}

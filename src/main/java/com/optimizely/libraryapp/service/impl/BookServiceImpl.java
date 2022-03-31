package com.optimizely.libraryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.parser.BookParser;
import com.optimizely.libraryapp.service.BookService;
import com.optimizely.libraryapp.util.Constants;

@Service
public class BookServiceImpl implements BookService {

	private BookParser bookParser;

	public BookServiceImpl(BookParser bookParser) {
		this.bookParser = bookParser;
	}

	@Override
	public List<Book> readDataFromFile() {
		List<Book> bookList = bookParser.parse(Constants.BOOKE_FILE_NAME);
		return bookList;
	}

}

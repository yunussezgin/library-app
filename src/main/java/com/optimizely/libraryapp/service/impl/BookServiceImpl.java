package com.optimizely.libraryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.parser.BookDataParser;
import com.optimizely.libraryapp.service.BookService;
import com.optimizely.libraryapp.util.Constants;

@Service
public class BookServiceImpl implements BookService {

	private BookDataParser bookDataParser;

	public BookServiceImpl(BookDataParser bookDataParser) {
		this.bookDataParser = bookDataParser;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = bookDataParser.parseDataFromFile(Constants.BOOKE_FILE_NAME);
		return bookList;
	}

}

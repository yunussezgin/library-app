package com.optimizely.libraryapp.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public Book getBookByIsbn(String isbn) {
		List<Book> bookList = getAllBooks();
		return bookList.stream().filter(b -> b.getIsbnNumber().equals(isbn)).findFirst().orElse(null);
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		List<Book> bookList = getAllBooks();
		return bookList.stream().filter(b -> b.getAuthorList().contains(author)).collect(Collectors.toList());
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		List<Book> bookList = getAllBooks();
		return bookList.stream().filter(b -> b.getTitle().contains(title)).sorted(Comparator.comparing(Book::getTitle))
				.collect(Collectors.toList());
	}

}

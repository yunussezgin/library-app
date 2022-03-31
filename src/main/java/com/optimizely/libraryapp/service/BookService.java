package com.optimizely.libraryapp.service;

import java.util.List;

import com.optimizely.libraryapp.model.Book;

public interface BookService {

	List<Book> getAllBooks();

	Book getBookByIsbn(String isbn);

	List<Book> getBooksByAuthor(String author);

	List<Book> getBooksByTitle(String title);

}

package com.optimizely.libraryapp;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.optimizely.libraryapp.model.Author;
import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.model.Magazine;
import com.optimizely.libraryapp.service.AuthorService;
import com.optimizely.libraryapp.service.BookService;
import com.optimizely.libraryapp.service.MagazineService;

@SpringBootApplication
public class LibraryAppApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(LibraryAppApplication.class, args);

		AuthorService authorService = applicationContext.getBean(AuthorService.class);
		List<Author> authorList = authorService.readDataFromFile();

		for (Author author : authorList)
			System.out.println(author.toString());

		BookService bookService = applicationContext.getBean(BookService.class);
		List<Book> bookList = bookService.readDataFromFile();

		for (Book book : bookList)
			System.out.println(book.toString());

		MagazineService magazineService = applicationContext.getBean(MagazineService.class);
		List<Magazine> magazineList = magazineService.readDataFromFile();

		for (Magazine magazine : magazineList)
			System.out.println(magazine.toString());

	}

}

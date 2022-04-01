package com.optimizely.libraryapp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import com.optimizely.libraryapp.model.Author;
import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.model.Magazine;
import com.optimizely.libraryapp.service.AuthorService;
import com.optimizely.libraryapp.service.BookService;
import com.optimizely.libraryapp.service.MagazineService;

@EnableCaching
@SpringBootApplication
public class LibraryAppApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(LibraryAppApplication.class, args);

		// FUNCTIONALITY REQUERMENTS

		// AUTHOR
		AuthorService authorService = applicationContext.getBean(AuthorService.class);
		List<Author> authorList = authorService.getAllAuthors();
		printListConsole(authorList);

		// BOOK
		BookService bookService = applicationContext.getBean(BookService.class);
		List<Book> bookList = bookService.getAllBooks();
		printListConsole(bookList);
		Book bookByIsbn = bookService.getBookByIsbn("4545-8558-3232");
		printConsole(bookByIsbn);
		List<Book> bookByAuthor = bookService.getBooksByAuthor("pr-walter@optivo.de");
		printListConsole(bookByAuthor);
		List<Book> bookByTitle = bookService.getBooksByTitle("Das Perfekte Dinner");
		printListConsole(bookByTitle);

		// MAGAZINE
		MagazineService magazineService = applicationContext.getBean(MagazineService.class);
		List<Magazine> magazineList = magazineService.getAllMagazines();
		printListConsole(magazineList);
		Magazine magazineByIsbn = magazineService.getMagazineByIsbn("2365-8745-7854");
		printConsole(magazineByIsbn);
		List<Magazine> magazineByAuthor = magazineService.getMagazinesByAuthor("pr-gustafsson@optivo.de");
		printListConsole(magazineByAuthor);
		List<Magazine> magazineByTitle = magazineService.getMagazinesByTitle("Gourmet");
		printListConsole(magazineByTitle);

	}

	private static <T> void printListConsole(List<T> objList) {
		if (CollectionUtils.isEmpty(objList))
			return;
		for (Object obj : objList)
			System.out.println(obj.toString());
	}

	private static <T> void printConsole(T obj) {
		if (obj == null)
			return;
		System.out.println(obj.toString());
	}

}

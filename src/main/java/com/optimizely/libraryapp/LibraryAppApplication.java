package com.optimizely.libraryapp;

import java.util.List;

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

		AuthorService authorService = applicationContext.getBean(AuthorService.class);
		List<Author> authorList = authorService.getAllAuthors();

		for (Author author : authorList)
			System.out.println(author.toString());

		BookService bookService = applicationContext.getBean(BookService.class);
		List<Book> bookList = bookService.getAllBooks();
		Book bookByIsbn = bookService.getBookByIsbn("4545-8558-3232");
		List<Book> bookByAuthor = bookService.getBooksByAuthor("pr-walter@optivo.de");
		List<Book> bookByTitle = bookService.getBooksByTitle("p");

		for (Book book : bookList)
			System.out.println(book.toString());

		MagazineService magazineService = applicationContext.getBean(MagazineService.class);
		List<Magazine> magazineList = magazineService.getAllMagazines();
		Magazine magazineByIsbn = magazineService.getMagazineByIsbn("2365-8745-7854");
		List<Magazine> magazineByAuthor = magazineService.getMagazinesByAuthor("pr-gustafsson@optivo.de");
		List<Magazine> magazineByTitle = magazineService.getMagazinesByTitle("V");

		for (Magazine magazine : magazineList)
			System.out.println(magazine.toString());

	}

}

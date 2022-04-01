package com.optimizely.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.parser.BookDataParser;
import com.optimizely.libraryapp.service.impl.BookServiceImpl;
import com.optimizely.libraryapp.util.Constants;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

	@Mock
	private BookDataParser bookDataParser;

	@InjectMocks
	private BookServiceImpl bookService;

	Book bookMock = null;
	List<Book> bookListMock = null;

	@BeforeEach
	public void setup() {
		bookListMock = new ArrayList<>();
		// 1. BOOK
		bookMock = new Book();
		bookMock.setIsbnNumber("2145-8548-3325");
		bookMock.setTitle("Das große GU-Kochbuch Kochen für Kinder");
		bookMock.setShortDescription("Es beginnt mit... den ersten Löffelchen für Mami	 Papi und den Rest der Welt.");
		List<String> authorList = new ArrayList<>();
		authorList.add("pr-ferdinand@optivo.de");
		authorList.add("pr-lieblich@optivo.de");
		bookMock.setAuthorList(authorList);
		bookListMock.add(bookMock);
		// 2. BOOK
		bookMock = new Book();
		bookMock.setIsbnNumber("1024-5245-8584");
		bookMock.setTitle("Genial italienisch");
		bookMock.setShortDescription(
				"Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht.");
		authorList = new ArrayList<>();
		authorList.add("pr-lieblich@optivo.de");
		authorList.add("pr-walter@optivo.de");
		authorList.add("pr-rabe@optivo.de");
		authorList.add("pr-mueller@optivo.de");
		bookMock.setAuthorList(authorList);
		bookListMock.add(bookMock);
		// 3. BOOK
		bookMock = new Book();
		bookMock.setIsbnNumber("2215-0012-5487");
		bookMock.setTitle("O'Reillys Kochbuch für Geeks");
		bookMock.setShortDescription(
				"Nach landläufiger Meinung leben Geeks von Cola und TK-Pizza die sie nachts am Rechner geistesabwesend in sich reinschlingen.");
		authorList = new ArrayList<>();
		authorList.add("pr-mueller@optivo.de");
		bookMock.setAuthorList(authorList);
		bookListMock.add(bookMock);
	}

	@Test
	public void givenBookList_whenGetAllBooks_thenReturnBookList() {
		// given
		given(bookDataParser.parseDataFromFile(Constants.BOOK_FILE_NAME)).willReturn(bookListMock);

		// when
		List<Book> bookList = bookService.getAllBooks();

		// then
		assertThat(bookList).isNotNull();
		assertThat(bookList.size()).isEqualTo(3);
	}

	@Test
	public void givenBookList_whenGetBookByIsbn_thenReturnBookObject() {
		// given
		given(bookDataParser.parseDataFromFile(Constants.BOOK_FILE_NAME)).willReturn(bookListMock);

		// when
		Book book = bookService.getBookByIsbn("1024-5245-8584");

		// then
		assertThat(book).isNotNull();
		assertThat(book.getIsbnNumber()).isEqualTo("1024-5245-8584");
	}

	@Test
	public void givenBookList_whenGetBookByAuthor_thenReturnBookList() {
		// given
		given(bookDataParser.parseDataFromFile(Constants.BOOK_FILE_NAME)).willReturn(bookListMock);

		// when
		List<Book> bookList = bookService.getBooksByAuthor("pr-mueller@optivo.de");

		// then
		assertThat(bookList).isNotNull();
		assertThat(bookList.size()).isEqualTo(2);
	}
	
	@Test
	public void givenBookList_whenGetBookByTitle_thenReturnSortedBookList() {
		// given
		given(bookDataParser.parseDataFromFile(Constants.BOOK_FILE_NAME)).willReturn(bookListMock);

		// when
		List<Book> bookList = bookService.getBooksByTitle("Kochbuch");

		// then
		assertThat(bookList).isNotNull();
		assertThat(bookList.size()).isEqualTo(2);
		assertThat(bookList.get(0).getIsbnNumber()).isEqualTo("2145-8548-3325");
		assertThat(bookList.get(1).getIsbnNumber()).isEqualTo("2215-0012-5487");
	}

}

package com.optimizely.libraryapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.optimizely.libraryapp.model.Author;
import com.optimizely.libraryapp.parser.AuthorDataParser;
import com.optimizely.libraryapp.service.impl.AuthorServiceImpl;
import com.optimizely.libraryapp.util.Constants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

	@Mock
	private AuthorDataParser authorDataParser;

	@InjectMocks
	private AuthorServiceImpl authorService;

	Author author = null;
	List<Author> authorList = null;

	@BeforeEach
	public void setup() {
		authorList = new ArrayList<>();
		author = new Author();
		author.setEmailAddress("pr-walter@optivo.de");
		author.setFirstName("Paul");
		author.setLastName("Walter");
		authorList.add(author);
		author = new Author();
		author.setEmailAddress("pr-ferdinand@optivo.de");
		author.setFirstName("Franz");
		author.setLastName("Ferdinand");
		authorList.add(author);
		author = new Author();
		author.setEmailAddress("pr-lieblich@optivo.de");
		author.setFirstName("Werner");
		author.setLastName("Lieblich");
		authorList.add(author);
	}

	@Test
	public void givenAuthorList_whenGetAllAuthors_thenReturnAuthorList() {
		// given
		given(authorDataParser.parseDataFromFile(Constants.AUTHOR_FILE_NAME)).willReturn(authorList);

		// when
		List<Author> authorList = authorService.getAllAuthors();

		// then
		assertThat(authorList).isNotNull();
		assertThat(authorList.size()).isEqualTo(3);
	}

	@Test
	public void givenEmptyList_whenGetAllAuthors_thenReturnEmptyAuthorList() {
		// given
		given(authorDataParser.parseDataFromFile(Constants.AUTHOR_FILE_NAME)).willReturn(Collections.emptyList());

		// when
		List<Author> authorList = authorService.getAllAuthors();

		// then
		assertThat(authorList).isNotNull();
		assertThat(authorList.size()).isEqualTo(0);
	}

}

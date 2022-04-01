package com.optimizely.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

	@Mock
	private AuthorDataParser authorDataParser;

	@InjectMocks
	private AuthorServiceImpl authorService;

	Author authorMock = null;
	List<Author> authorListMock = null;

	@BeforeEach
	public void setup() {
		authorListMock = new ArrayList<>();
		// 1. AUTHOR
		authorMock = new Author();
		authorMock.setEmailAddress("pr-walter@optivo.de");
		authorMock.setFirstName("Paul");
		authorMock.setLastName("Walter");
		authorListMock.add(authorMock);
		// 2. AUTHOR
		authorMock = new Author();
		authorMock.setEmailAddress("pr-ferdinand@optivo.de");
		authorMock.setFirstName("Franz");
		authorMock.setLastName("Ferdinand");
		authorListMock.add(authorMock);
		// 3. AUTHOR
		authorMock = new Author();
		authorMock.setEmailAddress("pr-lieblich@optivo.de");
		authorMock.setFirstName("Werner");
		authorMock.setLastName("Lieblich");
		authorListMock.add(authorMock);
	}

	@Test
	public void givenAuthorList_whenGetAllAuthors_thenReturnAuthorList() {
		// given
		given(authorDataParser.parseDataFromFile(Constants.AUTHOR_FILE_NAME)).willReturn(authorListMock);

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

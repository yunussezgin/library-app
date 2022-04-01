package com.optimizely.libraryapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.optimizely.libraryapp.model.Book;
import com.optimizely.libraryapp.model.Magazine;
import com.optimizely.libraryapp.parser.MagazineDataParser;
import com.optimizely.libraryapp.service.impl.MagazineServiceImpl;
import com.optimizely.libraryapp.util.CommonUtils;
import com.optimizely.libraryapp.util.Constants;

@ExtendWith(MockitoExtension.class)
public class MagazineServiceTests {

	@Mock
	private MagazineDataParser magazineDataParser;

	@InjectMocks
	private MagazineServiceImpl magazineService;

	Magazine magazineMock = null;
	List<Magazine> magazineListMock = null;

	@BeforeEach
	public void setup() {
		magazineListMock = new ArrayList<>();
		// 1. Magazine
		magazineMock = new Magazine();
		magazineMock.setIsbnNumber("4545-8541-2012");
		magazineMock.setPublicationDate(CommonUtils.parseDateFromString("10.07.2006"));
		magazineMock.setTitle("Meine Familie und ich");
		List<String> authorList = new ArrayList<>();
		authorList.add("pr-mueller@optivo.de");
		magazineMock.setAuthorList(authorList);
		magazineListMock.add(magazineMock);
		// 2. Magazine
		magazineMock = new Magazine();
		magazineMock.setIsbnNumber("5454-5587-3210");
		magazineMock.setPublicationDate(CommonUtils.parseDateFromString("01.05.2007"));
		magazineMock.setTitle("Schöner kochen");
		authorList = new ArrayList<>();
		authorList.add("pr-mueller@optivo.de");
		authorList.add("pr-walter@optivo.de");
		magazineMock.setAuthorList(authorList);
		magazineListMock.add(magazineMock);
		// 3. Magazine
		magazineMock = new Magazine();
		magazineMock.setIsbnNumber("2365-5632-7854");
		magazineMock.setPublicationDate(CommonUtils.parseDateFromString("12.12.2002"));
		magazineMock.setTitle("Kochen für Genießer");
		authorList = new ArrayList<>();
		authorList.add("pr-gustafsson@optivo.de");
		magazineMock.setAuthorList(authorList);
		magazineListMock.add(magazineMock);
	}

	@Test
	public void givenMagazineList_whenGetAllMagazines_thenReturnMagazineList() {
		// given
		given(magazineDataParser.parseDataFromFile(Constants.MAGAZINE_FILE_NAME)).willReturn(magazineListMock);

		// when
		List<Magazine> magazineList = magazineService.getAllMagazines();

		// then
		assertThat(magazineList).isNotNull();
		assertThat(magazineList.size()).isEqualTo(3);
	}

	@Test
	public void givenMagazineList_whenGetMagazineByIsbn_thenReturnMagazineObject() {
		// given
		given(magazineDataParser.parseDataFromFile(Constants.MAGAZINE_FILE_NAME)).willReturn(magazineListMock);

		// when
		Magazine magazine = magazineService.getMagazineByIsbn("4545-8541-2012");

		// then
		assertThat(magazine).isNotNull();
		assertThat(magazine.getIsbnNumber()).isEqualTo("4545-8541-2012");
	}

	@Test
	public void givenMagazineList_whenGetMagazineByAuthor_thenReturnMagazineList() {
		// given
		given(magazineDataParser.parseDataFromFile(Constants.MAGAZINE_FILE_NAME)).willReturn(magazineListMock);

		// when
		List<Magazine> mazineList = magazineService.getMagazinesByAuthor("pr-mueller@optivo.de");

		// then
		assertThat(mazineList).isNotNull();
		assertThat(mazineList.size()).isEqualTo(2);
	}

	@Test
	public void givenMagazineList_whenGetMagazineByTitle_thenReturnSortedMagazineList() {
		// given
		given(magazineDataParser.parseDataFromFile(Constants.MAGAZINE_FILE_NAME)).willReturn(magazineListMock);

		// when
		List<Magazine> mazineList = magazineService.getMagazinesByTitle("kochen");

		// then
		assertThat(mazineList).isNotNull();
		assertThat(mazineList.size()).isEqualTo(2);
		assertThat(mazineList.get(0).getIsbnNumber()).isEqualTo("2365-5632-7854");
		assertThat(mazineList.get(1).getIsbnNumber()).isEqualTo("5454-5587-3210");
	}

}

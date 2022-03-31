package com.optimizely.libraryapp.model;

import java.util.List;

public class Book {

	private String title;
	private String isbnNumber;
	private List<String> authorList;
	private String shortDescription;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public List<String> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<String> authorList) {
		this.authorList = authorList;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", isbnNumber=" + isbnNumber + ", authorList=" + authorList
				+ ", shortDescription=" + shortDescription + "]";
	}	

}

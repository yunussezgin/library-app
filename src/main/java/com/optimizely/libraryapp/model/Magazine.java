package com.optimizely.libraryapp.model;

import java.util.Date;
import java.util.List;

public class Magazine {

	private String title;
	private String isbnNumber;
	private List<String> authorList;
	private Date publicationDate;

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

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public String toString() {
		return "Magazine [title=" + title + ", isbnNumber=" + isbnNumber + ", authorList=" + authorList
				+ ", publicationDate=" + publicationDate + "]";
	}

}

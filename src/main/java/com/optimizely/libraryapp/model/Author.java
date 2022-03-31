package com.optimizely.libraryapp.model;

public class Author {

	private String emailAddress;
	private String firstName;
	private String lastName;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Author [emailAddress=" + emailAddress + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}

package com.jdc.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.ElementCollection;

@Embeddable
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;

	private String address;
	
	@ElementCollection
	private List<String> phones;
	@ElementCollection
	private List<String> emails;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

}

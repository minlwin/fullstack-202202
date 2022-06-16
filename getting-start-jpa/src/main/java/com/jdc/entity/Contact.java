package com.jdc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(table = "CONTACT_INFO")
	private String phone;
	@Column(table = "CONTACT_INFO")
	private String email;
	@Column(table = "CONTACT_INFO")
	private String address;

	public Contact() {
	}

	public Contact(String phone, String email, String address) {
		super();
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(phone, other.phone);
	}

}

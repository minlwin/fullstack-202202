package com.jdc.demo.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jdc.demo.entity.listeners.TimeEnableEntity;
import com.jdc.demo.entity.listeners.Times;

@Entity
@ExcludeDefaultListeners
public class Contact implements TimeEnableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = LAZY)
	private Member member;
	private String phone;
	private String email;

	private Times times;

	public Contact() {
	}

	public Contact(String phone, String email) {
		super();
		this.phone = phone;
		this.email = email;
	}

	public Times getTimes() {
		return times;
	}

	public void setTimes(Times times) {
		this.times = times;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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

}

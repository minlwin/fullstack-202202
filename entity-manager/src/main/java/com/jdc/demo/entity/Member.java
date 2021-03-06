package com.jdc.demo.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jdc.demo.entity.listeners.TimeEnableEntity;
import com.jdc.demo.entity.listeners.Times;
import com.jdc.demo.entity.listeners.TimesListener;

@Entity
@EntityListeners(TimesListener.class)
public class Member implements TimeEnableEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String loginId;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

	@ElementCollection
	private List<String> tags;

	@OneToMany(mappedBy = "member", orphanRemoval = true, cascade = { PERSIST, MERGE, DETACH })
	private List<Contact> contacts;

	private Times times;

	public Member() {
		tags = new ArrayList<>();
		contacts = new ArrayList<>();
	}

	public Member(String name, String loginId, String password) {
		this();
		this.name = name;
		this.loginId = loginId;
		this.password = password;
		this.role = Role.Member;
	}

	public enum Role {
		Admin, Member
	}

	public Times getTimes() {
		return times;
	}

	public void setTimes(Times times) {
		this.times = times;
	}

	public void addContact(Contact c) {
		c.setMember(this);
		contacts.add(c);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}

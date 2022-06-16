package com.jdc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;

@Entity
@Table(
		name = "MEMBER_TBL"
)
@SecondaryTables({
	@SecondaryTable(
			name = "LOGIN_INFO", 
			indexes = {
					@Index(columnList = "role")
			}
	),
	@SecondaryTable(
			name = "CONTACT_INFO", 
			uniqueConstraints = @UniqueConstraint(
					columnNames = "email"
			)
	)	
})
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "member_seq")
	@TableGenerator(name = "member_seq", allocationSize = 1, initialValue = 2000)
	private int id;
	private String name;
	
	@Column(table = "LOGIN_INFO")
	private String loginId;
	@Column(table = "LOGIN_INFO")
	private String password;
	@Column(table = "LOGIN_INFO")
	@Enumerated(STRING)
	private Role role;

	private Date dateOfBith;
	
	@Embedded
	private Contact contact;

	public enum Role {
		Admin, Teacher, Student
	}

	public Member() {
	}

	public Member(String name, String phone, String email) {
		super();
		this.contact = new Contact();
		this.name = name;
		contact.setPhone(phone);
		contact.setEmail(email);
	}

	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDateOfBith() {
		return dateOfBith;
	}

	public void setDateOfBith(Date dateOfBith) {
		this.dateOfBith = dateOfBith;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}

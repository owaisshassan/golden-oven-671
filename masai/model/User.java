package com.masai.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLoginId;
	private String userName;
	private String password;
	private String firstname;
	private String lastName;
	private long contact;
	private String email;
	
	@OneToOne
	private Reservation reservation;


	public User(String userName, String password, String firstname, String lastName, long contact, String email,
			Reservation reservation) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstname = firstname;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.reservation = reservation;
	}
	
	
	
	
	
}

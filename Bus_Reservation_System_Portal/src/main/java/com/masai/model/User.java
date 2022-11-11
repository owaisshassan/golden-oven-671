package com.masai.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLoginId;
	private String userName;
	private String password;
	private String firstname;
	private String lastName;
	private String contact;
	private String email;
	
	@OneToOne
	private Reservation reservation;


	public User(String userName, String password, String firstname, String lastName, String contact, String email,
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

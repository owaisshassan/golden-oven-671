package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentAdminSession {
	
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer adminId;
	
	private String adminUserName;
	private String adminPassword;
	private String mobile;

}

package com.masai.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;



@Data
@Entity
public class Admin {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer adminId;
	
	@Size(min = 3, max = 12, message = "adminUserName length should be in range of 3-12 letters")
	private String adminUserName;
	
	@NotNull(message = "please fill password!, password cannot be null")
	private String adminPassword;
	
	@Min(value = 10,  message = "mobile NO. should be in range of 10-12 digits")
	private long mobile;

}

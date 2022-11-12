package com.masai.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	@NotBlank(message= "Password cannot be blank!")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{6,8}", message = "Password must be 6-8 characters in length")
	private String adminPassword;
	
//	@Min(value = 10,  message = "mobile NO. should be in range of 10-12 digits")
//	private long mobile;
	
	@NotNull(message="Mobile number cannot be null!")
	@NotBlank(message= "Mobile number cannot be blank!")
	@Pattern(regexp = "[7986]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	@Size(min = 10)
	private String mobile;

}

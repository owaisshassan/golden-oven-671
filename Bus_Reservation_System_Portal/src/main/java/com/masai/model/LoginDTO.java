package com.masai.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDTO {

	@NotNull(message = "Mobile number cannot be null") 
	@Size(min = 10, max = 12, message = "Please provide mobile number in range of 10 to 12 digits")
	private long mobileNo;
	
	@NotNull(message = "password cannot be null")
	@Size(min = 3, max = 8, message = "Please provide pasword in range of 3 to 8 characters")
	private String password;
	
}

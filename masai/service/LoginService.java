package com.masai.service;

import com.masai.exceptions.LoginException;
import com.masai.model.LoginDTO;

public interface LoginService {
	
	
	
	public String logIntoUserAccount(LoginDTO dto)throws LoginException;

	public String logOutFromUserAccount(String key)throws LoginException;
	
	public String logIntoAdminAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAdminAccount(String key)throws LoginException;

}

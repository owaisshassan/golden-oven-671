package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.model.LoginDTO;
import com.masai.service.LoginService;

@RestController
@RequestMapping("/brsp")
public class LoginController {
	
	@Autowired
	private LoginService LoginService;
	
	
	
//  http://localhost:8888/brsp/login	
	@PostMapping("/login")
	public ResponseEntity<String> logInUserHandler(@RequestBody LoginDTO dto) throws LoginException {
		
		String result = LoginService.logIntoUserAccount(dto);
		return new ResponseEntity<String>(result,HttpStatus.OK );
		
	}
	
	
//  http://localhost:8888/brsp/logout	
	@PostMapping("/logout")
	public String logoutUserHandler(@RequestParam(required = false) String key) throws LoginException {
		return LoginService.logOutFromUserAccount(key);
		
	}

	
//  http://localhost:8888/brsp/admin/login	
	@PostMapping("/admin/login")
	public ResponseEntity<String> logInAdminHandler(@RequestBody LoginDTO dto) throws LoginException {
		
		String result = LoginService.logIntoAdminAccount(dto);
		return new ResponseEntity<String>(result,HttpStatus.OK );
		
	}

	
//  http://localhost:8888/brsp/admin/logout		
	@PostMapping("/admin/logout")
	public String logoutAdminHandler(@RequestParam(required = false) String key) throws LoginException {
		return LoginService.logOutFromAdminAccount(key);
		
	}

}

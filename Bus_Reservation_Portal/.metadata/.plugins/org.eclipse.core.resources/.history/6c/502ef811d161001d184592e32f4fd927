package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.UserException;
import com.masai.model.User;
import com.masai.service.UserService;



@RestController
@RequestMapping("/brsp")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	
//   http://localhost:8888/brsp/users
	@PostMapping("/users")
	public ResponseEntity<User> addUserHandler( @RequestBody User user) throws UserException{
		User addedUser= userService.addUser(user);
		
		
		return new ResponseEntity<User>(addedUser, HttpStatus.CREATED);
	}
	
	
//provide login id of the user you want to update
//if mobile num provided matches with any already existing user, it will not be updated
//change contact and hit the request	
	
//  http://localhost:8888/brsp/users	
	@PutMapping("/users")
	public ResponseEntity<User> updateUserHandler(@RequestParam(required = false) String key, @RequestBody User user) throws UserException{
		User updated= userService.updateUser(user, key);
		
		return new ResponseEntity<User>(updated, HttpStatus.OK);
	}
	
	
//  http://localhost:8888/brsp/users/userId	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUserHandler(@PathVariable("id") int id, @RequestParam String key) throws UserException, AdminException{
		User delUser = userService.deleteUser(id,key);
		
		return new ResponseEntity<User>(delUser, HttpStatus.OK) ;
	}
	
	
//  http://localhost:8888/brsp/users/userId		
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> viewUserHandler(@PathVariable("userId") int id) throws UserException{
		User viewUser  = userService.viewUser(id) ;
		
		return new ResponseEntity<User>(viewUser, HttpStatus.ACCEPTED) ;
	}
	
	
//  http://localhost:8888/brsp/users	
	@GetMapping("/users")
	public ResponseEntity<List<User>> viewAllUsersHandler() throws UserException{
		List<User> allUsers = userService.viewAllUsers() ;
		
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK) ;
	}
	
	
	
	
	

	//User requestBody:

//    {
//	"userName": "owais",
//	"password": "1234",
//	"firstName": "owais",
//	"lastName": "bhat",
//	"contact": "12345678",
//	"email": "ob@gmail.com"
//    }	
	
	
	
	
}

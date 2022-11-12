package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.model.Admin;
import com.masai.service.AdminService;

@RestController
@RequestMapping("/brsp")
public class AdminController {
	
	
	
	@Autowired
	private AdminService adService;
	
//	http://localhost:8888/brsp/admin
	@PostMapping("/admin")
	public ResponseEntity<Admin> saveAdminHandler(@Valid @RequestBody Admin admin) throws AdminException {
		
		Admin savedCustomer= adService.createAdmin(admin);

		return new ResponseEntity<Admin>(savedCustomer,HttpStatus.CREATED);
	}
	
	
	
//	http://localhost:8888/brsp/admin	
	@PutMapping("/admin")
	public  ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin,@RequestParam(required = false) String key ) throws AdminException {
		Admin updatedCustomer= adService.updateAdmin(admin, key);
				
		return new ResponseEntity<Admin>(updatedCustomer,HttpStatus.OK);
		
	}
	
	

}

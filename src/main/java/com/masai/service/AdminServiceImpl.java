package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminSession;
import com.masai.repository.AdminRepo;
import com.masai.repository.CurrentAdminSessionRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	@Autowired
	private AdminRepo adminDao;
	
	@Autowired
	private CurrentAdminSessionRepo casDao;
	

	@Override
	public Admin createAdmin(Admin admin) throws AdminException {
		Admin existingAdmin= adminDao.findByMobile(admin.getMobile());
		
		if(existingAdmin != null) 
		throw new AdminException("Admin Already Registered with given Mobile number");
		return adminDao.save(admin);
	}
	
	
	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException {
		CurrentAdminSession loggedInAdmin= casDao.findByUuid(key);
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key to update a Admin");
		}
		
		if(admin.getAdminId() == loggedInAdmin.getAdminId()) {
			return adminDao.save(admin);
		}
		else
			throw new AdminException("Invalid Admin Details, please login first");
	}
	
	
	
	
	
	
	
	
	

}

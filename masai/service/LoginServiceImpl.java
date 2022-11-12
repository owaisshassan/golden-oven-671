package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.LoginDTO;
import com.masai.model.User;
import com.masai.repository.AdminRepo;
import com.masai.repository.CurrentAdminSessionRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.UserRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepo userDao;
	
	@Autowired
	private CurrentUserSessionRepo cusDao;
	
	@Autowired
	private AdminRepo adminDao;
	
	@Autowired
	private CurrentAdminSessionRepo casDao;

	@Override
	public String logIntoUserAccount(LoginDTO dto) throws LoginException {
		User existingUser= userDao.findByContact(dto.getMobileNo());
		
		if(existingUser == null) {
			throw new LoginException("Please Enter a valid mobile number");
		}
		
		Optional<CurrentUserSession> validUserSessionOpt =  cusDao.findById(existingUser.getUserLoginId());
		
		if(validUserSessionOpt.isPresent()) {
			
			throw new LoginException("User already Logged In with this number");
			
		}
		
		if(existingUser.getPassword().equals(dto.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserLoginId(),key,LocalDateTime.now());
			
			cusDao.save(currentUserSession);

			return currentUserSession.toString();
		}
		else
			throw new LoginException("Please Enter a valid password");
	}
	
	

	@Override
	public String logOutFromUserAccount(String key) throws LoginException {
		CurrentUserSession validUserSession = cusDao.findByUuid(key);
		
		if(validUserSession == null) {
			throw new LoginException("User Not Logged In with this key");
		}
		
		cusDao.delete(validUserSession);
		
		return "Logged Out !";
	}

	@Override
	public String logIntoAdminAccount(LoginDTO dto) throws LoginException {
		Admin existingAdmin= adminDao.findByMobile(dto.getMobileNo());
		
		if(existingAdmin == null) {
			throw new LoginException("Please Enter a valid mobile number");
		}
		
		Optional<CurrentAdminSession> validAdminSessionOpt =  casDao.findById(existingAdmin.getAdminId());
		
		if(validAdminSessionOpt.isPresent()) {
			
			throw new LoginException("Admin already Logged In with this number");
			
		}
		
		if(existingAdmin.getAdminPassword().equals(dto.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentAdminSession currentAdminSession = new CurrentAdminSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
			
			casDao.save(currentAdminSession);

			return currentAdminSession.toString();
		}
		else
			throw new LoginException("Please Enter a valid password");	
	}

	@Override
	public String logOutFromAdminAccount(String key) throws LoginException {
		CurrentAdminSession validAdminSession = casDao.findByUuid(key);
		
		if(validAdminSession == null) {
			throw new LoginException("Admin Not Logged In with this number");
		}
		
		casDao.delete(validAdminSession);
		
		return "Logged Out !";
	}
	
	

}

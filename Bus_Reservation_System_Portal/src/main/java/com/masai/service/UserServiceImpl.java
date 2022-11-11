package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.UserException;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.repository.AdminRepo;
import com.masai.repository.CurrentAdminSessionRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userDao;
	
	//@Autowired
	//private AdminRepo adminDao;
	
	@Autowired
	private CurrentUserSessionRepo cusDao;
	
	@Autowired
	private CurrentAdminSessionRepo casDao;
	
	
	
	
	@Override
	public User addUser(User user) throws UserException {
		
			User us= userDao.findByContact(user.getContact());
			
			if(us!=null) {
				throw new UserException("User already exists!");
			}else {
				return userDao.save(user);
			}
		
		
	}

	@Override
	public User updateUser(User user, String key) throws UserException {
		CurrentUserSession currUser= cusDao.findByUuid(key);
		
		if(currUser !=null) {
			 Optional<User> opt= userDao.findById(user.getUserLoginId());
			 
			 if(opt.get().getUserLoginId() != currUser.getUserId()) {
				 throw new UserException("Invalid Request! Please provide correct userLoginId!");
			 }
			 if(opt.isPresent()) {
				 return userDao.save(user);
			 }else {
				 throw new UserException("User not registered!");
			 }
		}else {
			throw new UserException("Invalid key!");
		}
	}

	@Override
	public User deleteUser(int userId, String key) throws UserException{
//		CurrentAdminSession loggedInAdmin= casDao.findByUuid(key);
		CurrentUserSession loggedInUser= cusDao.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please check key, No User loggedIn with given key");
		}
		
		Optional<User> opt = userDao.findById(userId);
		
		if(opt.isPresent()) {
			userDao.deleteById(userId);
			
			User u= opt.get();
			return u;
		}else {
			throw new UserException("User Not Found!");
		}
		
	}

	@Override
	public User viewUser(int userID) throws UserException {
		Optional<User> opt=  userDao.findById(userID) ;
		
		if(opt.isPresent()) {
			 return opt.get() ;
		}else {
			throw new UserException("No user found with id = "+userID) ;
		}
	}

	@Override
	public List<User> viewAllUsers() throws UserException {
		List<User> userList =  userDao.findAll() ;
		
		if(userList.size() != 0) {
			 return userList ;
		}else {
			throw new UserException("No users found!") ;
		}
	}

}

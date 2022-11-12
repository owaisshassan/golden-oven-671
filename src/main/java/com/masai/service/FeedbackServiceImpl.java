package com.masai.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BusException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.UserException;
import com.masai.model.Bus;
import com.masai.model.CurrentUserSession;
import com.masai.model.Feedback;
import com.masai.model.User;
import com.masai.repository.BusRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.FeedbackRepo;
import com.masai.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepo feedbackDao;
	
	@Autowired
	private CurrentUserSessionRepo cusDao;
	
	@Autowired
	private UserRepo userDao;
	
	@Autowired
	private BusRepo busDao;
	
	
	
	@Override
	public Feedback addFeedBack(Feedback feedback, Integer busId, Integer userid, String key) throws FeedbackException, UserException, BusException{
		
		CurrentUserSession currentLoggedInUser = cusDao.findByUuid(key);
		
		if(currentLoggedInUser == null)
			throw new UserException("Please provide a valid key!");
		
		else {
			Optional<User> userOpt = userDao.findById(userid);
			
			if(userOpt.isPresent()) {
				User u=userOpt.get();
				
				if(u.getUserLoginId()== currentLoggedInUser.getUserId()) {
					Optional<Bus> busOpt= busDao.findById(busId);
					
					if(busOpt.isPresent()) {
						
						feedback.setBus(busOpt.get());
						feedback.setUser(u);
						feedback.setFeedbackdatetime(LocalDateTime.now());;
						Feedback savedFeedback = feedbackDao.save(feedback);

						return savedFeedback;
						
					}else {
						throw new BusException("Bus with id "+busId+" not present");
					}
				}else {
					throw new UserException("User not loggedIn");
				}
			}else 
				throw new UserException("User Id not valid");
		}
		
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback, String key) throws FeedbackException, UserException {

		CurrentUserSession currentLoggedInUser = cusDao.findByUuid(key);
		
		if(currentLoggedInUser == null)
			throw new UserException("Please provide a valid key!");
		
		else {
			Optional<Feedback> feedbackOpt= feedbackDao.findById(feedback.getFeedBackId());
			
			if(feedbackOpt.isPresent()) {
				
				User u = feedbackOpt.get().getUser();
				Bus b = feedbackOpt.get().getBus();
				
				if(u.getUserLoginId() == currentLoggedInUser.getUserId()) {
					
					feedback.setUser(u);
					feedback.setBus(b);
					
					return  feedbackDao.save(feedback);
					
				}else {
					throw new UserException("User details invalid, Please Login first!");
				}
	
			}else {
				throw new FeedbackException("Feedback does not exist");
			}
		}
		
	}

	@Override
	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException {
		
		Optional<Feedback> optFeedback = feedbackDao.findById(feedbackId);
		
		if(optFeedback.isPresent())
			return optFeedback.get();
		
		throw new FeedbackException("Feedback does not exist for id: " + feedbackId);
	}

	@Override
	public List<Feedback> viewAllFeedBack() throws FeedbackException {
		
		List<Feedback> feedbackList =  feedbackDao.findAll();
		
		if(feedbackList.size() == 0)
			throw new FeedbackException("No feedbacks present in database");
		
		return feedbackList;
	}
	
}

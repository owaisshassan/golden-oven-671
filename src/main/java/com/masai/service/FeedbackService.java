package com.masai.service;

import java.util.List;

import com.masai.exceptions.BusException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.UserException;
import com.masai.model.Feedback;

public interface FeedbackService {
	
	public Feedback addFeedBack(Feedback feedback, Integer busId, Integer userid, String key) throws FeedbackException,UserException,BusException;
	
	public Feedback updateFeedBack(Feedback feedback, String key) throws FeedbackException,UserException;
	
	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException;
	
	public List<Feedback> viewAllFeedBack() throws FeedbackException;
		
}

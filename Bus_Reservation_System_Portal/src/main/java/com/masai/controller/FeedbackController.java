package com.masai.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.BusException;
import com.masai.exceptions.FeedbackException;
import com.masai.exceptions.UserException;
import com.masai.model.Feedback;
import com.masai.service.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/feedbacks/{userId}/{busId}")
	public ResponseEntity<Feedback> registerFeedback(@Valid @RequestBody Feedback feedback, @PathVariable("userId") Integer uId, @PathVariable("busId") Integer bId,@RequestParam(required = false) String key ) throws FeedbackException, UserException, BusException{
		feedback.setFeedbackdatetime(LocalDateTime.now());
		return new ResponseEntity<Feedback>(feedbackService.addFeedBack(feedback, bId, uId,key),HttpStatus.CREATED);
	}
	
	@PutMapping("/feedbacks")
	public ResponseEntity<Feedback> updateFeedback(@Valid @RequestBody Feedback feedback,@RequestParam(required = false) String key ) throws FeedbackException, UserException{
		feedback.setFeedbackdatetime(LocalDateTime.now());
		return new ResponseEntity<Feedback>(feedbackService.updateFeedBack(feedback,key),HttpStatus.OK);
			
	}
	
	@GetMapping("/feedbacks/{id}")
	public ResponseEntity<Feedback> getFeedback(@PathVariable("id") Integer feedbackId) throws FeedbackException{
		return new ResponseEntity<Feedback>(feedbackService.viewFeedBack(feedbackId),HttpStatus.OK);
		
	}
	
	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> getAllFeedback() throws FeedbackException{
		List<Feedback> feedbackList = feedbackService.viewAllFeedBack();
		return new ResponseEntity<List<Feedback>>(feedbackList,HttpStatus.OK);
	}
}

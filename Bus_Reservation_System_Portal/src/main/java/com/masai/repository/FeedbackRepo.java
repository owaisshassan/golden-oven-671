package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
	

	
}

package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer>{
	
	
}

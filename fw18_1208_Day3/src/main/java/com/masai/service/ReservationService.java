package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exceptions.ReservationException;
import com.masai.model.Reservation;

public interface ReservationService {
	public Reservation addReservation(Reservation reservation,Integer userId,String key) throws ReservationException;
	public Reservation updateReservation(Reservation reservation, Integer userId,String key) throws ReservationException;
	public Reservation deleteReservation(Integer reservationId,Integer userId,String key) throws ReservationException;
	public Reservation viewReservation(Integer reservationId) throws ReservationException;
	public List<Reservation> viewAllReservation() throws ReservationException;
//	public List<Reservation> getAllReservation(LocalDate date) throws ReservationException;
	
}

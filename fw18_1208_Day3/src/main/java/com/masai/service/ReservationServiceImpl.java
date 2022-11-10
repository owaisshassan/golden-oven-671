package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BusException;
import com.masai.exceptions.ReservationException;
import com.masai.exceptions.UserException;
import com.masai.model.Bus;
import com.masai.model.CurrentUserSession;
import com.masai.model.Reservation;
import com.masai.model.ReservationStatus;
import com.masai.model.User;
import com.masai.repository.BusRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.ReservationRepo;
import com.masai.repository.UserRepo;
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationRepo reservationDao;
	
	@Autowired
	private BusRepo busDao;
	
	@Autowired
	private UserRepo userDao;

	@Autowired
	private CurrentUserSessionRepo usDao;

	@Override
	public Reservation addReservation(Reservation reservation, Integer userId, String key)  {
		Reservation r = null;
		
		CurrentUserSession loggedInUser= usDao.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key");
		}
		else {
			
			Optional<User> userOpt = userDao.findById(userId);
			if(userOpt.isEmpty()) {
				throw new UserException("No User present with given id");
			}
			if(userOpt.get().getUserLoginId() == loggedInUser.getUserId()) {
				List<Bus> busList = busDao.findByRouteFromAndRouteTo(reservation.getSource(), reservation.getDestination());
				
//				busDao.findByRouteFromAndRouteTo(reservation.getSource(), key)
				if(userOpt.isEmpty()) {
					throw new UserException("Invalid user Id");
				}
				else if(busList.size()==0){
					throw new BusException("Sorry No buses present in that route to reserve");
				}
				else {
					for(Bus b : busList) {
						if(b.getAvailableSeats()>0) {
							b.setAvailableSeats(b.getAvailableSeats()-reservation.getNo_of_reservation());
							User u = userOpt.get();
							reservation.setBus(b);
							reservation.setReservationStatus(ReservationStatus.BOOKED.name());
							u.setReservation(reservation);
							r = reservationDao.save(reservation);
							break;
						}
					}
				}
				
				if(r==null) {
					throw new ReservationException("No seats left to Book... update Failed");
				}
				
				return r;
			}
			else {
				throw new UserException("Invalid user details, Please Login first ");
			}
		}
	}

	@Override
	public Reservation updateReservation(Reservation reservation, Integer userId, String key) throws ReservationException {
		Reservation r = null;
		CurrentUserSession loggedInUser= usDao.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key");
		}
		else {
			Optional<User> userOpt = userDao.findById(userId);
			if(userOpt.isEmpty()) {
				throw new UserException("No User present with given id");
			}
			if(userOpt.get().getUserLoginId() == loggedInUser.getUserId()) {
				List<Bus> busList = busDao.findByRouteFromAndRouteTo(reservation.getSource(), reservation.getDestination());
				
				if(userOpt.isEmpty()) {
					throw new UserException("Invalid user Id");
				}
				else if(busList.size()==0){
					throw new BusException("Sorry No buses present in that route to reserve");
				}
				else {
					for(Bus buses : busList) {
						if(buses.getAvailableSeats()>0) {
							Optional<Reservation> reOpt = reservationDao.findById(reservation.getReservationId());
							Reservation re = reOpt.get();
							buses.setAvailableSeats(buses.getAvailableSeats()+re.getNo_of_reservation());
							buses.setAvailableSeats(buses.getAvailableSeats()-reservation.getNo_of_reservation());
							User u = userOpt.get();
							reservation.setBus(buses);
							reservation.setReservationStatus(ReservationStatus.BOOKED.name());
							u.setReservation(reservation);
							r = reservationDao.save(reservation);
							break;
						}
					}
				}
				
				if(r==null) {
					throw new ReservationException("No seats left to Book... update Failed");
				}
			}
			else {
				throw new UserException("Invalid user details, Please Login first ");
			}
		}
		return r;
		
	}

	@Override
	public Reservation deleteReservation(Integer reservationId, Integer userId, String key) throws ReservationException {
		CurrentUserSession loggedInUser= usDao.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key");
		}
		else {
			Optional<Reservation> rOpt = reservationDao.findById(reservationId);
			if(rOpt.isPresent()) {
				Optional<User> userOpt = userDao.findById(userId);
				if(userOpt.isEmpty()) {
					throw new UserException("No User present with given id");
				}
				User u = userOpt.get();
				if(userOpt.get().getUserLoginId() == loggedInUser.getUserId()) {
					Reservation r = rOpt.get();
					
		//			Optional<Bus> busOpt = busDao.findById(r.getBus());
		//			 Bus b = busOpt.get();
					Bus b = r.getBus();
//					 b.setAvailableSeats(b.getAvailableSeats()+r.getNo_of_reservation());
					b.setAvailableSeats(b.getAvailableSeats()+r.getNo_of_reservation());
					r.setReservationStatus(ReservationStatus.CANCELED.name());
					r.setBus(null);
					
						u.setReservation(null);
				
					reservationDao.delete(r);
					return r;
				}
				else {
					throw new UserException("Invalid user details, Please Login first ");
				}
			}
			else {
				throw new ReservationException("No reservation found with Id : "+reservationId);
			}
		}
		
	}

	@Override
	public Reservation viewReservation(Integer reservationId) throws ReservationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> viewAllReservation() throws ReservationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
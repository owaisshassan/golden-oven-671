package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.BusException;
import com.masai.exceptions.RouteException;
import com.masai.model.Bus;
import com.masai.model.CurrentAdminSession;
import com.masai.model.Route;
import com.masai.repository.BusRepo;
import com.masai.repository.CurrentAdminSessionRepo;
import com.masai.repository.RouteRepo;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private BusRepo busDao;
	
	@Autowired
	private RouteRepo routeDao;
	

	@Autowired
	private CurrentAdminSessionRepo casDao;

	
	
	
	
	
	
	@Override
	public Bus addBus(Bus bus, String key) throws BusException, AdminException {
		System.out.println(bus);
		CurrentAdminSession loggedInAdmin= casDao.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key to add bus!");
		}
		System.out.println("bus route from= "+bus.getRouteFrom());
		System.out.println("bus route to= "+bus.getRouteTo());
		Route route=routeDao.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
		System.out.println(route);
		if(route != null) {
			route.getBusList().add(bus);
			bus.setRoute(route);
			return busDao.save(bus);
		}
		else
			throw new BusException("Bus detail is not correct");
	}

	@Override
	public Bus updateBus(Bus bus, String key) throws BusException, AdminException, RouteException {
		CurrentAdminSession loggedInAdmin= casDao.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new AdminException("Looks like admin has not logedin or input key in incorrect, Please check");
		}
		else {
			Optional<Bus> opt = busDao.findById(bus.getBusId());
			if(opt.isPresent()) {
				Bus existingBus = opt.get();
				
			Route r = routeDao.findByRouteFromAndRouteTo(bus.getRouteFrom(),bus.getRouteTo());
				if(r!=null) {
					List<Bus> list = r.getBusList();
					list.add(bus);
					bus.setRoute(r);
				}
				else {
					throw new RouteException("No such route found");
				}
				
				busDao.save(bus);
				return existingBus;
			}
			else {
				throw new BusException("No Bus found with given details");
			}
		}
	}

	@Override
	public Bus deleteBus(int busId, String key) throws BusException, AdminException {
		// TODO Auto-generated method stub
		CurrentAdminSession loggedInAdmin= casDao.findByUuid(key);
		if(loggedInAdmin == null) {
			throw new AdminException("Looks like admin has not logedin or input key in incorrect, Please check");
		}
		else {
			Optional<Bus> opt = busDao.findById(busId);
			if(opt.isPresent()) {
				Bus b = opt.get();
				b.setRoute(null);
				busDao.delete(b);
				return b;
			}
			else {
				throw new BusException("No Bus present with given id : "+busId);
			}
		}
		
	}

	@Override
	public Bus viewBus(int busId) throws BusException {
		
		Optional<Bus> opt = busDao.findById(busId);
		if(opt.isPresent()) {
			Bus b = opt.get();
			return b;
		}
		else {
			throw new BusException("No Bus present with given id : "+busId);
		}
	}

	@Override
	public List<Bus> viewBusByType(String busType) throws BusException {
		
		
		List<Bus> b = busDao.findByBusType(busType);
		if(b.size()>0) {
			return b;
		}
		else {
			throw new BusException("No Bus present with given id : "+busType);
		}
	}

	@Override
	public List<Bus> viewAllBus() throws BusException {
		
		List<Bus> b =busDao.findAll();
		if(b.size()>0) {
			return b;
		}
		else {
			throw new BusException("No Buses present");
		}
		
	}

	
}

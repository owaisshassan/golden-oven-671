package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.RouteException;
import com.masai.model.Bus;
import com.masai.model.CurrentAdminSession;
import com.masai.model.Route;
import com.masai.repository.AdminRepo;
import com.masai.repository.BusRepo;
import com.masai.repository.CurrentAdminSessionRepo;
import com.masai.repository.RouteRepo;


@Service
public class RouteServiceImpl implements RouteService{

	
	@Autowired
	private BusRepo busDao;
	
	@Autowired
	private RouteRepo routeDao;
	
	@Autowired
	private AdminRepo adminDao;
	
	@Autowired
	private CurrentAdminSessionRepo casDao;
	
	
	@Override
	public Route addRoute(Route route, String key) throws RouteException {
		
CurrentAdminSession currAdminLogin = casDao.findByUuid(key);
		
		if(currAdminLogin == null) {
			throw new AdminException("Input key is incorrect, please try again...");
		}
		else {
			Route r = routeDao.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
			if(r==null) {
				List<Bus> busList = route.getBus();
				System.out.println("Size is : "+busList.size());
				
				for(int i=0;i<busList.size();i++) {
					busList.get(i).setRouteFrom(route.getRouteFrom());
					busList.get(i).setRouteTo(route.getRouteTo());
					busList.get(i).setRoute(route);
					busDao.save(busList.get(i));
				}
				
				return routeDao.save(route);
			}
			else {
				throw new RouteException("Route already exists....");
			}
		}
	}
	
//----------------------------------------------------------------------------
	
	@Override
	public Route updateRoute(Route route, String key) {
CurrentAdminSession currAdminLogin = casDao.findByUuid(key);
		
		if(currAdminLogin == null) {
			throw new AdminException("Input key in incorrect, Please try again...");
		}
		else {
		
			Optional<Route> opt = routeDao.findById(route.getRouteId());
			if(opt.isPresent()) {
				
				List<Bus> busList = opt.get().getBus();
				
				for(Bus b: busList) {
					b.setRouteFrom(route.getRouteFrom());
					b.setRouteTo(route.getRouteTo());
					
				}
				
				return routeDao.save(route);
			}
			else {
				throw new RouteException("This route is not present to update...");
			}
		}
	}

	
//-------------------------------------------------------------------------------
	
	@Override
	public Route deleteRoute(int routeId, String key) {
CurrentAdminSession currAdminLogin = casDao.findByUuid(key);
		
		if(currAdminLogin == null) {
			throw new AdminException("Input key in incorrect, Please try again...");
		}
		else {
			Optional<Route> opt = routeDao.findById(routeId);
			if(opt.isPresent()) {
				Route existingRoute = opt.get();
				routeDao.delete(existingRoute);
				return existingRoute;
			}
			else {
				throw new RouteException("Route not present with id : "+routeId+"...");
			}
		}
	}

	
//-------------------------------------------------------------------------------
	
	@Override
	public Route viewRoute(int routeId) throws RouteException {
		Optional<Route> opt = routeDao.findById(routeId);
		if(opt.isPresent()) {
			Route r = opt.get();
			return r;
		}
		else {
			throw new RouteException("Route not present with id : "+routeId+"...");
		}
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		List<Route> list = routeDao.findAll();
		if(list.size()>0) {
			return list;
		}
		else {
			throw new RouteException("Route not present");
		}
	}
}

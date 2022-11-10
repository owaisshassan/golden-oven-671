package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Bus;

public interface BusRepo extends JpaRepository<Bus, Integer> {

public Bus findByDriverName(String driverName);
	
	public List<Bus> findByBusType(String busType);
	
	public List<Bus> findByRouteFromAndRouteTo(String routeFrom,String routeTo);
}

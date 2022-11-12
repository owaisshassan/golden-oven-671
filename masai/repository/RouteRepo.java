package com.masai.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {
	
	//@Query("select r from Route r where r.routeFrom=?1 AND r.routeTo=?2")
	public Route findByRouteFromAndRouteTo(String routeFrom,String routeTo);

}

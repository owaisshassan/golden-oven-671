package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.RouteException;
import com.masai.model.Route;
import com.masai.service.RouteService;



@RestController
public class RouteController {

	
	@Autowired
	private RouteService routeService;

//  http://localhost:8888/routes
	@PostMapping("/routes")
	public ResponseEntity<Route> createRoute(@Valid @RequestBody Route route,@RequestParam(required = false) String key ) throws RouteException, AdminException {
		
		Route r= routeService.addRoute(route, key);
		
		return new ResponseEntity<Route>(r,HttpStatus.CREATED);
	}
	
	
//  http://localhost:8888/routes	
	@PutMapping("/routes")
	public ResponseEntity<Route> updateIt(@RequestBody Route route,@RequestParam(required = false) String key) throws RouteException, AdminException{
		return new ResponseEntity<Route>(routeService.updateRoute(route,key),HttpStatus.ACCEPTED);

	}

//  http://localhost:8888/routes/routeID	
	@DeleteMapping("routes/{routeId}")
	public ResponseEntity<Route> deleteIt(@PathVariable("routeId") Integer Id, @RequestParam(required = false) String key) throws RouteException, AdminException{
		return new ResponseEntity<Route>(routeService.deleteRoute(Id,key), HttpStatus.OK);

	}
	
	
//  http://localhost:8888/routes/routeID	
	@GetMapping("routes/{routeId}")
	public ResponseEntity<Route> viewItbyId(@PathVariable("routeId") Integer Id) throws RouteException{
		return new ResponseEntity<Route>(routeService.viewRoute(Id), HttpStatus.OK);

	}
	

//  http://localhost:8888/routes	
	@GetMapping("routes")
	public ResponseEntity<List<Route>> viewAll() throws RouteException{
		return new ResponseEntity<List<Route>>(routeService.viewAllRoute(), HttpStatus.OK);

	}
}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Route;
import com.masai.service.RouteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RouteController {

	
	@Autowired
	private RouteService routeService;
	
	@PostMapping("/addRoute")
	public ResponseEntity<Route> createRoute(@Valid @RequestBody Route route,@RequestParam(required = false) String key ) {
		return new ResponseEntity<Route>(routeService.addRoute(route,key),HttpStatus.CREATED);

	}
	
	@PutMapping("/updateRoute")
	public ResponseEntity<Route> updateIt(@Valid @RequestBody Route route,@RequestParam(required = false) String key){
		return new ResponseEntity<Route>(routeService.updateRoute(route,key),HttpStatus.ACCEPTED);

	}
	
	@DeleteMapping("deleteRoute/{routeId}")
	public ResponseEntity<Route> deleteIt(@PathVariable("routeId") Integer Id, @RequestParam(required = false) String key){
		return new ResponseEntity<Route>(routeService.deleteRoute(Id,key), HttpStatus.OK);

	}
	
	@GetMapping("route/{routeId}")
	public ResponseEntity<Route> viewItbyId(@PathVariable("routeId") Integer Id){
		return new ResponseEntity<Route>(routeService.viewRoute(Id), HttpStatus.FOUND);

	}
	
	
	@GetMapping("routes")
	public ResponseEntity<List<Route>> viewAll(){
		return new ResponseEntity<List<Route>>(routeService.viewAllRoute(), HttpStatus.OK);

	}
}

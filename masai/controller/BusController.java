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
import com.masai.exceptions.BusException;
import com.masai.exceptions.RouteException;
import com.masai.model.Bus;
import com.masai.service.BusService;



@RestController
public class BusController {
	
	@Autowired
	private BusService bService;
	
	@PostMapping("/buses")
	public ResponseEntity<Bus> createBus(@Valid @RequestBody Bus bus,@RequestParam(required = false) String key) throws BusException, RouteException, AdminException {
		return new ResponseEntity<Bus>(bService.addBus(bus,key),HttpStatus.CREATED);
//		http://localhost:8888/buses     admin key
	}
	
	@PutMapping("/buses")
	public ResponseEntity<Bus> updateIt(@RequestBody Bus bus,@RequestParam(required = false) String key) throws BusException, AdminException, RouteException{
		return new ResponseEntity<Bus>(bService.updateBus(bus,key),HttpStatus.ACCEPTED);
//		http://localhost:8888/buses
	}
	
	@DeleteMapping("/buses/{busId}")
	public ResponseEntity<Bus> deleteIt(@PathVariable("busId") Integer Id,@RequestParam(required = false) String key) throws BusException, AdminException, RouteException{
		return new ResponseEntity<Bus>(bService.deleteBus(Id,key), HttpStatus.OK);
//		http://localhost:8888/buses/busID
	}
	
	@GetMapping("/buses/{busId}")
	public ResponseEntity<Bus> viewItbyId(@PathVariable("busId") Integer Id) throws BusException{
		return new ResponseEntity<Bus>(bService.viewBus(Id), HttpStatus.ACCEPTED);
//		http://localhost:8888/buses/busID
	}
	
	@GetMapping("/busByType/{busType}")
	public ResponseEntity<List<Bus>> viewItbyType(@PathVariable("busType") String type) throws BusException{
		return new ResponseEntity<List<Bus>>(bService.viewBusByType(type), HttpStatus.FOUND);
//		http://localhost:8888/busByType/busType
	}
	
	@GetMapping("/buses")
	public ResponseEntity<List<Bus>> viewAll() throws BusException{
		return new ResponseEntity<List<Bus>>(bService.viewAllBus(), HttpStatus.OK);
//		http://localhost:8888/buses
	}

}

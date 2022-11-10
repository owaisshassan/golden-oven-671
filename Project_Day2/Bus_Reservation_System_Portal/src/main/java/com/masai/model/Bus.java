package com.masai.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	
	@NotNull(message = "busName should not be null")
	@Pattern(regexp = "^[a-zA-Z]$", message = "Please type alphabetic Bus Name only!")
	@Size(min = 3, max = 12, message = "Bus name length should be min 3 and max 12")
	private String busName;
	
	@NotNull(message = "Driver name should not be null")
	@Pattern(regexp="^[a-zA-Z_]+( [a-zA-Z_]+)*$", message = "Please type alphabetic Driver Name only")
	@Size(min=3,max=16, message = "Driver name should be of size min=3 and max=16")
	private String driverName;
	
	@NotNull(message = "busType should not be null")
	@Pattern(regexp="^[a-zA-Z_]+( [a-zA-Z_]+)*$", message = "busType should be alphabetic only!")
	@Size(min=2,max=12, message = "busType should be of size between 2 to 12 charcters")
	private String busType;
	
	@NotNull(message = "Please don't pass null value")
	@Pattern(regexp="^[a-zA-Z_]+( [a-zA-Z_]+)*$", message = "routeFrom should be alphabetic only")
	@Size(min=2,max=14, message = "routeFrom should be of size between 2 to 14 charcters")
	private String routeFrom;
	
	@NotNull(message = "routeTo should not be null")
	@Pattern(regexp="^[a-zA-Z_]+( [a-zA-Z_]+)*$", message = "routeTo should contain only letters")
	@Size(min=2,max=20, message = "routeTo should be of size between 2 to 20 charcters")
	private String routeTo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss[.SSS][.SS][.S]")
	private LocalTime arrivalTime;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss[.SSS][.SS][.S]")
	private LocalTime departureTime;
	
	@NotNull(message = "seats should not be null")
	@Range(min=12,max=28, message = "seats should be of between 12 to 28")
	private Integer seats;
	
	@NotNull(message = "availableSeats should not be null")
	@Range(min=0,max=28, message = "availableSeats should be of between 0 to 28")
	private Integer availableSeats;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Route route;
	
	
	
	
	
	
}

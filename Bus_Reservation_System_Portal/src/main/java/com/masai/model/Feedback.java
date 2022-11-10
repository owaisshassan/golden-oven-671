package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Feedback {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedBackId;
	
	@NotNull(message = "driverRating cannot be null!")
	private Integer driverRating;
	
	@NotNull(message = "serviceRating cannot be null!")
	private Integer serviceRating;
	
	@NotNull(message = "overallRating cannot be null!")
	private Integer overallRating;
	
	
	private String comments;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime feedbackdatetime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userID")
	private User user;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="busID")
	private Bus bus;

	
	
}

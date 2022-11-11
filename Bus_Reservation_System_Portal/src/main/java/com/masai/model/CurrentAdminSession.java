package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentAdminSession {
	
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer AdminId;
	
	private String uuid;
	private LocalDateTime localDateTime;

}

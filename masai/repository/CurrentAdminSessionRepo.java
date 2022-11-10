package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CurrentAdminSession;

public interface CurrentAdminSessionRepo extends JpaRepository<CurrentAdminSession, Integer> {
	
	public CurrentAdminSession findByUuid(String uuid);

}

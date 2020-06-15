package com.amdocs.media.assignement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.media.assignement.model.UserLoginDAO;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginDAO, String>  {
	
	UserLoginDAO findByUsername(String username);

	
}

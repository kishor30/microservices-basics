package com.uxpsystems.assignment.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.uxpsystems.assignment.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	UserProfile findByUsername(String username);
}

package com.uxpsystems.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.uxpsystems.assignment.model.UserProfile;
import com.uxpsystems.assignment.service.UserProfileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserProfileController {
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	private Environment env;
	
	
	@GetMapping("api/profiles")
	public List<UserProfile> getAllUserProfiles() {
		log.info("getting profiles");
		return userProfileService.getAllUserProfiles();
	}


	
	
	@GetMapping("api/profile/{userid}")
	public UserProfile getUserProfile(@PathVariable("userid") String UserProfileId) {
		log.info("getting profiles by ID");
		return userProfileService.getUserProfile(UserProfileId);

	}

	@DeleteMapping("api/profile/{userid}")
	public List<UserProfile> deleteUserProfile(@PathVariable("userid") String UserProfileId) {
		log.info("removing profiles");
		userProfileService.removeUserProfile(UserProfileId);
		return userProfileService.getAllUserProfiles();

	}


	@PutMapping("api/profile/{userid}")
	public List<UserProfile> updateProfile(@PathVariable("userid") String userProfileId, @RequestBody UserProfile userProfile) {
		log.info("updating users as ID:"+userProfileId+"and updations are as:"+userProfile);
		//userProfileService.updateUserProfile(userProfileId,userProfile);
		log.info("all Users:",userProfileService.getAllUserProfiles());
		System.out.println("all users:"+userProfileService.getAllUserProfiles());
		return userProfileService.getAllUserProfiles();

	}

	@PostMapping("api/profile")
	public List<UserProfile> addProfile(@RequestBody UserProfile userProfile) {
		log.info("adding users");
		userProfileService.addUserProfile(userProfile);
		return userProfileService.getAllUserProfiles();

	}
}

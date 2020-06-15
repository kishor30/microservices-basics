package com.amdocs.media.assignement.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amdocs.media.assignement.model.UserProfileDTO;

@Service
@FeignClient(name="profile-service")
public interface UserAuthServiceFeign {
	
	@PostMapping("api/profile")
	List<UserProfileDTO> addProfile(@RequestBody UserProfileDTO userProfile);
	
	@PutMapping("api/profile/{username}")
	List<UserProfileDTO> updateProfile(@PathVariable("username") String userProfileName, @RequestBody UserProfileDTO userProfile);
	
	@DeleteMapping("api/profile/{username}")
    List<UserProfileDTO> deleteUserProfile(@PathVariable("username") String UserProfileName);
	
	@GetMapping("api/profiles")
	List<UserProfileDTO> getAllUserProfiles();
	
	

}

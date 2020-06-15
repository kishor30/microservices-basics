package com.amdocs.media.assignement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amdocs.media.assignement.model.UserLoginDAO;
import com.amdocs.media.assignement.model.UserProfileDTO;
import com.amdocs.media.assignement.service.UserAuthServiceFeign;
import com.amdocs.media.assignement.service.UserLoginService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserAuthServiceFeign userauthServiceFeign;
	
	
	@DeleteMapping("profile/{username}")
	@PreAuthorize("#UserProfileName == authentication.principal.username")
	public List<UserProfileDTO> deleteUserProfile(@PathVariable("username") String UserProfileName,@RequestBody UserProfileDTO userprofile) {
		System.out.println("user to be deleted is:"+UserProfileName);
		String kafkaTopic = "deleteTopic";
		userLoginService.send(userprofile,kafkaTopic);
		//userauthServiceFeign.deleteUserProfile(UserProfileName);
		return userauthServiceFeign.getAllUserProfiles();

	}


	@PutMapping("profile/{username}")
	@PreAuthorize("#userProfileName == authentication.principal.username")
	public List<UserProfileDTO> updateProfile(@PathVariable("username") String userProfileName, @RequestBody UserProfileDTO userProfile) {
		log.info("authenticated and sending call to profiles");
		System.out.println("sending user with username as:"+userProfileName+"and UserDetails as:"+userProfile);
		String kafkaTopic = "updateTopic";
		userLoginService.send(userProfile,kafkaTopic);
		//userauthServiceFeign.updateProfile(userProfileName, userProfile);
		log.warn("profiles after update",userauthServiceFeign.getAllUserProfiles() );
		System.out.println("users after updation are:"+userauthServiceFeign.getAllUserProfiles());
		return userauthServiceFeign.getAllUserProfiles();

	}

	@PostMapping("/profile")
	@PreAuthorize("#userProfile.username == principal.username")
	public List<UserProfileDTO> addProfile(@RequestBody UserProfileDTO userProfile) {
	
		userauthServiceFeign.addProfile(userProfile);
		return userauthServiceFeign.getAllUserProfiles();

	}
	
	
	
	
	@GetMapping("/getUser/{username}")
	@PreAuthorize("#username == principal.username")
	public String getKishor() {
		return "Hey Kishor";
	}
	
	@PostMapping("/userlogin")
	public String Userlogin(@RequestBody UserLoginDAO UserDetails) {


	System.out.println("nameKishor");
	
	UserDetails.setPassword(bCryptPasswordEncoder.encode(UserDetails.getPassword()));
	System.out.println("encoded"+UserDetails.getPassword());
	userLoginService.saveUsers(UserDetails);
	return "Kishor";
	}
	
	@PostMapping("/userloginwithPass")
	public String UserloginwithPass(@RequestBody UserLoginDAO UserDetails) {



	return "Welcome:"+UserDetails.getUsername();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/users")
	public List<Object> getUserfromProfile() {
		
		// get list of available images 
		return 	restTemplate.getForObject("http://profile-service/profiles", List.class);
				
	
	}
	
	
	

}

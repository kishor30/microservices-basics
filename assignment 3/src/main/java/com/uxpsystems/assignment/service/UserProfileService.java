package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.uxpsystems.assignment.dao.UserProfileRepository;
import com.uxpsystems.assignment.model.UserProfile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepo;
	

	public boolean addUserProfile(UserProfile profile) {
		// TODO Auto-generated method stub
		return userProfileRepo.save(profile) == profile;
	}


	public boolean removeUserProfile(String UserProfileName) {
		UserProfile profile = userProfileRepo.findByUsername(UserProfileName);

		if (profile != null) {
			userProfileRepo.delete(profile);
			return true;
		}

		return false;
	}

	@KafkaListener(topics = "updateTopic", groupId = "group_id")
	public boolean updateUserProfile(byte[] UserProfileName) {
		System.out.println("userto be  updated:"+UserProfileName);
		log.info("##########consumed messages are:############",UserProfileName);
		
		String uname = new String(UserProfileName);
		Gson g = new Gson();
		UserProfile p = g.fromJson(uname, UserProfile.class);
		System.out.println("kafkaResult:::"+p);
		/*
		 * log.warn("user to be updated", userProfile);
		 * System.out.println("input object:"+userProfile);
		 */

		UserProfile profile = userProfileRepo.findByUsername(p.getUsername());
		
		log.warn("user to be updated", profile);
		System.out.println(" user from db  updated:"+profile);
		if (profile != null) {
			userProfileRepo.save(p);
			log.warn("user exist and  updated as per ahead:", p);
			log.debug("printing the user fromdb:", p);
			System.out.println(" user updated:"+p);
			return true;
		}
		log.warn("user does not exist!", profile);
		log.debug("printing the non existing :", profile);
		System.out.println("no user updated:"+profile);
		return false;

	}

	@KafkaListener(topics = "deleteTopic", groupId = "group_id")
	public void consume(byte[] UserProfileName){
		System.out.println("userto be  deleted:"+UserProfileName);
		log.info("##########consumed messages are:############",UserProfileName);
		
		String uname = new String(UserProfileName);
		Gson g = new Gson();
		UserProfile p = g.fromJson(uname, UserProfile.class);
		System.out.println("kafkaResult:::"+p);
		UserProfile profile = userProfileRepo.findByUsername(p.getUsername());
		System.out.println("userto be  deleted:"+profile);
		if (profile != null) {
			userProfileRepo.delete(profile);
			log.info("##########deleted User:############",UserProfileName);
			System.out.println(" deleted user:"+UserProfileName);
		}
	
	}
	public UserProfile getUserProfile(String UserProfileName) {
		// TODO Auto-generated method stub
		return userProfileRepo.findByUsername(UserProfileName);
	}


	public List<UserProfile> getAllUserProfiles() {
		// TODO Auto-generated method stub
		return userProfileRepo.findAll();
	}
}

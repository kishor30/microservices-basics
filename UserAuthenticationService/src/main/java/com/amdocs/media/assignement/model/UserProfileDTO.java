package com.amdocs.media.assignement.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserProfileDTO {

	
	String username;

	String address;
	
	Long phoneNumber;

	
	public UserProfileDTO() {
		super();
	
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "UserProfileDTO [username=" + username + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
}


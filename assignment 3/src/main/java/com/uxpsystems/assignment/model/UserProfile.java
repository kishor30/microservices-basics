package com.uxpsystems.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="userProfile")
@Data
public class UserProfile {

	@Id
	@Column(name = "username")
	String username;
	
	@Column(name = "address")
	String address;

	@Column(name = "phoneNumber")
	Long phoneNumber;

	@Override
	public String toString() {
		return "UserProfile [username=" + username + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	/*
	 * public UserProfile() {
	 * 
	 * }
	 * 
	 * public UserProfile(String username,String address, Long phoneNumber) {
	 * super(); this.username = username; this.address = address; this.phoneNumber =
	 * phoneNumber; }
	 * 
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 * 
	 * public String getAddress() { return address; } public void setAddress(String
	 * address) { this.address = address; } public Long getPhoneNumber() { return
	 * phoneNumber; } public void setPhoneNumber(Long phoneNumber) {
	 * this.phoneNumber = phoneNumber; }
	 */
	
}

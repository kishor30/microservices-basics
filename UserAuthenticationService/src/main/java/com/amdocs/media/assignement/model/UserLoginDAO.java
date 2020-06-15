package com.amdocs.media.assignement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserLoginDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column(nullable = false, unique = true)
	String username;

	@Column(name = "password")
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public UserLoginDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserLoginDAO [userid=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	


}

package com.amdocs.media.assignement.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserLoginDAO user;
	  
	    public UserPrincipal(UserLoginDAO user) {
	        this.user = user;
	    }

	

		@Override
		public String toString() {
			return "UserPrincipal [user=" + user + "]";
		}



		

		@Override
		public String getPassword() {
			 return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			 return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			  return true;
		}

		@Override
		public boolean isEnabled() {
			 return true;
		}



		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
}

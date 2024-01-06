package com.oms.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.oms.entity.UserDetailsEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserContext extends User {

	UserDetailsEntity userDetailsEntity;
	
	private Long apartmentId;
	private Long userId;
	
	private String userFullName;
	
	public UserContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public UserContext(UserDetailsEntity userDetailsEntity, 
			Collection<? extends GrantedAuthority> authorities) {
		super(userDetailsEntity.getContactNo1(), userDetailsEntity.getPassword(), authorities);
		this.userDetailsEntity = userDetailsEntity;
		
		this.userId = userDetailsEntity.getId();
	}
	
	public String getUserFullName() {
		if(userDetailsEntity != null) {
			String firstName = userDetailsEntity.getFirstName() != null ? userDetailsEntity.getFirstName() : "";
			String lastName = userDetailsEntity.getLastName() != null ? userDetailsEntity.getLastName() : "";
			this.userFullName = firstName + " " + lastName;
			return this.userFullName.trim();
		} else {
			throw new RuntimeException("User not found on UserContext.");
		}
	}

}

package com.oms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oms.annotations.EntityMapping;
import com.oms.entity.UserDetailsEntity;
import com.oms.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityMapping(entity=UserDetailsEntity.class)
public class UserDetailsDTO {

	private Long id;	
	
	private String type;
	
	private String role;
	
	private String firstName;	
	
	private String middleName;	
	
	private String lastName;	
	
	private String aadharCardNo;	
	
	private String userAddress;
	
	private String contactNo1;	
	
	private String contactNo2;
	
	private String emailId;	
	
	private String password;	
	
	private Boolean isDeleted;
	
	private Boolean isActive;

	private Long createdBy;	

	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date createdDate;
	
	private Long modifiedBy;	
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date modifiedDate;
}

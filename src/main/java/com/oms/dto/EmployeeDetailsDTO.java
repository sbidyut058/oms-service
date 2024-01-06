/**
 * 
 */
package com.oms.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oms.annotations.EntityMapping;
import com.oms.entity.EmployeeDetailsEntity;
import com.oms.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author prady
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityMapping(entity=EmployeeDetailsEntity.class)
public class EmployeeDetailsDTO {
	
	private Long id;
	private String employeeCode;	
	private String firstName;		
	private String middleName;	
	private String lastName;
	private String emailId;	
	private String bloodGroup;
	private String state;
	private String country;
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date dateOfBirth;
	private String contactNumber;
	private String homeContact;
	private String address;
	private String gender;
	private String qualification;
	private String fatherName;
	private String motherName;
	private String maritalStatus;
	private String spouseName;
	private Long child;
	private String aadharCardNo;
	private String panCardNo;
	private String bankAccountNo;
	
	@JsonProperty("IFSCcode")
	private String IFSCcode;
	
	private String bankName;
	private String bankBranch;
	private Date dateOfJoining;
	private String post;
	private String employeeType;
	private Long salary;
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date dateOfResign;
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date releaseDate;
	
	private Boolean isDeleted;
	private Boolean isActive;
	private Long createdBy;	
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date createdDate;
	private Long modifiedBy;
	
	@JsonFormat(pattern = "yyyy-mm-dd", timezone = DateUtils.TIMEZONE_ASIA_KOLKATA, shape=JsonFormat.Shape.STRING)
	private Date modifiedDate;
}

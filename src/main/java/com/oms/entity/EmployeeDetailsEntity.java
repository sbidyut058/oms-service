/**
 * 
 */
package com.oms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.oms.constants.DBConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author prady
 *
 */
@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DBConstants.TABLE_EMPLOYEE_DETAILS)
public class EmployeeDetailsEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;
	
	@Column(name = "FIRST_NAME")
	private String firstName;	
	
	@Column(name = "MIDDLE_NAME")
	private String middleName;	
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "BLOOD_GROUP")
	private String bloodGroup;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name = "HOME_CONTACT")
	private String homeContact;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "QUALIFICATION")
	private String qualification;
	
	@Column(name = "FATHER_NAME")
	private String fatherName;
	
	@Column(name = "MOTHER_NAME")
	private String motherName;
	
	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;
	
	@Column(name = "SPOUSE_NAME")
	private String spouseName;
	
	@Column(name = "CHILD")
	private Long child;
	
	@Column(name = "AADHAAR_CARD_NO")
	private String aadharCardNo;
	
	@Column(name = "PAN_CARD_NO")
	private String panCardNo;
	
	@Column(name = "BANK_ACCOUNT_NO")
	private String bankAccountNo;
	
	@Column(name = "IFSC_CODE")
	private String IFSCcode;
	
	@Column(name = "BANK_NAME")
	private String bankName;
	
	@Column(name = "BANK_BRANCH")
	private String bankBranch;
	
	@Column(name = "DATE_OF_JOINING")
	private Date dateOfJoining;
	
	@Column(name = "POST")
	private String post;
	
	@Column(name = "EMPLOYEE_TYPE")
	private String employeeType;
	
	@Column(name = "SALARY")
	private Long salary;
	
	@Column(name = "DATE_OF_RESIGN")
	private Date dateOfResign;
	
	@Column(name = "RELEASE_DATE")
	private Date releaseDate;
	
	@Column(name = "IS_DELETED", columnDefinition = "boolean default false")
	private Boolean isDeleted;
	
	@Column(name = "IS_ACTIVE", columnDefinition = "boolean default true")
	private Boolean isActive;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;	
	
	@Column(name = "CREATED_DATE")
	@CreationTimestamp
	private Date createdDate;
	
	@Column(name = "MODIFIED_BY")
	private Long modifiedBy;	
	
	@Column(name = "MODIFIED_DATE")
	@UpdateTimestamp
	private Date modifiedDate;	
}

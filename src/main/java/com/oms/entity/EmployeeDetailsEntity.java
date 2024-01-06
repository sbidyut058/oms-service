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
	public Long id;
	
	
	
	// More fields need to add here.......
	
	
	
	
	@Column(name = "CREATED_BY")
	public Long createdBy;	
	
	@Column(name = "CREATED_DATE")
	@CreationTimestamp
	public Date createdDate;
	
	@Column(name = "MODIFIED_BY")
	public Long modifiedBy;	
	
	@Column(name = "MODIFIED_DATE")
	@UpdateTimestamp
	public Date modifiedDate;	
}

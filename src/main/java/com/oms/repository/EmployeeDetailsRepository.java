/**
 * 
 */
package com.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.entity.EmployeeDetailsEntity;

/**
 * @author prady
 *
 */
@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetailsEntity, Long>, CrudRepository<EmployeeDetailsEntity, Long>,JpaSpecificationExecutor<EmployeeDetailsEntity> {

}

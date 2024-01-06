package com.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oms.entity.UserDetailsEntity;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long>, CrudRepository<UserDetailsEntity, Long> {

	@Modifying
	@Query("UPDATE UserDetailsEntity f SET f.isDeleted = 1 WHERE f.id = :id")
	public void softDeleteById (Long id);
}

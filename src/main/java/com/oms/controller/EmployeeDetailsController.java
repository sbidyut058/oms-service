/**
 * 
 */
package com.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oms.dto.ApiResponseEntity;
import com.oms.dto.EmployeeDetailsDTO;
import com.oms.service.EmployeeDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author prady
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/employee_details")
public class EmployeeDetailsController {

	@Autowired
	private EmployeeDetailsService employeeDetailsService;

	@PostMapping(value = "/create_or_update")
	public ResponseEntity<ApiResponseEntity> createOrUpdate(@RequestBody EmployeeDetailsDTO employeeDetailsDTO) throws Exception {
		log.info("Started executing API createOrUpdate: {}", employeeDetailsDTO);
		return ResponseEntity.status(HttpStatus.OK).body(employeeDetailsService.saveOrUpdate(employeeDetailsDTO));		
	}
	
	@GetMapping(value = "/list_view/get")
	public ResponseEntity<ApiResponseEntity> getListView(@RequestParam(name="params", required = false) String reqParams) throws Exception {
		log.info("Started executing API getListView");		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDetailsService.getListView(reqParams));		
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<ApiResponseEntity> getById(@PathVariable Long id) throws Exception {
		log.info("Started executing API getById: {}", id);		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDetailsService.getById(id));		
	}
	
	@DeleteMapping(value = "/delete/multiple")
	public ResponseEntity<ApiResponseEntity> softDeleteMultipleById(@RequestBody List<Long> ids) {
		log.info("Started executing API softDeleteMultipleById: {}", ids);		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDetailsService.deleteMultipleById(ids));		
	}
}

/**
 * 
 */
package com.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/flat_details")
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
}

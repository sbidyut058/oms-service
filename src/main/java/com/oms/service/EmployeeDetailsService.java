package com.oms.service;

import com.oms.dto.ApiResponseEntity;
import com.oms.dto.EmployeeDetailsDTO;

public interface EmployeeDetailsService {

	ApiResponseEntity saveOrUpdate(EmployeeDetailsDTO employeeDetailsDTO);
	ApiResponseEntity getListView(String reqParams) throws Exception;
}

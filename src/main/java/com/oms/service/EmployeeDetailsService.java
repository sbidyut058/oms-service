package com.oms.service;

import java.util.List;

import com.oms.dto.ApiResponseEntity;
import com.oms.dto.EmployeeDetailsDTO;

public interface EmployeeDetailsService {

	ApiResponseEntity saveOrUpdate(EmployeeDetailsDTO employeeDetailsDTO);
	ApiResponseEntity getListView(String reqParams) throws Exception;
	ApiResponseEntity getById(Long id);
	ApiResponseEntity deleteMultipleById(List<Long> ids);
}

package com.oms.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.dbquery.DBQueryExecuter;
import com.oms.dbquery.QueryMaker;
import com.oms.dto.RequestParamDTO;
import com.oms.entity.EmployeeDetailsEntity;
import com.oms.repository.EmployeeDetailsRepository;
import com.oms.bean.UserContext;
import com.oms.utils.Utils;
import com.oms.constants.ApiConstants;
import com.oms.dto.ApiResponseEntity;
import com.oms.dto.EmployeeDetailsDTO;
import com.oms.service.EmployeeDetailsService;

import lombok.extern.slf4j.Slf4j;
/**
 * @author prady
 *
 */
@Slf4j
@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	DBQueryExecuter dbQueryExecuter;
	
	@Autowired
	EmployeeDetailsRepository employeeDetailsRepository;
	
	@Override
	public ApiResponseEntity saveOrUpdate(EmployeeDetailsDTO employeeDetailsDTO) {
		log.info("Started executing saveOrUpdate serviceImpl.");
//		UserContext userContext = Utils.getUserContext();
		
		EmployeeDetailsEntity employeeDetailsEntity = null;
		if(employeeDetailsDTO.getId() != null && employeeDetailsDTO.getId() > 0) {
			employeeDetailsEntity = employeeDetailsRepository.findById(employeeDetailsDTO.getId()).get();
			BeanUtils.copyProperties(employeeDetailsDTO, employeeDetailsEntity, Utils.getIgnoreEntityPropsOnUpdate(null));
//			employeeDetailsEntity.setModifiedBy(userContext.getUserDetailsEntity().getId());
		} else {
			employeeDetailsEntity = new EmployeeDetailsEntity();
			BeanUtils.copyProperties(employeeDetailsDTO, employeeDetailsEntity);
//			employeeDetailsEntity.setCreatedBy(userContext.getUserDetailsEntity().getId());
//			employeeDetailsEntity.setModifiedBy(userContext.getUserDetailsEntity().getId());
		}
		
			
		employeeDetailsRepository.save(employeeDetailsEntity);
			
		BeanUtils.copyProperties(employeeDetailsEntity, employeeDetailsDTO);	
		
		return new ApiResponseEntity(ApiConstants.RESP_STATUS_SUCCESS, employeeDetailsDTO);
	}

	@Override
	public ApiResponseEntity getListView(String reqParams) throws Exception {
		log.info("Started executing getListView serviceImpl: {}", reqParams);
		RequestParamDTO reqParamDto = RequestParamDTO.getInstance(reqParams);
		List<EmployeeDetailsDTO> rtnList = dbQueryExecuter.executeQuery(new QueryMaker<EmployeeDetailsDTO>(reqParamDto, EmployeeDetailsDTO.class));
		
		return new ApiResponseEntity(ApiConstants.RESP_STATUS_SUCCESS, rtnList);
	}

}

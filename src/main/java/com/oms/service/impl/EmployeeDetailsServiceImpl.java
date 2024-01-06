package com.oms.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.dbquery.DBQueryExecuter;
import com.oms.dbquery.QueryMaker;
import com.oms.dto.RequestParamDTO;
import com.oms.entity.EmployeeDetailsEntity;
import com.oms.exception.NoRecordFoundException;
import com.oms.exception.RecordIdNotFoundException;
import com.oms.repository.EmployeeDetailsRepository;
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
		
			
		employeeDetailsEntity = employeeDetailsRepository.save(employeeDetailsEntity);
			
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
	
	@Override
	public ApiResponseEntity getById (Long id) {
//		UserContext userContext = Utils.getUserContext();

		if(id != null && id > 0) {
			EmployeeDetailsEntity entity = employeeDetailsRepository.findById(id).orElse(null);
			if(entity != null) {
				EmployeeDetailsDTO dto = new EmployeeDetailsDTO();
				BeanUtils.copyProperties(entity, dto);
				return new ApiResponseEntity(ApiConstants.RESP_STATUS_SUCCESS, dto);
			}
			throw new NoRecordFoundException(ApiConstants.STATUS_MESSAGE.get(ApiConstants.RESP_STATUS_NO_RECORD_FOUND_EXCEPTION));
		}
		throw new RecordIdNotFoundException(ApiConstants.STATUS_MESSAGE.get(ApiConstants.RESP_STATUS_RECORD_ID_NOT_FOUND_EXCEPTION));
	}
	
	@Transactional
	@Override
	public ApiResponseEntity deleteMultipleById (List<Long> ids) {
//		UserContext userContext = Utils.getUserContext();

		if(ids != null && !ids.isEmpty()) {
			for(Long id:ids) {
				if(id != null && id > 0) {
					employeeDetailsRepository.softDeleteById(id);
				}
			}
			return new ApiResponseEntity(ApiConstants.RESP_STATUS_SUCCESS, ApiConstants.STATUS_MESSAGE.get(ApiConstants.RESP_STATUS_SUCCESS));
		}
		throw new RecordIdNotFoundException(ApiConstants.STATUS_MESSAGE.get(ApiConstants.RESP_STATUS_RECORD_ID_NOT_FOUND_EXCEPTION));
	}

}

package com.oms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oms.constants.ApiConstants;
import com.oms.dto.ApiResponseEntity;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponseEntity> exception (InvalidCredentialsException ex, WebRequest wr) {
		ApiResponseEntity entity = new ApiResponseEntity(ApiConstants.RESP_STATUS_INVALID_CREDENTIALS_EXCEPTION , ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ApiResponseEntity> exception (InvalidEmailException ex, WebRequest wr) {
		ApiResponseEntity entity = new ApiResponseEntity(ApiConstants.RESP_STATUS_INVALID_EMAIL_EXCEPTION , ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<ApiResponseEntity> exception (NoRecordFoundException ex, WebRequest wr) {
		ApiResponseEntity entity = new ApiResponseEntity(ApiConstants.RESP_STATUS_NO_RECORD_FOUND_EXCEPTION , ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
	@ExceptionHandler(RecordIdNotFoundException.class)
	public ResponseEntity<ApiResponseEntity> exception (RecordIdNotFoundException ex, WebRequest wr) {
		ApiResponseEntity entity = new ApiResponseEntity(ApiConstants.RESP_STATUS_RECORD_ID_NOT_FOUND_EXCEPTION , ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseEntity> exception (Exception ex, WebRequest wr) {
		ApiResponseEntity entity = new ApiResponseEntity(ApiConstants.RESP_STATUS_EXCEPTION , ex.getLocalizedMessage(), null);
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
}

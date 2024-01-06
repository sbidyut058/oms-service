package com.oms.constants;

import java.util.HashMap;
import java.util.Map;

public class DBConstants {
	
	public static final String FIELD_TYPE_TEXT = "TEXT";
	public static final String FIELD_TYPE_NUMBER = "NUMBER";
	public static final String FIELD_TYPE_BOOLEAN = "BOOLEAN";
	public static final String FIELD_TYPE_DATE= "DATE";
	
	
	public static final String FIELD_NAME_FLAT_NO = "flatNo";
	public static final String FIELD_NAME_FK_FLAT_ID = "flatId";
	public static final Map<String, String> DATABASE_KEY_CONSTRAINTS_MAP = new HashMap<>();
	static {		
		DATABASE_KEY_CONSTRAINTS_MAP.put(FIELD_NAME_FLAT_NO, FIELD_NAME_FK_FLAT_ID);
		
	}
	
	public static final String TABLE_EMPLOYEE_DETAILS = "employee_details";
	public static final String TABLE_USER_DETAILS = "user_details";
	
	
	public static final String TABLE_LINK_EMPLOYEE_DETAILS_AND_USER_DETAILS = "link_employee_details_user_details";
	

}

package com.oms.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkEntityFieldMapping {

	Class< ?> entity();
	
	String mappingFieldL();
	
	String mappingFieldR();
	
	Class< ?> mappingEntity();
}

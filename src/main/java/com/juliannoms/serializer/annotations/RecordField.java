package com.juliannoms.serializer.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.juliannoms.serializer.converters.Converter;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RecordField {
	
	int length();
	
	@SuppressWarnings("rawtypes")
	Class<? extends Converter> converter();
	
	Property[] properties() default {};

}

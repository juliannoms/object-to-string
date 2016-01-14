package com.juliannoms.serializer.converters.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.juliannoms.serializer.converters.PropertiesConverter;
import com.juliannoms.serializer.exception.ConverterException;

public class DateConveter implements PropertiesConverter<Date> {
	
	public DateConveter() {
		super();
	}

	private static SimpleDateFormat SDF = new SimpleDateFormat();
	
	public static final String PATTERN_PROPERTY = "pattern";  
	public static final String DEFAULT_PATTERN = "yyyyMMdd";

	public String getAsString(Date obj, int length) {
		return SDF.format(obj);
	}

	public Date getAsObject(String str) {
		try {
			return SDF.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new ConverterException("Error while getAsObject() for "+this.getClass());
	}

	public void setProperties(Properties props) {
		String pattern = props.getProperty(PATTERN_PROPERTY);
		if(null != pattern && !pattern.equals("")) {
			SDF.applyPattern(pattern);
		}else {
			SDF.applyPattern(DEFAULT_PATTERN);
		}
	}

}

package com.juliannoms.serializer.converters;

import java.util.Properties;


public interface PropertiesConverter<T> extends Converter<T> {
	
	public void setProperties(Properties props);

}

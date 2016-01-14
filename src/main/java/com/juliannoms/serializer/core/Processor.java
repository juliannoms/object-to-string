package com.juliannoms.serializer.core;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Properties;

import com.juliannoms.serializer.annotations.Property;
import com.juliannoms.serializer.annotations.Record;
import com.juliannoms.serializer.annotations.RecordField;
import com.juliannoms.serializer.converters.Converter;
import com.juliannoms.serializer.converters.PropertiesConverter;
import com.juliannoms.serializer.exception.BeanAccessException;
import com.juliannoms.serializer.exception.NewInstanceException;
import com.juliannoms.serializer.exception.NonRecordAnnotationException;
import com.juliannoms.serializer.exception.RecordLengthException;

public class Processor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String serialize(Object obj) {

		if (!obj.getClass().isAnnotationPresent(Record.class)) {
			throw new NonRecordAnnotationException("The object passed to be serialized is not annotated with @Record");
		}

		StringBuilder sb = new StringBuilder();
		for (Field f : obj.getClass().getDeclaredFields()) {
			RecordField fieldAnnotation = f.getAnnotation(RecordField.class);
			if (null != fieldAnnotation) {
				Converter converter = (Converter) getNewInstance(fieldAnnotation.converter());
				setProperties(converter, fieldAnnotation.properties());
				int length = fieldAnnotation.length();
				Object getterObj = invokeGetter(obj, f.getName());
				sb.append(converter.getAsString(getterObj, length));
			}
		}

		Record recordAnnotation = obj.getClass().getAnnotation(Record.class);
		int recordLength = recordAnnotation.lengthRecord();
		if (sb.length() != recordLength) {
			throw new RecordLengthException("Generated String length error for: " + obj.getClass().getName());
		}

		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public static <T> T deserialize(String serial, Class<T> annotatedClass) {

		if (!annotatedClass.isAnnotationPresent(Record.class)) {
			throw new NonRecordAnnotationException("The object passed to be deserialized is not annotated with @Record");
		}

		Record recordAnnotation = annotatedClass.getAnnotation(Record.class);
		int recordLength = recordAnnotation.lengthRecord();

		if (recordLength > serial.length()) {
			throw new RecordLengthException("Serial Length Record error for: " + annotatedClass.getName());
		}

		T obj = null;
		obj = annotatedClass.cast(getNewInstance(annotatedClass));
		int offset = 0;

		for (Field f : obj.getClass().getDeclaredFields()) {
			RecordField fieldAnnotation = f.getAnnotation(RecordField.class);
			if (null != fieldAnnotation) {
				Converter converter = (Converter) getNewInstance(fieldAnnotation.converter());
				setProperties(converter, fieldAnnotation.properties());
				int length = fieldAnnotation.length();
				Object value = converter.getAsObject(serial.substring(offset, offset + length));
				invokeSetter(obj, value, f.getName());
				offset += length;
			}
		}
		return obj;
	}

	private static void invokeSetter(Object bean, Object value, String property) {
		try {
			PropertyDescriptor p = new PropertyDescriptor(property, bean.getClass());
			p.getWriteMethod().invoke(bean, value);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new BeanAccessException("The setter method of property '" + property + "' from class '" + bean.getClass()
				+ "' is not accessible.");
	}

	private static Object invokeGetter(Object bean, String property) {
		try {
			PropertyDescriptor p = new PropertyDescriptor(property, bean.getClass());
			return p.getReadMethod().invoke(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new BeanAccessException("The getter method of property '" + property + "' from class '" + bean.getClass()
				+ "' is not accessible.");
	}

	@SuppressWarnings("rawtypes")
	private static Object getNewInstance(Class clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new NewInstanceException("Impossible to create a new instance of " + clazz.getName());
	}

	@SuppressWarnings("rawtypes")
	private static void setProperties(Converter converter, Property[] properties) {
		if (converter instanceof PropertiesConverter) {
			Properties props = new Properties();
			for (Property p : properties)
				props.put(p.name(), p.value());
			PropertiesConverter conv = (PropertiesConverter) converter;
			conv.setProperties(props);
		}
	}

}

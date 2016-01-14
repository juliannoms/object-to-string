package com.juliannoms.serializer.converters.impl;

import org.apache.commons.lang3.StringUtils;

import com.juliannoms.serializer.converters.Converter;


public class StringConverter implements Converter<String> {

	public String getAsString(String obj, int length) {
		String str = StringUtils.left(obj, length);
		return StringUtils.rightPad(str, length);
	}

	public String getAsObject(String str) {
		return str.trim();
	}

}

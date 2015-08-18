package br.gov.serpro.serializer.converters.impl;

import org.apache.commons.lang3.StringUtils;

import br.gov.serpro.serializer.converters.Converter;


public class IntegerConverter implements Converter<Integer> {

	public String getAsString(Integer obj, int length) {
		String str = String.valueOf(obj);
		str = StringUtils.right(str, length);
		return StringUtils.leftPad(str, length, '0');
	}

	public Integer getAsObject(String str) {
		return Integer.parseInt(str);
	}
	
	

}

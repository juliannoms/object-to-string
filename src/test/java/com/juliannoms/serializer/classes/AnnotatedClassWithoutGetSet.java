package com.juliannoms.serializer.classes;

import com.juliannoms.serializer.annotations.Record;
import com.juliannoms.serializer.annotations.RecordField;
import com.juliannoms.serializer.converters.impl.IntegerConverter;
import com.juliannoms.serializer.converters.impl.StringConverter;

@Record(lengthRecord=10)
public class AnnotatedClassWithoutGetSet {

	public AnnotatedClassWithoutGetSet() {
	}
	
	@RecordField(converter=StringConverter.class, length=5)
	private String versao="01.00";
	
	@RecordField(converter=IntegerConverter.class, length=5)
	private int numero = 1;

}

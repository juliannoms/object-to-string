package com.juliannoms.serializer.classes;

import com.juliannoms.serializer.annotations.Record;
import com.juliannoms.serializer.annotations.RecordField;
import com.juliannoms.serializer.converters.impl.StringConverter;

@Record(lengthRecord=10)
public class AnnotatedWrongLengthClass {

	@RecordField(converter=StringConverter.class, length=8)
	private String nome = "bla";

	
	public String getNome() {
		return nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}
}

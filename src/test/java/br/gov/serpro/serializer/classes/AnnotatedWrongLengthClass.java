package br.gov.serpro.serializer.classes;

import br.gov.serpro.serializer.annotations.Record;
import br.gov.serpro.serializer.annotations.RecordField;
import br.gov.serpro.serializer.converters.impl.StringConverter;

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

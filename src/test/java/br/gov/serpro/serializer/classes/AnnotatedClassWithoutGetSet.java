package br.gov.serpro.serializer.classes;

import br.gov.serpro.serializer.annotations.Record;
import br.gov.serpro.serializer.annotations.RecordField;
import br.gov.serpro.serializer.converters.impl.IntegerConverter;
import br.gov.serpro.serializer.converters.impl.StringConverter;

@Record(lengthRecord=10)
public class AnnotatedClassWithoutGetSet {

	public AnnotatedClassWithoutGetSet() {
	}
	
	@RecordField(converter=StringConverter.class, length=5)
	private String versao="01.00";
	
	@RecordField(converter=IntegerConverter.class, length=5)
	private int numero = 1;

}

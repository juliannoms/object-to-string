package br.gov.serpro.serializer.annotation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.gov.serpro.serializer.classes.AnnotatedClass;
import br.gov.serpro.serializer.classes.AnnotatedClassWithoutGetSet;
import br.gov.serpro.serializer.classes.AnnotatedPropsClass;
import br.gov.serpro.serializer.classes.AnnotatedWrongLengthClass;
import br.gov.serpro.serializer.classes.NonAnnotatedClass;
import br.gov.serpro.serializer.core.Processor;
import br.gov.serpro.serializer.exception.BeanAccessException;
import br.gov.serpro.serializer.exception.NonRecordAnnotationException;
import br.gov.serpro.serializer.exception.RecordLengthException;

public class AnnotationsTest {

	@Test(expected = NonRecordAnnotationException.class)
	public void nonAnnotatedClassSerialize() {
		NonAnnotatedClass n = new NonAnnotatedClass();
		Processor.serialize(n);
	}

	@Test
	public void annotatedClassSerialize() {
		AnnotatedClass n = new AnnotatedClass();
		n.setNome("Julianno");
		n.setSobrenome("Silva");
		n.setIdade(31);
		String returned = Processor.serialize(n);
		Assert.assertEquals(43, returned.length());
		Assert.assertEquals("Julianno          Silva          0000000031", returned);
	}

	@Test(expected = RecordLengthException.class)
	public void wrongLengthClassSerialize() {
		AnnotatedWrongLengthClass n = new AnnotatedWrongLengthClass();
		Processor.serialize(n);
	}

	@Test(expected = NonRecordAnnotationException.class)
	public void nonAnnotatedClassDeserialize() {
		Processor.deserialize("test", NonAnnotatedClass.class);
	}

	@Test(expected = RecordLengthException.class)
	public void wrongLengthClassDeserialize() {
		Processor.deserialize("", AnnotatedWrongLengthClass.class);
	}
	
	@Test
	public void annotatedClassDeserialize() {
		AnnotatedClass n = new AnnotatedClass();
		n.setNome("Julianno");
		n.setSobrenome("Silva");
		n.setIdade(31);
		AnnotatedClass result = Processor.deserialize("Julianno          Silva          0000000031", AnnotatedClass.class);
		Assert.assertEquals(n, result);
		System.out.println("Result: "+result.toString());
	}
	
	@Test(expected=BeanAccessException.class)
	public void nonGetterSerialize() {
		AnnotatedClassWithoutGetSet n = new AnnotatedClassWithoutGetSet();
		Processor.serialize(n);
	}
	
	@Test(expected=BeanAccessException.class)
	public void nonSetterDeserialize() {
		Processor.deserialize("01.0000001", AnnotatedClassWithoutGetSet.class);
	}
	
	@Test
	public void testPropertiesDateConverterSerialize() {
		Date data  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(data));
		sdf.applyPattern("yyyy/MM/dd");
		sb.append(sdf.format(data));
		sdf.applyPattern("dd/MM/yyyy");
		sb.append(sdf.format(data));
		AnnotatedPropsClass an = new AnnotatedPropsClass(data);
		String serial = Processor.serialize(an);
		Assert.assertEquals(sb.toString(), serial);
		System.out.println(serial);
	}
	
	@Test
	public void testPropertiesDateConverterDeserialize() {
		Date data  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		StringBuilder sb = new StringBuilder();
		sb.append(sdf.format(data));
		sdf.applyPattern("yyyy/MM/dd");
		sb.append(sdf.format(data));
		sdf.applyPattern("dd/MM/yyyy");
		sb.append(sdf.format(data));
		AnnotatedPropsClass an = Processor.deserialize("201406112014/06/1111/06/2014", AnnotatedPropsClass.class);
		System.out.println(an.toString());
	}

}

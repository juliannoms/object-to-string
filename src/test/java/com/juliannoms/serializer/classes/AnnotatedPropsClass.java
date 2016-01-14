package com.juliannoms.serializer.classes;

import java.util.Date;

import com.juliannoms.serializer.annotations.Property;
import com.juliannoms.serializer.annotations.Record;
import com.juliannoms.serializer.annotations.RecordField;
import com.juliannoms.serializer.converters.impl.DateConveter;

@Record(lengthRecord = 28)
public class AnnotatedPropsClass {

	@RecordField(converter = DateConveter.class, length = 8)
	private Date data3;

	@RecordField(converter = DateConveter.class, length = 10, properties = { @Property(name = DateConveter.PATTERN_PROPERTY, value = "yyyy/MM/dd") })
	private Date data;

	@RecordField(converter = DateConveter.class, length = 10, properties = { @Property(name = DateConveter.PATTERN_PROPERTY, value = "dd/MM/yyyy") })
	private Date data2;

	public AnnotatedPropsClass(Date data) {
		super();
		this.data = data;
		this.data2 = data;
		this.data3 = data;
	}

	public AnnotatedPropsClass() {
		super();
	}


	@Override
	public String toString() {
		return "AnnotatedPropsClass [data3=" + data3 + ", data=" + data + ", data2=" + data2 + "]";
	}

	
	public Date getData3() {
		return data3;
	}

	
	public void setData3(Date data3) {
		this.data3 = data3;
	}

	
	public Date getData() {
		return data;
	}

	
	public void setData(Date data) {
		this.data = data;
	}

	
	public Date getData2() {
		return data2;
	}

	
	public void setData2(Date data2) {
		this.data2 = data2;
	}

}

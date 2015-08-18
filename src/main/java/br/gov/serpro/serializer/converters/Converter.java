package br.gov.serpro.serializer.converters;


public interface Converter<T> {
	
	public String getAsString(T obj, int length);
	
	public T getAsObject(String str);

}

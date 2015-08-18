package br.gov.serpro.serializer.classes;

import br.gov.serpro.serializer.annotations.Property;
import br.gov.serpro.serializer.annotations.Record;
import br.gov.serpro.serializer.annotations.RecordField;
import br.gov.serpro.serializer.converters.impl.IntegerConverter;
import br.gov.serpro.serializer.converters.impl.StringConverter;

@Record(lengthRecord=43)
public class AnnotatedClass {
	
	@RecordField(length=18, converter=StringConverter.class, properties= {@Property(name="teste", value="teste")})
	private String nome;
	
	@RecordField(length=15, converter=StringConverter.class)
	private String sobrenome;
	
	@RecordField(length=10, converter=IntegerConverter.class)
	private int idade;
	
	@SuppressWarnings("unused")
	private String qualquerCoisa = "faslkfhsajkhl";


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idade;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnotatedClass other = (AnnotatedClass) obj;
		if (idade != other.idade)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AnnotatedClass [nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade + "]";
	}


	
	public String getNome() {
		return nome;
	}


	
	public void setNome(String nome) {
		this.nome = nome;
	}


	
	public String getSobrenome() {
		return sobrenome;
	}


	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	
	public int getIdade() {
		return idade;
	}


	
	public void setIdade(int idade) {
		this.idade = idade;
	}

}

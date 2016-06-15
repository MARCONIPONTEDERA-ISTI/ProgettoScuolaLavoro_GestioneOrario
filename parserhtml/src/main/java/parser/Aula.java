package parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aula implements Serializable {
	
	private String Nome;
	private List<Orario> listaOrari;
	
	public Aula(String nome){
		this.Nome = nome;
		listaOrari = new ArrayList<Orario>();
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public List<Orario> getListaOrari() {
		return listaOrari;
	}

	public void setListaOrari(Orario o) {
		 listaOrari.add(o);
	}

	@Override
	public String toString() {
		return "Aula [Nome=" + Nome + ", listaOrari=" + listaOrari + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		Aula other = (Aula) obj;
		if (!Nome.equals(other.getNome()))
			return false;
		return true;
	}
	
	
}

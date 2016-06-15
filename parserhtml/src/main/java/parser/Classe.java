package parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classe implements Serializable {
	
	private String nome;
	private List<Orario> listaOrari;
	
	public Classe(){
		listaOrari = new ArrayList<Orario>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Orario> getListaOrari() {
		return listaOrari;
	}

	public void setListaOrari(Orario o) {
		 listaOrari.add(o);
	}

	@Override
	public String toString() {
		return "Classe [nome=" + nome + ", listaOrari=" + listaOrari + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		Classe other = (Classe) obj;
		if (!nome.equals(other.getNome()))
			return false;
		return true;
	}
	
	
	
}

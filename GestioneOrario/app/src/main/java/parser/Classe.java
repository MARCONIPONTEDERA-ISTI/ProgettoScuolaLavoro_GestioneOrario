package parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Classe implements Serializable {
	
	private String nome;
	private TreeSet<Orario> listaOrari;
	
	public Classe(){
		listaOrari = new TreeSet<Orario>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Orario> getListaOrari() {
		return new ArrayList<Orario>(listaOrari);
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

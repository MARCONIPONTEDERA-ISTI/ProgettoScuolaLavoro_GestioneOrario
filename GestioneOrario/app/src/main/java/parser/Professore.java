package parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Professore implements Serializable {
	
	private String Nome;
	private TreeSet<Orario> listaOrari;
	
	public Professore(){
		listaOrari = new TreeSet<Orario>();
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public ArrayList<Orario> getListaOrari() {
		return new ArrayList<Orario>(listaOrari);
	}

	public void setListaOrari(Orario o) {

		if(!o.getSOrainizio().equals("07:50") & listaOrari.isEmpty()){
			Orario orario =  new Orario(null, null, "", "", "", "");
			listaOrari.add(orario);

			listaOrari.add(o);
		}else{
			listaOrari.add(o);

		}

	}

	@Override
	public String toString() {
		return "Professore [Nome=" + Nome + ", listaOrari=" + listaOrari + "]";
	}

	@Override
	public boolean equals(Object obj) {

		Professore other = (Professore) obj;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		return true;
	}
	
	

	public int compareTo(Professore o) {
		if (!Nome.equals(o.Nome))
			return Nome.compareTo(o.getNome());
		return 0;
	}
	
}

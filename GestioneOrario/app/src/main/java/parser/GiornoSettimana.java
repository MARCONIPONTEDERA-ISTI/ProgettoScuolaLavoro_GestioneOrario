package parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiornoSettimana implements Serializable {
	
	private Map<String,Classe> listaClassi;
	private Map<String,Professore> listaProfessori;
	private Map<String,Aula> listaAule;
	
	private int Dayofweek;
	
	GiornoSettimana(){
		listaClassi = new HashMap<String, Classe>();
		listaProfessori= new HashMap<String,Professore>();
		listaAule= new HashMap<String,Aula>();
	}
	
	
	public ArrayList<Orario> searchProfessore(String Nome){
		Professore i = listaProfessori.get(Nome);
		if(i!=null){
			return i.getListaOrari();
		}
		return null;
	}
	
	public List<Orario> searchClasse(String nome){
		Classe i = listaClassi.get(nome);
		if(i!=null){
			return i.getListaOrari();
		}
		return null;
	}
	
	public List<Orario> searchAula(String nome){
			Aula i = listaAule.get(nome);
			if(i!=null){
				return i.getListaOrari();
			}
			return null;
	}
	
	

	public int getDayofweek() {
		return Dayofweek;
	}

	public void setDayofweek(int dayofweek) {
		Dayofweek = dayofweek;
	}

	public Classe getListaClassi(String nomeclasse) {	
		Classe i = listaClassi.get(nomeclasse);
		if(i==null){
			Classe p = new Classe();
			p.setNome(nomeclasse);
			listaClassi.put(nomeclasse, p);
			return p;
		}
		return i;
	}
	
	public Aula getAula(String a){
		Aula i = listaAule.get(a);
		if(i==null){
			Aula p = new Aula(a);
			listaAule.put(a, p);
			return p;
		}
		return i;	
	}
	
	public Professore getProf(String nomeprof){
		Professore i = listaProfessori.get(nomeprof);
		if(i==null){
			Professore p = new Professore();
			p.setNome(nomeprof);;
			listaProfessori.put(nomeprof, p);
			return p;
		}
		return i;	
		
	}

	@Override
	public String toString() {
		return "GiornoSettimana [listaClassi=" + listaClassi
				+ ", listaProfessori=" + listaProfessori + ", listaAule="
				+ listaAule + ", Dayofweek=" + Dayofweek + "]";
	}


	@Override
	public boolean equals(Object obj) {
		
		GiornoSettimana other = (GiornoSettimana) obj;
		if (Dayofweek != other.getDayofweek())
			return false;
		return true;
	}



}

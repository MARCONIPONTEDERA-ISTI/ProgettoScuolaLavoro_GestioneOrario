package parser;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Orario implements Serializable {

	private Date orainizio;
	private Date orafine;
	private String Aula;
	private String Professore;
	private String Classe;
	private String Materia;
	
	
	
	public Orario(Date orainizio, Date orafine, String aula, String professore,
			String classe, String materia) {
		this.orainizio = orainizio;
		this.orafine = orafine;
		this.Aula = aula;
		this.Professore = professore;
		this.Classe = classe;
		this.Materia = materia;
	}
	public Date getOrainizio() {
		return orainizio;
	}
	public void setOrainizio(Date orainizio) {
		this.orainizio = orainizio;
	}
	public Date getOrafine() {
		return orafine;
	}
	public void setOrafine(Date orafine) {
		this.orafine = orafine;
	}
	public String getAula() {
		return Aula;
	}
	public void setAula(String aula) {
		Aula = aula;
	}
	public String getProfessore() {
		return Professore;
	}
	public void setProfessore(String professore) {
		Professore = professore;
	}
	public String getClasse() {
		return Classe;
	}
	public void setClasse(String classe) {
		Classe = classe;
	}
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return "orainizio=" + format.format(orainizio) + ", orafine=" + format.format(orafine)
				+ ", Aula=" + Aula + ", Professore=" + Professore + ", Classe="
				+ Classe + ", Materia=" + Materia + "\n\r";
	}
	
	
	


}

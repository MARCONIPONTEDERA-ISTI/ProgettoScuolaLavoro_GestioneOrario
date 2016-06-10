package parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class TabellaOrario {

	List<GiornoSettimana> orariosettimana;
	Map<String,List<String>> mapMateriaProf;
	private String url;

	
	public TabellaOrario(){
		orariosettimana = new ArrayList<GiornoSettimana>();
		mapMateriaProf = new HashMap<String, List<String>>();
	}



	public void read(){
		
		Map<String,String> mapprofurl =  new HashMap<String, String>();
		try {
			Document doc = Jsoup.connect(url).get();
			//Elements h1 = doc.getElementsByTag("tr"); //$NON-NLS-1$
			Elements colonnetabella1 = doc.getElementsByTag("td"); //$NON-NLS-1$
			//System.out.println(colonnetabella1);
			for (int i = 0; i < colonnetabella1.size(); i++) {
				Element colonna = colonnetabella1.get(i);
				List<Node> figli = colonna.childNodes();
				boolean flag = false;
				for (Node iterator : figli) {
					List<Node> figli2 = iterator.childNodes();
					if(!figli2.isEmpty()){
						for(Node iterator2 : figli2){
							String Nomecolonna = iterator2.toString();
							if(Nomecolonna.trim().equals("Docenti")){

								flag= true;
								continue;
							}
							if(flag){
								if(iterator2.nodeName().equals("a")){
									Element e = (Element)iterator2 ; 
									String nomedocente = e.text();
									mapprofurl.put(nomedocente, e.attr("href"));
								}
							}



						}


					}


				}

			}



			parserProfPage(mapprofurl);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private  void parserProfPage(Map<String, String> mapprofurl) throws IOException {
		

		try {
			int o=0;
			for(String nomeprof:  mapprofurl.keySet()){
				Map<Integer,List<Columns>> mappaoccupazioni = new HashMap<Integer, List<Columns>>();
				String url = mapprofurl.get(nomeprof);
				System.out.println(url+nomeprof);
				Document doc = Jsoup.connect("http://www.marconipontedera.it/dcb/doceboCore/orario/"+url).get();
				//String gg = "http://www.marconipontedera.it/dcb/doceboCore/orario/Docenti/Docente26.html";
				//Document doc = Jsoup.connect(gg).get();
				Elements righe = doc.getElementsByTag("tr"); //$NON-NLS-1$

				Date d1;
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");

				d1 = format.parse("7:50");

				for (int i = 0; i < righe.size(); i++) {
					Elements	celle =	righe.get(i).getElementsByTag("td");
					for(int x = 0; x < celle.size(); x++){

						Element cella = celle.get(x);

						String rowspan = cella.attr("rowspan");
						if(cella.children().size()>0){
							Element e = cella.child(0);
							
							if(!rowspan.isEmpty()){
								Integer rows = Integer.decode(rowspan);
								int realcolumns = 0;
								if(rows > 1 && i>0){
									
										realcolumns = controllo(mappaoccupazioni,i,x);

									
									insertelement(mappaoccupazioni,i,rows,realcolumns);
									
									//Elements fonts = e.children();
									String cc = e.text();
									String aula = "";
									String classe = "";
									String prf = "";
									Elements ea = e.getElementsByTag("a");
									for(Element ee :  ea){
										String atr = ee.attr("href");
										if(atr.contains("Classi"))
											classe = ee.text();
										if(atr.contains("Docenti"))
											prf = ee.text();
										if(atr.contains("Aule")){
											if(aula.equals("")){
												aula = ee.text();
											}else{
												aula += " - "+ee.text();
											}
										}
										//System.out.println(fonts.get(y).text());
									}
									GiornoSettimana day = getDay(realcolumns);;

									
									Date finale = DateUtils.addMinutes(d1, 10*rows);
									String raula = aula.replace(".", "\\.").replace("(", "\\(").replace(")", "\\)");
									String materia = cc.replaceAll(raula, "");
									materia = materia.replaceAll(classe, "").replaceAll(prf, "").trim();
									//System.out.println(materia);
									String[] numaula = aula.split(" - ");
									
									
									Classe classed = day.getListaClassi(classe);
									Professore f = day.getProf(nomeprof);
									setMapMateriaProf(materia,f.getNome());
									Orario orario = null;
									if(numaula.length>1){
										for (int j = 0; j < numaula.length; j++) {
											String string = numaula[j];
											orario =  new Orario(d1, finale, string, nomeprof, classe, materia);
											Aula a = day.getAula(string);
											a.setListaOrari(orario);
											
										}
										
									}else{
										orario =  new Orario(d1, finale, aula, nomeprof, classe, materia);
										Aula a = day.getAula(aula);
										a.setListaOrari(orario);
										
									}
									classed.setListaOrari(orario);
									
									f.setListaOrari(orario);

								}
							}
						}
					}
					if(i>0){
						d1 = DateUtils.addMinutes(d1, 10);
					}

				}
				
			/*if(o==0)
					break;
				o++;*/
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		

	}


	private void setMapMateriaProf(String materia, String f) {
		if(mapMateriaProf.containsKey(materia)){
			mapMateriaProf.get(materia).add(f);
		}else{
			List<String> lp = new ArrayList<String>();
			lp.add(f);
			mapMateriaProf.put(materia,lp );
		}
		
	}



	private  GiornoSettimana getDay(int realcolumns) {
		GiornoSettimana p = new GiornoSettimana();
		p.setDayofweek(realcolumns);
		int t = orariosettimana.indexOf(p);
		if(t>=0){
			return orariosettimana.get(t);
		}else{
			orariosettimana.add(p);
		}
		return p;

	}



	private  int controllo(Map<Integer,List<Columns>> mappaoccupazioni,
			int i, int x) {
		int tmp = x;

		List<Columns> listaelementixriga = mappaoccupazioni.get(i);
		if(listaelementixriga!=null){
			tmp += numCellSpawPresentFirst(listaelementixriga,tmp);
			while(isPresent(listaelementixriga,tmp)){
				tmp++;
			}
		}
		return tmp;
	}

	private  int numCellSpawPresentFirst(
			List<Columns> listaelementixriga, int tmp) {
		int res = 0;
		for(Columns c: listaelementixriga){
			if(c.getC()<=tmp){
				res++;
			}
		}
		return res;

	}

	private  boolean isPresent(List<Columns> list, int x){
		Columns tmp = new Columns();
		tmp.setC(x);
		if(list.contains(tmp)){
			return true;
		}
		return false;
	}


	private static void insertelement(Map<Integer,List<Columns>> mappaoccupazioni,
			int realrow, int rowspan, int columns) {
		for (int row = 1; row < rowspan; row++) {
			Columns c = new Columns();
			
			if(mappaoccupazioni.containsKey(realrow+row)){

				c.setC(columns);

				if(!mappaoccupazioni.get(realrow+row).contains(c))
					mappaoccupazioni.get(realrow+row).add(c);

			}else{
				List<Columns> is = new ArrayList<Columns>();


				is.add(c);
				c.setC(columns);

				mappaoccupazioni.put(realrow+row,is);
			}
		}

	}

	public  List<Orario> SearchbyProf(String string, int i) {
		List<Orario> LOrario = new ArrayList<Orario>();
		for(GiornoSettimana gs  : orariosettimana){
			if(gs.getDayofweek()==i){
				LOrario = gs.searchProfessore(string);
				System.out.println(getGiorno(i)+" "+LOrario);
				//break;
			}
		}

		return LOrario;


	}
	public  List<Orario> SearchbyAula(String string, int i) {
		List<Orario> LOrario = new ArrayList<Orario>();
		for(GiornoSettimana gs  : orariosettimana){
			if(gs.getDayofweek()==i){
				LOrario = gs.searchAula(string);
				System.out.println(getGiorno(i)+" "+LOrario);
				//break;
			}
		}

		return LOrario;
	}
	public  List<Orario> SearchbyClasse(String string, int i) {
		List<Orario> LOrario = new ArrayList<Orario>();
		for(GiornoSettimana gs  : orariosettimana){
			if(gs.getDayofweek()==i){
				LOrario = gs.searchClasse(string);
				System.out.println(getGiorno(i)+" "+LOrario);
				//break;
			}
		}

		return LOrario;


	}

	private  String getGiorno(int num){
		switch (num) {
		case 1:
			return "LUN";

		case 2:
			return "MAR";

		case 3:
			return "MER";

		case 4:
			return "GIO";

		case 5:
			return "VEN";

		case 6:
			return "SAB";

		default:
			break;
		}
		return "";
	}

	public void setUrl(String url) {
		this.url = url;

	}



	public Set<String> getMaterie() {
		
		return mapMateriaProf.keySet();
	}
}
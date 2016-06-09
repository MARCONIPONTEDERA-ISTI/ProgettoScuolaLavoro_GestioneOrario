package parser;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;



public class Main_Phil {

	//public static 


	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String url = "http://www.marconipontedera.it/dcb/doceboCore/orario/index.html";
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
		/*   Elements links = doc.select("a[href]");
	        Elements media = doc.select("[src]");
	        Elements imports = doc.select("link[href]");*/


	}

	private static void parserProfPage(Map<String, String> mapprofurl) throws IOException {
		Map<Integer,List<Columns>> mappaoccupazioni = new HashMap<Integer, List<Columns>>();
		List<GiornoSettimana> orariosettimana = new ArrayList<GiornoSettimana>();
		try {
			int o=0;
			for(String nomeprof:  mapprofurl.keySet()){
				String url = mapprofurl.get(nomeprof);
				System.out.println(url);
				Document doc = Jsoup.connect("http://www.marconipontedera.it/dcb/doceboCore/orario/"+url).get();
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
							//System.out.println(e.text());
							if(!rowspan.isEmpty()){
								Integer rows = Integer.decode(rowspan);
								int realcolumns = 0;
								if(rows > 1){
									if(i>0){
										realcolumns = controllo(mappaoccupazioni,i,x);

									}
									insertelement(mappaoccupazioni,i,rows,realcolumns);

									//Elements fonts = e.children();
									String cc = e.text();
									String aula = "";
									String classe = "";
									Elements ea = e.getElementsByTag("a");
									for(Element ee :  ea){
										String atr = ee.attr("href");
										if(atr.contains("Classi"))
											classe = ee.text();
										if(atr.contains("Aule"))
											aula = ee.text();
										//System.out.println(fonts.get(y).text());
									}
									GiornoSettimana day = getDay(orariosettimana,realcolumns);;
									
									//day.setDayofweek(realcolumns);
									
									

									
									

									
									

									String materia = cc.replaceAll(aula, "").replaceAll(classe, "");
									Date finale = DateUtils.addMinutes(d1, 10*rows);
									Orario orario =  new Orario(d1, finale, aula, nomeprof, classe, materia);
									Classe classed = day.getListaClassi(classe);
									classed.setListaOrari(orario);
									
									Aula a = day.getAula(aula);
									
									
									a.setListaOrari(orario);
									Professore f = day.getProf(nomeprof);
									f.setListaOrari(orario);

									/*	System.out.println("Giorno "+getGiorno(x));


									System.out.println("Orario I "+format.format(d1));
									System.out.println("aula "+aula);
									System.out.println("classe "+classe);
									String materia = cc.replaceAll(aula, "").replaceAll(classe, "");
									System.out.println("materia "+materia);

									System.out.println("Orario F "+format.format(finale));
									System.out.println();*/
								}
							}
						}
					}
					if(i>0){
						d1 = DateUtils.addMinutes(d1, 10);
					}
					
				}
				//System.out.println(celle);
				/*String giorno = "";
			for (int i = 0; i < celle.size(); i++) {
				int  colonna = i % 6 ;
				Element cella = celle.get(i);
				String rowspan = cella.attr("rowspan");
				Elements e = cella.children();
				if(i==1 ){
					giorno = e.text();
				}

				System.out.println(giorno);
			}*/
			if(o==5)
				break;
			o++;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//System.out.println(orariosettimana);
		Search(orariosettimana,"BERNARDINI",5);

	}


	private static GiornoSettimana getDay(
			List<GiornoSettimana> orariosettimana, int realcolumns) {
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

	private static int controllo(Map<Integer,List<Columns>> mappaoccupazioni,
			int i, int x) {
		int tmp = x;

		List<Columns> listaelementixriga = mappaoccupazioni.get(i);
		if(listaelementixriga!=null){
			tmp += numCellSpawPresentFirst(listaelementixriga,tmp);
			while(isPresent(listaelementixriga,tmp)){
				tmp++;
			}
			/*if(tmp==x){
				
			}
			while(isPresent(listaelementixriga,tmp)){
				tmp++;
			}*/
		}
		return tmp;
	}

	private static int numCellSpawPresentFirst(
			List<Columns> listaelementixriga, int tmp) {
		int res = 0;
		for(Columns c: listaelementixriga){
			if(c.getC()<tmp){
				res++;
			}
		}
		return res;

	}

	private static boolean isPresent(List<Columns> list, int x){
		Columns tmp = new Columns();
		tmp.setC(x);
		if(list.contains(tmp)){
			return true;
		}
		return false;
	}


	private static void insertelement(Map<Integer,List<Columns>> mappaoccupazioni,
			int i, int rowspan, int x) {
		for (int j = 1; j < rowspan; j++) {
			Columns c = new Columns();
			if(j==1)
				c.setFirst(true);
			if(mappaoccupazioni.containsKey(i+j)){

				c.setC(x);


				mappaoccupazioni.get(i+j).add(c);

			}else{
				List<Columns> is = new ArrayList<Columns>();


				is.add(c);
				c.setC(x);

				mappaoccupazioni.put(i+j,is);
			}
		}

	}

	private static void Search(List<GiornoSettimana> orariosettimana,
			String string, int i) {
		List<Orario> LOrario = new ArrayList<Orario>();
		for(GiornoSettimana gs  : orariosettimana){
			if(gs.getDayofweek()==i){
				LOrario = gs.searchProfessore(string);
				System.out.println(getGiorno(i)+" "+LOrario);
				//break;
			}
		}




	}

	private static String getGiorno(int num){
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
}

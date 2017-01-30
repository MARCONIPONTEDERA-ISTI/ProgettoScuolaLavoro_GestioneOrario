package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	//public static 


	public static void main(String[] args) throws Exception {
		
		String url = "http://www.marconipontedera.gov.it/wordpress/wp-content/Orario%202016-17/index.html";
		TabellaOrario tb = new TabellaOrario(url);
		tb.setUrl(url);
		tb.read();
		tb.SearchbyProf("ANDRONICO",3);
	//	tb.SearchbyClasse("4BSA",4);
		
		while (true) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String line = bufferedReader.readLine();
		tb.SearchbyAula(line,1);
		
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		 line = bufferedReader.readLine();
		tb.SearchbyClasse(line,1);
		}
		
		
		

	}
}
package parser;





public class Main {

	//public static 


	public static void main(String[] args) {
		
		String url = "http://www.marconipontedera.it/dcb/doceboCore/orario/index.html";
		TabellaOrario tb = new TabellaOrario();
		tb.setUrl(url);
		tb.read();
	//	tb.SearchbyProf("ANDRONICO",3);
	//	tb.SearchbyClasse("4BSA",4);
		tb.SearchbyAula("20",4);
		System.out.println(tb.getMaterie());

	}
}
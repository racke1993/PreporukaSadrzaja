package logika;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Kontroler {

	private static Kontroler instanca;
	private static HashMap<String, String> tekstovi; //mapa key-value : putanja-tekst
	private static HashMap<String, Integer> recnikProba;
	File[] files;
	
	public Kontroler(){
		
		files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/").listFiles();
		//files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/konacniFajlovi/").listFiles();
		tekstovi = Metode.vratiFajlove(files);
		
		recnikProba = new HashMap<String,Integer>();
		Metode.vratiFajlove(files);
	}
	public static Kontroler getInstanca(){
		if(instanca==null) instanca = new Kontroler();
		return instanca;
	}
	
	public HashMap<String, String> getTekstovi() {
		return this.tekstovi;
	}
	public HashMap<String, Integer> getRecnik() {
		return this.recnikProba;
	}
	
	public File[] getFiles() {
		return files;
	}
	public void setFiles(File[] files) {
		this.files = files;
	}
	public void setTekstovi(HashMap<String, String> tekstovi) {
		this.tekstovi = tekstovi;
	}
	
//	public void formirajRecnik(){
//		File[] files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/").listFiles();
//		
//		for(int i=0;i<files.length;i++){
//			String fajl = Metode.vratiStringFajlaBezPonavljanja(files[i].getPath());
//			
//		}
//	}

	
	public static void main(String[] args) throws IOException{
		Metode.napuniRecnik();
		//Kontroler.getInstanca().printHashMap(Kontroler.getInstanca().getTekstovi());
		//System.out.println(iseciTextVratiPutanju("myFile.txt"));
	}
}

/**
 * @author "Nikola Milikic"
 * nikola.milikic@gmail.com
 * 
 * @author "Uros Krcadinac"
 * uros@krcadinac
 *
 * Intelligent Systems (FON, University of Belgrade) - http://is.fon.rs/
 * GOOD OLD AI Research Lab - http://goodoldai.org
 * 
 * December, 2013.
 * 
 */
package main;

import java.awt.Desktop;
import logika.JSONPamcenje;
import logika.Kontroler;
import logika.Metode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import annotators.Annotator;
import annotators.TagmeAnnotator;

public class StartAnnotators {

	public static void openWebpage(URI url) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(url);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	

	
	
	public static void pokreniTagNadFajlom(String textInput, String output){
		Annotator annotator = new TagmeAnnotator();
		/*
		String text = "At around the size of a domestic chicken, kiwi are by far the smallest " +
				"living ratites and lay the largest egg in relation to their body size of any " +
				"species of bird in the world.";
		*/
		String rezultat = annotator.getConcepts(textInput);
		System.out.println("TEXT INPUT JE : "+textInput);
		try {
			File file = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviJSON/"+output+".json");
			file.getParentFile().mkdirs();
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fajloviJSON/"+output+".json"), "utf-8"));
			writer.write(rezultat);
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void napraviTextFajloveKonacne(String putanjaDoFajla){
		String naziv = Metode.izvuciNazivIzPutanje(putanjaDoFajla);
		System.out.println(naziv);
		pokreniTagNadFajlom(Metode.vratiStringFajla(putanjaDoFajla), naziv);
		//System.out.println("*********************(*(*(*(*(*(*"+vratiStringFajla(putanjaDoFajla));
		
		String reci = JSONPamcenje.vratiStringReciIzJsona("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviJSON/"+naziv+".json");
		
		File file = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/JSoupRSS/"+naziv+".txt");
		file.getParentFile().mkdirs();
		try{
		PrintWriter writer1 = new PrintWriter("fajloviText/"+naziv+".txt", "UTF-8");
		writer1.write(reci);
		writer1.close();
		
		//PrintWriter writer2 = new PrintWriter("Recnik.txt", "UTF-8");
		
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("Recnik.txt", true));
		writer2.append(reci);
		writer2.close();
		}
		catch(Exception e){
			System.out.println("Greska: "+e);
		}
	}
	public static void pokreniTagNadSvima(File[] files){
		//String putanjaDoFajla = "/Users/Radomir/Documents/workspace/SeleniumProjekat/fajlovi/theguardian.com/world/2016/may/04/david-cameron-concessions-syrian-child-refugees.txt";
	    for (File file : files) {
	    	if (file.isDirectory()) {
	            //System.out.println("Directory: " + file.getName());
	    		pokreniTagNadSvima(file.listFiles()); // Calls same method again.
	        } else 
	       if(!file.getPath().contains(".DS_Store")){
	        	//System.out.println(file.getPath());
	    	   //System.out.println(file.getAbsolutePath());
	        	//tekstovi.put((String)file.getPath(), vratiStringFajla((String)file.getPath()));
	        	napraviTextFajloveKonacne(file.getAbsolutePath());
	        	//System.out.println((String)file.getPath()+vratiStringFajla((String)file.getPath()));
	        }
	 }
	     
	    
		//napraviTextFajloveKonacne(putanjaDoFajla)
	}
	public static void main(String[] args) throws IOException {
		
		File[] files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/JSoupRSS/").listFiles();
		pokreniTagNadSvima(files);
		
	}
}



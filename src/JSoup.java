import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import logika.Metode;

public class JSoup {
	public static String metoda(String path) throws IOException{

		Document doc = Jsoup.connect(path).get();

		// get page title
		String title = doc.title();
		//System.out.println("title : " + title); - BITNO

		// get all links
		Elements text = doc.getElementsByTag("body");
		String vrednost = "";
		//Elements links = doc.select("a[href]");
		for (Element instanca : text) {

			// get the value from href attribute
			//System.out.println("\nlink : " + link.attr("href"));
			//System.out.println("text : " + instanca.text()); //- BITNO
			vrednost = instanca.text();

		}
		vrednost = vrednost.replace("Close Skip to main content sign in Saved for later Comment activity Edit profile Email preferences Change password Sign out subscribe search dating more from the guardian: dating jobs change edition: switch to the UK edition switch to the US edition switch to the AU edition International switch to the UK edition switch to the US edition switch to the Australia edition The Guardian home","");
		//vrednost = vrednost.substring(vrednost.lastIndexOf(">"));
		
		vrednost = Metode.normalizacija(vrednost).trim();
		
		//System.out.println(vrednost);
		System.out.println(vrednost);
		return title+"NOVITEKST"+vrednost;
		
	}


	
	
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		
		
		
		HashMap<String,String> putanjaSadrzaj = new HashMap<>();
		try {
			
			int brojac = 0;
			for(String adresa : RSSReader.readRSS("https://www.theguardian.com/uk/rss")){
				System.out.println("Nova iteracija: "+brojac++);
				
				if(brojac == 0 || brojac == 1 || brojac ==2 || brojac == 69
						 || brojac == 70  || brojac == 71  || brojac == 68  || brojac == 67) continue;
				//za ove brojeve se ne dobijaju validni dokumenti
				if(brojac==120) break;
				//System.out.println(adresa);
				//System.out.println("ble?");
				String rez = metoda(adresa);
				
				//Nepotreban string : Close Skip to main content sign in Saved for later Comment activity Edit profile Email preferences Change password Sign out subscribe search dating more from the guardian: dating jobs change edition: switch to the UK edition switch to the US edition switch to the AU edition International switch to the UK edition switch to the US edition switch to the Australia edition The Guardian home 
				
				//System.out.println(rez.split("NOVITEKST")[1]);
				String nazivFajla = rez.split("NOVITEKST")[0];
				String sadrzajFajla = rez.split("NOVITEKST")[1];
				
				
				Metode.sacuvajFajlJSON(nazivFajla, sadrzajFajla);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Zavrsio upis u fajlove");
		
		
		//Metode.printHashMap(putanjaSadrzaj);
	}
}

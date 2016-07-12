package logika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Metode {
	
	public static HashMap vratiFajlove(File[] files) {//hash mapa - putanja sadrzaj
		HashMap<String,String> tekstovi = new HashMap<>();
	    for (File file : files) {
	        if(!file.getPath().contains(".DS_Store")){
	        	tekstovi.put((String)file.getPath(), vratiStringFajla((String)file.getPath()));
	        	}
	    }
	    //printHashMap(tekstovi);
	    System.out.println("Fajlovi vraceni!");
	    return tekstovi;
	    //printHashMap(keyValue);
	}
	
	
	public static String vratiRandom(HashMap<String,String> map){
		//Metode.printHashMap(map);
		System.out.println("Metoda: "+map.size());
		Iterator iterator = map.keySet().iterator();
		
		String[] niz = new String[map.size()];
		int brojac = 0;
		while (iterator.hasNext()) {
			niz[brojac] = iterator.next().toString();
		    brojac++;
		}
		System.out.println(map.size());
		
		return //map.get(new Random().nextInt(map.size()));
				niz[new Random().nextInt(map.size())];
	
	}
	
	public static String vratiPrviElementIzMape(HashMap mapa){
		
		Map<String,Double> map=mapa;
		 Map.Entry<String,Double> entry=map.entrySet().iterator().next();
		 String key= entry.getKey();
		 double value=entry.getValue();
		return key+"presek"+value;
	}
	
	public static void printHashMap(Map map){
		Iterator iterator = map.keySet().iterator();
		  
		while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   String value = map.get(key).toString();
		  
		   System.out.println(key + " " + value);
		}
	}
	public static String izvuciNazivIzPutanje(String putanja){
		String nazivFajla = putanja.substring(putanja.lastIndexOf("/")+1);
		nazivFajla = nazivFajla.substring(0, nazivFajla.length() - 4);
		return nazivFajla;
	}

	public static void napuniRecnik(){
		File[] files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/").listFiles();
		String zaRecnik = "";
		PrintWriter writer1 = null;
		try{
	    for (File file : files) {
	        if(!file.getPath().contains(".DS_Store")){
	        	//System.out.println(file.getPath());
	        	zaRecnik+=vratiStringFajlaBezPonavljanja(file.getPath());
	        	//tekstovi.put((String)file.getPath(), vratiStringFajla((String)file.getPath()));
	        	//System.out.println((String)file.getPath()+vratiStringFajla((String)file.getPath()));
	        	}
	    }
	    writer1 = new PrintWriter("Recnik.txt", "UTF-8");
		
		writer1.write(zaRecnik);
		}
		catch(Exception e){System.out.println("Greska: "+e.getMessage());}
		writer1.close();
		System.out.println("Recnik uspesno napunjen!");
	}
	public static String vratiStringFajlaBezPonavljanja(String putanja){
		String text = " ";
		// This will reference one line at a time
        String line = null;
       
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(putanja);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	for(int i=0;i<line.split(" ").length;i++)
            	text += line.split(" ")[i]+" ";
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		putanja + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + putanja + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return text;
        
	}
	
	public static boolean daLiSePojavljujeRecUTextu(String rec, String text){
		
		if(text.contains(rec)) return true;
		return false;
		
	}
//	
	public static String normalizacija(String text){
		return text.replaceAll("[^A-Za-z0-9\\s]", "").toLowerCase();
	}
	
	public static HashMap sortByValues(Map<String, Double> map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	public static HashMap sortByValues2(Map<String, Integer> map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	public static HashMap sortByValuesReverse(Map<String, Double> map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }	
	public static Map sortByValues2reverse(Map<String, Integer> map) {
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	}
	public static void sacuvajFajlJSON(String ime,String sadrzaj) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("JSoupRSS/"+ime+".txt", "UTF-8");
		writer.println(sadrzaj);
		writer.close();
		System.out.println("Fajl sacuvan");
	}
	public static void sacuvajFajlKonacno(String ime,String sadrzaj) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("konacniFajlovi/"+ime+".txt", "UTF-8");
		writer.println(sadrzaj);
		writer.close();
		System.out.println("Fajl sacuvan");
	}	
	public static String vratiStringFajla(String putanja){
		
//		System.out.println("vrati string fajla");
//		
//		String filePath = new File("").getAbsolutePath().concat(putanja);
//		System.out.println("PATH::::"+filePath);
		
		String text = " ";
		// This will reference one line at a time
        String line = null;
       
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(putanja);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	text += line;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		putanja + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + putanja + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return text.trim().toLowerCase();
        
	}
	public static String vratiStringFajlaRelative(String putanja){
		
		System.out.println("vrati string fajla");
		
		String filePath = new File("").getAbsolutePath().concat("/"+putanja);
		System.out.println("Putanja:"+filePath);
		
		String text = " ";
		// This will reference one line at a time
        String line = null;
       
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(filePath);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	text += line;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		filePath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + putanja + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return text.trim().toLowerCase();
        
	}
	public static boolean postoji(String putanja){
		File f = new File(new File("").getAbsolutePath().concat("/"+putanja));
		if(f.exists() && !f.isDirectory()) { 
		   return true;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(vratiStringFajlaRelative("/probniUpiti/treciUpit.txt"));
		System.out.println(postoji("/probniUpiti/treciUpit.txt"));
	//	/Users/Radomir/Documents/workspace/SeleniumProjekat/probniUpiti/treciUpit.txt
	}
}

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import logika.JSONPamcenje;
import logika.Kontroler;
import logika.Kontroler2;
import logika.Metode;
import logika.Slicnost;

public class Algoritmi2 {
	
	public static String vratiMinHillClimbing(String putanjaUpita){
		
		String upit = Metode.vratiStringFajlaRelative(putanjaUpita);
		System.out.println("Vas upit je: "+upit);
		
		upit = upit.toLowerCase().trim();
		
		HashMap<String,String> nazivISadrzaj = (HashMap<String, String>) Kontroler2.getInstanca().getTekstovi();
		
			
		String rand = vratiRandom((HashMap<String, String>)nazivISadrzaj);
		
		String randomText = Metode.vratiStringFajla(rand).trim();//od random teksta se krece
		
		System.out.println("RANDOM TEXT JE: "+rand);
		HashMap<String,Double> slicni = CosineSimilarity2.vratiNajslicnije(randomText);

		
		
		
		
		Iterator iterator = slicni.keySet().iterator();
		String[] nizPocetak = new String[2];
		nizPocetak[0]=Metode.vratiStringFajla(rand).trim();
		nizPocetak[1]=upit;
		
		
		double max = JSONPamcenje.vratiInstancuSlicnostiIzJSONa(
				new Slicnost(rand,putanjaUpita),"slicnostProba.json");
		
		System.out.println("max je : new Slicnost("+rand+" ,"+putanjaUpita+" ), slicnostProba.json");
		
		System.out.println("Poredjenje izmedju upita: "+upit+" i random texta "+randomText+" je: "+max);
		
		String maxKey =new File("").getAbsolutePath().concat("/"+rand); //samo u prvoj iteraciji
		String maxValue = nazivISadrzaj.get(rand);
		System.out.println(maxKey+ " Max value je: "+maxValue);
		int brojac = 0;//ne sme npr proci vise od 6 suseda(6 najslicnijih)
		
		String[] nizDokumenata = new String[2];
		
		
		
		while (iterator.hasNext()) {
			System.out.println("Brojac: "+brojac);
			String key = iterator.next().toString(); 
		    String value = slicni.get(key).toString();
		    String textIzPutanje = Metode.vratiStringFajla(key).trim();
		    
		    
		    //double similarity = CosineSimilarity.izvrsi(upit, Metode.vratiStringFajla(key));
		    
		    
		    
		    nizDokumenata[0] = textIzPutanje;
		    nizDokumenata[1] = upit;
		    double similarity = JSONPamcenje.vratiInstancuSlicnostiIzJSONa(
		    		new Slicnost(key,putanjaUpita),"slicnostProba.json");
		    
		    
		    System.out.println("Poredimo "+key+" to jest : "+textIzPutanje+" vs "+upit+" = > > > "+similarity);
		    
		    System.out.println("if(similarity: "+similarity+" > "+max+") max = "+similarity+" maxKey : "+maxKey+" minValue : "+maxValue);
		    
		    if(similarity>max)  {
		    	System.out.println("SUSED POSTAO BOLJI");
		    	max = similarity; 
		    	//maxKey=key;
		    	
		    	//maxKey = pomocMetoda(Metode.vratiStringFajla(key));
		    	maxKey = pomocMetoda(key,putanjaUpita);
		    	maxValue=value;
		    }
		    else {
		    	System.out.println("NISTA OD SUSEDA");
		    	brojac ++;
		    	}
		    if(brojac == 6) {System.out.println("stigao do 6");break;}
		}
		System.out.println("\n\n\n\nKONACAN MAX KEY JE :"+maxKey);
		return maxKey;
	}

	public static String pomocMetoda(String text,String putanjaUpita){
		String upit = putanjaUpita;
		System.out.println("\nusao u pomocnu metodu: "+text);
		
		HashMap<String,String> nazivISadrzaj = (HashMap<String, String>) Kontroler2.getInstanca().getTekstovi();
			
			//System.out.println("RANDOM TEXT JE: "+randomText);
			HashMap<String,Double> slicni = CosineSimilarity2.vratiNajslicnije(Metode.vratiStringFajla(text).trim());
			
	//		System.out.println("<>");
	//		Metode.printHashMap(slicni);
	//		System.out.println("<>");
			
			Iterator iterator = slicni.keySet().iterator();
			
//			String rezultatMetode = Metode.vratiPrviElementIzMape(slicni);
//			String nazivPrvog = rezultatMetode.split("presek")[0];
//			String sadrzajPrvog = rezultatMetode.split("presek")[1];
			
			String[] nizPocetak = new String[2];
			nizPocetak[0]= text;
			nizPocetak[1]=putanjaUpita;
			double max = JSONPamcenje.vratiInstancuSlicnostiIzJSONa(new Slicnost(nizPocetak[0],nizPocetak[1]),"slicnostProba.json");
		    
			//double max = 0.000000;
			String maxKey =text; String maxValue = nazivISadrzaj.get(text);
			
			
			//System.out.println(maxKey+ " Max value je: "+maxValue);
			
			
			int brojac = 0;//ne sme npr proci vise od 6 suseda(6 najslicnijih)
			
			String[] nizDokumenata = new String[2];
			
			
			
			while (iterator.hasNext()) {
				System.out.println("Brojac: "+brojac);
				String key = iterator.next().toString(); 
			    String value = slicni.get(key).toString();
			    String textIzPutanje = Metode.vratiStringFajla(key).trim();
			    
			    
			    //double similarity = CosineSimilarity.izvrsi(upit, Metode.vratiStringFajla(key));
			    
			    
			    
			    nizDokumenata[0] = key;
			    nizDokumenata[1] = putanjaUpita;
			    double similarity = JSONPamcenje.vratiInstancuSlicnostiIzJSONa(new Slicnost(nizDokumenata[0],nizDokumenata[1]),"slicnostProba.json");
			    System.out.println("Poredimo "+key+" to jest : "+textIzPutanje+" vs "+text+" = > > > "+similarity);
			    
			    System.out.println("if(similarity: "+similarity+" > "+max+") max = "+similarity+" maxKey : "+maxKey+" minValue : "+maxValue);
			    
			    if(similarity>max)  {
			    	System.out.println("SUSED POSTAO BOLJI");
			    	max = similarity; 
			    	//maxKey=key;
			    	maxKey=pomocMetoda(key,putanjaUpita);
			    	maxValue=value;
			    }
			    else {
			    	brojac ++;
			    	System.out.println("NISTA OD SUSEDA"+brojac);
			    }
			    if(brojac == 6) {System.out.println("stigao do 6");break;}
			}
			System.out.println("Zavrsena pomocna metoda");
			return maxKey;
	}
	
	public static String vratiRandom(HashMap<String,String> map){
		System.out.println("Metoda: "+map.size());
		Iterator iterator = map.keySet().iterator();
		
		String[] niz = new String[map.size()];
		int brojac = 0;
		while (iterator.hasNext()) {
			niz[brojac] = iterator.next().toString();
		    brojac++;
		}
		System.out.println(map.size());
		return niz[new Random().nextInt(map.size())];
	
	}
	public static String laksaMetoda(String param){

    	
    	String rez = vratiMinHillClimbing("/Users/Radomir/Documents/workspace/SeleniumProjekat/probniUpiti/"+param);
    	return rez;
	}
	
	public static void main(String[] args){
		
		//String proba = "upit.txt";
		//System.out.println(vratiMinHillClimbing(proba));
		/*String reci = Kontroler.getInstanca().vratiStringFajla(Kontroler.getInstanca().vratiRecnik());
		
		List<String> strings = new ArrayList<String>(Arrays.asList(reci));
		for(String rec : strings){System.out.println(rec);}
		*/
		vratiMinHillClimbing("probniUpiti/treciUpit.txt");
		
		//vratiMinHillClimbing("leave me alone");
		

		
		//vratiMinHillClimbing("of the exchequer crucifixion war crime dermot murnaghan sky news tony blair war crime john chilcot tony blair never ever all saints song john prescott leadership triumph heritage empowerment rural party anger iraq war iraq war john prescott daily politics politics show tony blair john chilcot the times sky news politics labour economics 2003 invasion of iraq");
				System.out.println("\n\n\n");
		
		
		//Metode.printHashMap(Kontroler.getInstanca().getTekstovi());
		
		
	}
	
}

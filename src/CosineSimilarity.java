import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logika.Kontroler;
import logika.Kontroler2;
import logika.Metode;



public class CosineSimilarity {

	
	public static HashMap vratiNajslicnije(String text){
		
		HashMap<String,Double> najslicniji = new HashMap<>();//putanja, slicnost?
		HashMap tekstovi = Kontroler.getInstanca().getTekstovi();
		
		///KLJUCNO KOJI SE TEKSTOVI UZIMAJU
		
		
		double slicnost;
			Iterator iterator = tekstovi.keySet().iterator();//key - putanja, string - sadrzaj
			String[] nizDokumenata = new String[2];
			while (iterator.hasNext()) {
				//System.out.println("\nnova iteracija\n");
			   String key = iterator.next().toString();
			   String value = tekstovi.get(key).toString();
			   
			   nizDokumenata[0] = value.toLowerCase().trim();
			   nizDokumenata[1] = text.toLowerCase().trim();
			   slicnost = tfPojasnjenje.izvrsiZaPoredjenje(nizDokumenata);
			   
			   //System.out.println(value + " VS " + text + " = "+slicnost);
//			   
			   if(slicnost==1) continue;
			   najslicniji.put(key, slicnost);
			   
			}
			najslicniji = Metode.sortByValuesReverse(najslicniji);
			//Metode.printHashMap(najslicniji);
			//Metode.sortByValues(najslicniji);
			
			
			
			//dole zakomentarisana metoda mora se realizovati!
//			System.out.println("\nNaslicniji su:");
//			Metode.printHashMap(najslicniji);
//			System.out.println("*********************************");
			return najslicniji;
		
	}
	
	public static double vratiSimilarity(double[] vektorPrvi, double[] vektorDrugi){
		double similarity = 0;
		
		double brojilac = 0; double imenilac = 0,imL = 0,imD = 0;
		
		for(int i=0;i<vektorPrvi.length;i++){
			brojilac += (vektorPrvi[i]*vektorDrugi[i]);
		}
		for(int i=0;i<vektorPrvi.length;i++){
			imL += Math.pow(vektorPrvi[i],2); imD += Math.pow(vektorDrugi[i],2);
		}
		
		imenilac = Math.sqrt(imL*imD);
		similarity = brojilac/imenilac;
		
		
		System.out.println("*Similarity: "+similarity+"\n ************ ZA -"+Arrays.toString(vektorPrvi)+" - VS -"+Arrays.toString(vektorDrugi));
		return similarity;
	}
	

	public static void main(String[] args){
		/*
		String prvi = "Julie loves me more than Linda loves me very much";
		String drugi = "Jane likes me more than Julie loves me";
		List<String> recenice = new ArrayList<>();
		recenice.add(prvi); recenice.add(drugi);
		
		String recnikText = napuniRecnik(recenice);
		
		System.out.println("OVO JE TEKST RECNIKA : \n" +recnikText);
		System.out.println("OVO SU TEKSTOVI KOJI SE POREDE : \n");
		System.out.println("PRVI TEKST : "+prvi+"\n");
		System.out.println("DRUGI TEKST : "+drugi+"\n");
		vrati(prvi, drugi,recnikText);
		*/
		double[] niz1 = {10,5,3,0,0,0,0,0};//= new double[5]; 
		double[] niz2 = {5,0,7,0,0,9,0,0};//= new double[5];
		
		//niz1[0]=2;niz1[1]=3;niz1[2]=4;niz1[3]=1;niz1[4]=7;
		//niz2[0]=2;niz2[1]=3;niz2[2]=4;niz2[3]=0;niz2[4]=7;	
		
		
		//vratiSimilarity(niz1,niz2);
		
		
		System.out.println(Metode.vratiPrviElementIzMape(CosineSimilarity.vratiNajslicnije("But you just said you are vegan i am out")));
	}
}

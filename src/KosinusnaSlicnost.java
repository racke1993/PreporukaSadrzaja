import java.io.IOException;
import java.text.DecimalFormat;
import logika.JSONPamcenje;
import logika.Kontroler;
import logika.Metode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.http.impl.io.SocketOutputBuffer;

public class KosinusnaSlicnost {
	
	public static double vrati(String prvi, String drugi){
		int[] vektorPrvi = null,vektorDrugi=null; 
		System.out.println("Prvi: "+prvi); System.out.println("Drugi: "+drugi);
		if(prvi.split(" ").length!=drugi.split(" ").length)
		{
			System.out.println("Drugacije duzine!");
			System.out.println(prvi.split(" ").length+"-VS-"+drugi.split(" ").length);
			return -1;
		}
		
		
		List<String> listaReci = new ArrayList<>();
		//String reci = "me Julie loves Linda than more likes Jane";
		
		//System.out.println("Reci recnika"+reci);
		
		
		
		/*
		for(String rec:listaReci){System.out.println(rec);}
		System.out.println("Kraj");
		
		List<String> listaReci = new ArrayList<>();
		for(String rec : prvi.split(" ")){
			if(!listaReci.contains(rec)) listaReci.add(rec);
		}
		for(String rec : drugi.split(" ")){
			if(!listaReci.contains(rec)) listaReci.add(rec);
		}
		//for(String rec: listaReci) System.out.println(rec);
		//System.out.println("\n");
		*/
		vektorPrvi = new int[listaReci.size()];vektorDrugi = new int[listaReci.size()];
		System.out.println(listaReci.size());
		System.out.println("Duzina prvog: "+prvi.split(" ").length);
		System.out.println("Duzina drugog: "+drugi.split(" ").length);
		

		
		for(int j=0;j<listaReci.size();j++){
			//if(j%100==0) System.out.println("Ovo je prva petlja: "+j);
			for(int i = 0;i<prvi.split(" ").length;i++){ // for(int i = 0;i<prvi.split(" ").length;i++)
				//System.out.println("if "+listaReci.get(j)+" = "+prvi.split(" ")[i]);
				
				if(listaReci.get(j).equals(prvi.split(" ")[i])) {
					vektorPrvi[j]++;  
					//System.out.println(" \tEVO GA za "+i+".clan vrednost je "+vektorPrvi[i]); //- bitno
					}
				//System.out.println("Ovo je "+i+".clan!"+listaReci.get(j)); //- bitno
			}
			//System.out.println("");
		}
		

		System.out.println("Prelaz");
		
		
		for(int j=0;j<listaReci.size();j++){
			//if(j%100==0) System.out.println("Ovo je druga petlja: "+j);
			for(int i = 0;i<drugi.split(" ").length;i++){ // for(int i = 0;i<prvi.split(" ").length;i++)
				//System.out.println("Za vrednost j = "+j+"to jest i = "+i+"if "+listaReci.get(j)+" = "+drugi.split(" ")[i]);
				
				if(listaReci.get(j).equals(drugi.split(" ")[i])) 
				{
					vektorDrugi[j]++; 
					//System.out.println(" \tEVO GA za "+i+".clan vrednost je "+vektorDrugi[i]);
				}
				//System.out.println("Ovo je "+i+".clan!");
			}
			//System.out.println("");
		}
		
		
		System.out.println("*************PRESEK IZGLEDA OVAKO*************");
		
		System.out.println(Arrays.toString(vektorPrvi)); 
		System.out.println(Arrays.toString(vektorDrugi));//System.out.println(Arrays.toString(vektorDrugi));
		
		
		
		
		
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
		
		
		System.out.println("Similarity: "+similarity+" ************ ZA -"+prvi+" - VS -"+drugi);
		return brojilac/imenilac;
	}
	

	
	
	
	public static void main(String[] args) throws IOException{
		
		
		//Metode.printHashMap(vratiNajslicnije("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/treci.txt"));
		
	}

	
}

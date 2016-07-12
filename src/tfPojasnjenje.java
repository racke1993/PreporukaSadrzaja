import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import logika.Slicnost;
import logika.Kontroler;
import logika.Kontroler2;
import logika.Metode;

public class tfPojasnjenje {
	 
    int numOfWords;
    double[] idfVector;
    double[][] tfIdfMatrix;
    double[][] tfMatrix;
    String[] wordVector;
    int docLength[];
    static HashMap<String,Integer> recnik = new HashMap<>();
    static double[] nizSlicnosti;
    
    public HashMap vratiRecnik(){
    	return this.recnik;
    }
    
    public tfPojasnjenje(String[] docs) {
        // STEP 1, scan all words and count the number of different words
        // mapWordToIdx maps word to its vector index
        HashMap<String, Integer> mapWordToIdx = new HashMap<>();
        int nextIdx = 0;
        for (String doc : docs) {
            for (String word : doc.split(" ")) {
                if (!mapWordToIdx.containsKey(word)) {
                    mapWordToIdx.put(word, nextIdx);
                    nextIdx++;
                }
            }
        }
 
        numOfWords = mapWordToIdx.size();
 
        
        // STEP 2, create word vector where wordVector[i] is the actual word
        wordVector = new String[numOfWords];
        for (String word : mapWordToIdx.keySet()) {
            int wordIdx = mapWordToIdx.get(word);
            wordVector[wordIdx] = word;
        }
        
        
        // STEP 3, create doc word vector where docCountVector[i] is number of
        // docs containing word of index i
        // and doc length vector
        int[] docCountVector = new int[numOfWords];
        docLength = new int[docs.length];
        
        nizSlicnosti = new double[docs.length];
        
        // lastDocWordVector is auxilary vector keeping track of last doc index
        // containing the word
        int[] lastDocWordVector = new int[numOfWords];
        for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
            lastDocWordVector[wordIdx] = -1;
        }
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            String doc = docs[docIdx];
            String[] words = doc.split(" ");
            for (String word : words) {
                docLength[docIdx] = words.length;
                
                int wordIdx = mapWordToIdx.get(word);
                //if (lastDocWordVector[wordIdx] < docIdx) {
                	lastDocWordVector[wordIdx] = docIdx;
                    docCountVector[wordIdx]++;
                //}
                
            }
        }
        
        for(int i=0;i<lastDocWordVector.length;i++){
        	//System.out.println("rec: "+wordVector[i]+" = "+docCountVector[i]);
        	
        	recnik.put(wordVector[i], docCountVector[i]);
        }
 
        // STEP 4, compute IDF vector based on docCountVector
        idfVector = new double[numOfWords];
        for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
            idfVector[wordIdx] = Math.log10(1 + (double) docs.length / (docCountVector[wordIdx]));
        }
 
        // STEP 5, compute term frequency matrix, tfMatrix[docIdx][wordIdx]
        tfMatrix = new double[docs.length][];
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            tfMatrix[docIdx] = new double[numOfWords];
        }
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            String doc = docs[docIdx];
            for (String word : doc.split(" ")) {
                int wordIdx = mapWordToIdx.get(word);
                tfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] + 1;
            }
        }
        // normalize idfMatrix by deviding corresponding doc length
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
                tfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] / docLength[docIdx];
            }
        }
 
        // STEP 6, compute final TF-IDF matrix
        // tfIdfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] * idfVector[wordIdx]
        tfIdfMatrix = new double[docs.length][];
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            tfIdfMatrix[docIdx] = new double[numOfWords];
        }
 
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
                tfIdfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] * idfVector[wordIdx];
            }
        }

    }
 
    public double[][] getTF_IDFMatrix() {
        return tfIdfMatrix;
    }
 
    public String[] getWordVector() {
        return wordVector;
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
		
		
		//System.out.println("*Similarity: "+similarity+"\n ************ ZA -"+Arrays.toString(vektorPrvi)+" - VS -"+Arrays.toString(vektorDrugi));
		return similarity;
	}
    public static HashMap izvrsiSve(String query,String[] nizDokumenata){
    	
    	//String j1 = "Svi mi imamo svoje slabosti i vrline";
    	//String j2 = "U poslednjih 6 meseci sam oslabila 10 kila";
    	//String j3 = "Veceras je mesec na nebu bas veliki";
    	
    	
    	
    	String[] docs = nizDokumenata;
    	//docs[0]=j1; docs[1]=j2; docs[2]=j3; docs[3]=query;
    	docs[nizDokumenata.length-1] = query;
    	tfPojasnjenje instanca = new tfPojasnjenje(docs);
    	double[][] niz1 = instanca.getTF_IDFMatrix(); 
    	
    	
    	double[] similarityNiz=null;
    	
    	double[] nizPoredjenje = new double[niz1[0].length];
    	double[] nizQuery = new double[niz1[0].length];
    	//System.out.println("\n");
    	//System.out.println(niz1.length);
    	
    	
    	for(int i=0;i<niz1.length;i++){
    		for(int j=0;j<niz1[i].length;j++){

    			//if(i!=niz1.length-1) //System.out.println(niz1[i][j] + " ");
    			nizPoredjenje[j] = niz1[i][j];
    			nizQuery[j] = niz1[docs.length-1][j];
    	    }
    		//if(i!=niz1.length-1){
    			System.out.println("broj i:"+i);
    			nizSlicnosti[i] = vratiSimilarity(nizQuery, nizPoredjenje);
    			System.out.println(docs[i]+" nizSlicnosti["+i+"] = "+vratiSimilarity(nizQuery, nizPoredjenje));
    		//System.out.println("SIMILARITY: "+vratiSimilarity(niz1[i], similarityNiz) );
    	    
    		//}
    	}
    	
    	System.out.println(nizSlicnosti.length);
    	
    	
    	
    	return instanca.vratiRecnik();
    	
    }

    public static double izvrsiZaPoredjenje(String[] nizDokumenata){
    	
    
    	String[] docs = nizDokumenata;

    	tfPojasnjenje instanca = new tfPojasnjenje(docs);
    	double[][] niz1 = instanca.getTF_IDFMatrix(); 
    	
    	double[] similarityNiz=null;
    	
    	double[] nizPoredjenje = new double[niz1[0].length];
    	double[] nizQuery = new double[niz1[0].length];
    	//System.out.println("\n");
    	//System.out.println(niz1.length);
    	
    	for(int i=0;i<niz1.length;i++){
    		for(int j=0;j<niz1[i].length;j++){

    			//if(i!=niz1.length-1) //System.out.println(niz1[i][j] + " ");
    			nizPoredjenje[j] = niz1[i][j];
    			nizQuery[j] = niz1[docs.length-1][j];
    	    }
    		nizSlicnosti[i] = vratiSimilarity(nizQuery, nizPoredjenje);
    			
    	}
    	
    	
    	
    	return nizSlicnosti[0];
    	
    }
    
    public static List<Slicnost> generisiSlicnosti(String nazivKontrolera){
//    	
//    	PrintWriter writer = new PrintWriter("slicnosti.txt", "UTF-8");
//		writer.println(sadrzaj);
//		writer.close();
//		System.out.println("Fajl sacuvan");
		
		
		double slicnost = -99;
		String prviText="";
		String drugiText="";
		HashMap<String,String> tekstovi = null,upiti=null;
		if(nazivKontrolera.equals("Kontroler")){
			tekstovi = (HashMap<String, String>) Kontroler.getInstanca().getTekstovi();
			upiti = (HashMap<String, String>) Kontroler.getInstanca().getUpiti();
		}
		
		if(nazivKontrolera.equals("Kontroler2")){
			tekstovi = (HashMap<String, String>) Kontroler2.getInstanca().getTekstovi();
			upiti = (HashMap<String, String>) Kontroler2.getInstanca().getUpiti();
		}
		

		List<Slicnost> nizSlicnosti = new ArrayList<>();
		
		
		Iterator it1 = tekstovi.keySet().iterator();
		  
		int brojacNeki = 0;
		while (it1.hasNext()) {
			System.out.println(brojacNeki++);
			
		   String keyPrvog = it1.next().toString();
		   String valuePrvog = tekstovi.get(keyPrvog).toString();
		  
		   
		   Iterator it2 = upiti.keySet().iterator();
		   
		   
		   int brojac = 0;
		   while (it2.hasNext()){
			   if(brojac==0){brojac++;continue;}
			   
			   String keyDrugog = it2.next().toString();
			   String valueDrugog = upiti.get(keyDrugog).toString();
			   
			   
			   
				String[] nizZaPoredjenje = new String[2];
				nizZaPoredjenje[0]=valuePrvog;
				nizZaPoredjenje[1]=valueDrugog;
				double sl = tfPojasnjenje.izvrsiZaPoredjenje(nizZaPoredjenje);
			   
				if(sl!=1.0){
					
					Slicnost instanca = new Slicnost();
					instanca.setSlicnost(sl);
					instanca.setPrviText(keyPrvog);
					instanca.setDrugiText(keyDrugog);
					instanca.setValuePrvog(valuePrvog);
					instanca.setValueDrugog(valueDrugog);
					
					nizSlicnosti.add(instanca);
					
				}
		   }
		   System.out.println("");
		   //System.out.println(key + " " + value);
		}
		System.out.println("GOTOVO\n\n\n\n\n\n");
		return nizSlicnosti;
		
    }
//    public static List<Slicnost> generisiSlicnostiIzmedjuTekstova(){
////    	
////    	PrintWriter writer = new PrintWriter("slicnosti.txt", "UTF-8");
////		writer.println(sadrzaj);
////		writer.close();
////		System.out.println("Fajl sacuvan");
//		
//		
//		double slicnost = -99;
//		String prviText="";
//		String drugiText="";
//		
//		HashMap<String,String> tekstovi = (HashMap<String, String>) Kontroler.getInstanca().getTekstovi();
//		
//		List<Slicnost> nizSlicnosti = new ArrayList<>();
//		
//		
//		Iterator it1 = tekstovi.keySet().iterator();
//		  
//		int brojacNeki = 0;
//		while (it1.hasNext()) {
//			System.out.println(brojacNeki++);
//			
//		   String keyPrvog = it1.next().toString();
//		   String valuePrvog = tekstovi.get(keyPrvog).toString();
//		  System.out.println(keyPrvog+" "+valuePrvog);
//		   Iterator it2 = tekstovi.keySet().iterator();
//		   
//		   
//		   int brojac = 0;
//		   while (it2.hasNext()){
//			   if(brojac==0){brojac++;continue;}
//			   
//			   String keyDrugog = it2.next().toString();
//			   String valueDrugog = tekstovi.get(keyDrugog).toString();
//			   
//			   
//			   
//				String[] nizZaPoredjenje = new String[2];
//				nizZaPoredjenje[0]=valuePrvog;
//				nizZaPoredjenje[1]=valueDrugog;
//				double sl = tfPojasnjenje.izvrsiZaPoredjenje(nizZaPoredjenje);
//			   
//				if(sl!=1.0){
//					//System.out.println("Izmedju prvog: "+valuePrvog+" i drugog "+valueDrugog+" slicnost iznosi: "+sl);
//					
//					Slicnost instanca = new Slicnost();
//					instanca.setSlicnost(sl);
//					instanca.setPrviText(keyPrvog);
//					instanca.setDrugiText(keyDrugog);
//					instanca.setValuePrvog(valuePrvog);
//					instanca.setValueDrugog(valueDrugog);
//					
//					nizSlicnosti.add(instanca);
//					
//				}
//		   }
//		   System.out.println("");
//		   //System.out.println(key + " " + value);
//		}
//		System.out.println("GOTOVO\n\n\n\n\n\n");
//		return nizSlicnosti;
//		
//    }
//    
    public static void vratiSlicnosti(String nazivFajla,String nazivKontrolera){
    	
    	List<Slicnost> lista = tfPojasnjenje.generisiSlicnosti(nazivKontrolera);
    	PrintWriter writer1;
		try {
			writer1 = new PrintWriter(nazivFajla, "UTF-8");
		
    	
    	writer1.write("{\n\t\"instance\":[\n\t");
    	
    	int brojac = 0;
    	for(Slicnost s : lista){
    		
    		writer1.write("{\t\n\t\t");
    		writer1.write("\"keyPrvog\":"+"\""+s.getPrviText()+"\""+",\n\t\t\t");
    		writer1.write("\"keyDrugog\":"+"\""+s.getDrugiText()+"\""+",\n\t\t\t");
    		writer1.write("\"slicnost\":"+s.getSlicnost()+",\n\t\t}");
    		if(brojac!=lista.size()-1)
    		writer1.write(",");
    		brojac++;
    	}
    	writer1.write("\n\t]\n}");
    	writer1.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

// {  
//   "timestamp":"2016-07-04T13:31:55",
//   "time":197,
//   "api":"tag",
//   "annotations":[  
//      {  
//         "id":31717,
//         "title":"United Kingdom",
//         "start":0,
//         "rho":"0.14053",
//         "end":2,
//         "spot":"uk"
//      },
//      {  
//         "id":37235,
//         "title":"Society",
//         "start":4,
//         "rho":"0.16124",
//         "end":11,
//         "spot":"society"
//      }
//      ]
// }
    
    public static void main(String[] args) {


    	//1.tip
    	//izvrsiSve(query,dokumenti);
    	
    	//vratiSlicnosti("slicnostProba.json");
    	vratiSlicnosti("slicnost.json","Kontroler");
    	vratiSlicnosti("slicnostProba.json","Kontroler2");
    	
    	
    	
    	
    	
//    	System.out.println("\nRecnik:");
//    	Metode.printHashMap(recnik);
//    	
//    	File[] files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/").listFiles();
//    	System.out.println("\n\n\n * * * * * * *");
//    	Metode.printHashMap(Metode.vratiFajlove(files));
//    	Metode.printHashMap(izvrsi(query,dokumenti));
    	
    	
	}
 
}
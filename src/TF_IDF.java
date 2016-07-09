import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import logika.Kontroler;
import logika.Metode;

public class TF_IDF {
	 
    int numOfWords;
    double[] idfVector;
    double[][] tfIdfMatrix;
    double[][] tfMatrix;
    String[] wordVector;
    int docLength[];
    static HashMap<String,Integer> recnik;
 
    public TF_IDF(String[] docs) {
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
                if (lastDocWordVector[wordIdx] < docIdx) {
                    lastDocWordVector[wordIdx] = docIdx;
                    docCountVector[wordIdx]++;
                }
            }
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
		
		
		System.out.println("*Similarity: "+similarity+"\n ************ ZA -"+Arrays.toString(vektorPrvi)+" - VS -"+Arrays.toString(vektorDrugi));
		return similarity;
	}

    public static void main(String[] args) {
//    	The test case is taken from Wikipedia TF-IDF where
//    	d1 = “this is a a sample” and d2=”this is another another example example example”
//    	Result of sorted TF-IDF values for d2 are
//    	[example, this, is, another]
//    	[0.20448053773699817, 0.13632035849133212, 0.043004285094854454, 0.043004285094854454]
    	
    	//String[] docs = CosineSimilarity.generisiTDZaRecnik();
    	
    	String d1 = "this is a a sample";
    	String d2 = "this is another another example example example";
    	
    	
    	
    	String c1 = "new york times";
    	String c2 = "new york post";
    	String c3 = "los angeles times";
    	String c4 = "I love drinking tea with my friends darling";
    	
    	String j1 = "James Vardy is having a party";//0
    	String j2 = "I am having a tea with my friends";//4/8
    	String j3 = "Ofcourse you are darling";//1/4
    	
    	String[] docs = new String[4];
    	docs[0]=j1; docs[1]=j2; docs[2]=j3; 
    	
    	docs[3]=c4;
    	//docs[0]=d1; docs[1]=d2;
    	TF_IDF instanca = new TF_IDF(docs);
    	double[][] niz1 = instanca.getTF_IDFMatrix(); 
    	
    	for(int i=0;i<niz1.length;i++){
    		System.out.println("\n");
    		for(int j=0;j<niz1[i].length;j++){
    			System.out.print(niz1[i][j]+" ");
    		}
    	}
    	
    	
    	double[] similarityNiz=null;
    	
    	
    	double[] nizPoredjenje = new double[niz1[0].length];
    	double[] nizQuery = new double[niz1[0].length];
    	
    	//System.out.println(niz1.length); - 4 broj dokumenata
    	
    	for(int i=0;i<niz1.length;i++){
    		System.out.println("\n");
    		for(int j=0;j<niz1[i].length;j++){

    			//if(i!=niz1.length-1) 
    				//System.out.print(niz1[i][j] + " ");
    			nizPoredjenje[j] = niz1[i][j];
    			nizQuery[j] = niz1[docs.length-1][j];//3 zato sto ima 4 teksta, a 3.tekst je nas query
    	    }
    		if(i!=niz1.length-1){//jer onda vraca poredjenje sa samim sobom
        	vratiSimilarity(nizQuery, nizPoredjenje);
    		//System.out.println("SIMILARITY: "+vratiSimilarity(niz1[i], similarityNiz) );
    	    System.out.println("***************************************************************");
    		}
	    	String[] niz2 = instanca.getWordVector();
	    	
	    	System.out.println("\nKRAJ\n");
	    	for(String text:niz2){System.out.println(text);}
	    	System.out.println("\nKRAJ\n");
	    	//Metode.printHashMap(mapa);
	    	
	    	
	    	//Metode.printHashMap(sortiraj(niz1, niz2));
    	
		}
    	
    }
 
}
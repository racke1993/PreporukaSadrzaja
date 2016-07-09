package logika;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 
public class JSONPamcenje {

    public static String vratiStringReciIzJsona(String filePath){
    	String listaReci = "";
    	// read the json file
    	try{
    				FileReader reader = new FileReader(filePath);

    				JSONParser jsonParser = new JSONParser();
    				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
    				JSONArray annotationsNiz= (JSONArray) jsonObject.get("annotations");
    				Iterator i = annotationsNiz.iterator();

    				// take each value from the json array separately
    				while (i.hasNext()) {
    					JSONObject innerObj = (JSONObject) i.next();
    					//System.out.println(innerObj.get("rho"));
    					//if((double)innerObj.get("rho")>0.1)
    					if(Double.parseDouble((String) innerObj.get("rho"))>0.1) 
    						{
    						String rec = (String) innerObj.get("title");
    						
    						rec = Metode.normalizacija(rec).toLowerCase().trim();
        					listaReci += " "+rec;
    						}
    					//listaReci.add((String) innerObj.get("title"));
    					//System.out.println("Title: "+ innerObj.get("title"));
    				}

    			} catch (FileNotFoundException ex) {
    				ex.printStackTrace();
    			} catch (IOException ex) {
    				ex.printStackTrace();
    			} catch (ParseException ex) {
    				ex.printStackTrace();
    			} catch (NullPointerException ex) {
    				ex.printStackTrace();
    			}
		return listaReci;
    }
    
    public static void generisiFajlove(File[] files){
    	
    	 for (File file : files) {
 	        if(!file.getPath().contains(".DS_Store")){
 	        	//System.out.println(file.getPath());
 	        	try {
 	        		String nazivFajla = Metode.izvuciNazivIzPutanje((String)file.getPath());
 	        		
					Metode.sacuvajFajlKonacno(nazivFajla,
							vratiStringReciIzJsona((String)file.getPath()));
					System.out.println("\n\n SACUVANO konacniFajlovi/"+(String)file.getPath()+"\n"+vratiStringReciIzJsona((String)file.getPath())+" \n\n");
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 	        	//System.out.println((String)file.getPath()+vratiStringFajla((String)file.getPath()));
 	        	}
 	    }
    	
    }
    
    public static double vratiInstancuSlicnostiIzJSONa(Slicnost s){
    	
    	String nazivPrvog = s.getPrviText();
    	String nazivDrugog = s.getDrugiText();
    	
    	double slicnost = 0;
		String vrednostPrvog = null;
		String vrednostDrugog = null;
    	
    	try{
    				FileReader reader = new FileReader("Slicnost.json");

    				JSONParser jsonParser = new JSONParser();
    				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
    				JSONArray instanceNiz= (JSONArray) jsonObject.get("instance");
    				Iterator i = instanceNiz.iterator();

    				// take each value from the json array separately
    				while (i.hasNext()) {
    					JSONObject innerObj = (JSONObject) i.next();
    					//System.out.println(innerObj.get("rho"));
    					//if((double)innerObj.get("rho")>0.1)
    					if(((String)innerObj.get("keyPrvog")).equals(nazivPrvog) && 
    					((String)innerObj.get("keyDrugog")).equals(nazivDrugog)
						){
    						slicnost = (double) innerObj.get("slicnost");
    						//vrednostPrvog = ((String) innerObj.get("vrednostPrvog"));
    						//vrednostDrugog = ((String) innerObj.get("vrednostDrugog"));
    					}
    					return slicnost;
    				}

    			} catch (FileNotFoundException ex) {
    				ex.printStackTrace();
    			} catch (IOException ex) {
    				ex.printStackTrace();
    			} catch (ParseException ex) {
    				ex.printStackTrace();
    			} catch (NullPointerException ex) {
    				ex.printStackTrace();
    			}
		return -99;
    }
    
    
    public static void main(String[] args) {
    	//String reci =
    			//vratiStringReciIzJsona("fajloviJSON/Trump comments 'clearly' racist, says Gary Johnson amid antisemitism furor | US news | The Guardian.json");
    			
    			File[] files = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviJSON/").listFiles();
    			
    			
    			//NAJBITNIJA METODA
    			//generisiFajlove(files);
    			Slicnost s = new Slicnost();
    			s.setPrviText("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/osmi.txt");
    			s.setDrugiText("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviText/prvi.txt");
    			
    			System.out.println(vratiInstancuSlicnostiIzJSONa(s));
    			
    			
    	//String reci2 = vratiStringReciIzJsona("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajloviJSON/david-cameron-concessions-syrian-child-refugees.json");
    	//System.out.println(reci);
    }
    
    
}
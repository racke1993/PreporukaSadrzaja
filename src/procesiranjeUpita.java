import java.util.Arrays;

import logika.Metode;

public class procesiranjeUpita {

	String[] nizPredefinanihUpita;
	
	public String[] getNizPredefinanihUpita() {
		return nizPredefinanihUpita;
	}

	public void setNizPredefinanihUpita(String[] nizPredefinanihUpita) {
		this.nizPredefinanihUpita = nizPredefinanihUpita;
	}

	public procesiranjeUpita() {
		
		nizPredefinanihUpita = new String[9];
		nizPredefinanihUpita[0] = "world news global terrorists europe asia africa australia middle east";
		nizPredefinanihUpita[1] = "sport football basketball soccer volleyball rugby golf cycling racing mma";
		nizPredefinanihUpita[2] = "culture film tv radio music games books arts design classical pop rap metal rock";
		nizPredefinanihUpita[3] = "business stocks shares money banking retail market payments investments risk";
		nizPredefinanihUpita[4] = "lifestyle health fitness recipes advices clothing love family women home food";
		nizPredefinanihUpita[5] = "fashion dress suits jacket bags denim luxurious blogs style trend tattoo hair";
		nizPredefinanihUpita[6] = "environment wildlife flood climate change energy pollution global warming";
		nizPredefinanihUpita[7] = "technology it ios android windows tablets laptops games microsoft apple facebook twitter";
		nizPredefinanihUpita[8] = "travel holiday hotels hostel airbnb sea lake mountain explore tourism";
//		nizPredefinanihUpita[9] = "";
//		nizPredefinanihUpita[10] = "";
//		nizPredefinanihUpita[11] = "";
//		nizPredefinanihUpita[12] = "";
//		nizPredefinanihUpita[13] = "";
//		nizPredefinanihUpita[14] = "";
//		nizPredefinanihUpita[15] = "";
//		nizPredefinanihUpita[16] = "";
//		nizPredefinanihUpita[17] = "";
//		nizPredefinanihUpita[18] = "";
//		nizPredefinanihUpita[19] = "";
		
	}
	//metoda vraca indeks odgovarajuceg predloga
	public int procesirajUpit(String upit){
		
		int indeks = -1;
		
		for(int i = 0;i<this.getNizPredefinanihUpita().length;i++){
			
			for(int j = 0 ; j<this.getNizPredefinanihUpita()[i].split(" ").length;j++){
				
				if(upit.equals(this.getNizPredefinanihUpita()[i].split(" ")[j])){
					return i;
				}
				
			}
			
		}
		
		return indeks;
		
	}
	public static void main(String[] args) {
		procesiranjeUpita p = new procesiranjeUpita();
		//System.out.println(Arrays.toString(p.getNizPredefinanihUpita()));
		//System.out.println(p.getNizPredefinanihUpita()[p.procesirajUpit("travel")]);
		System.out.println(Metode.normalizacija("Ovo je PROBA NEKOG moj; / ., hell y((((eah teksta.. ma da").trim());
	}
	
}

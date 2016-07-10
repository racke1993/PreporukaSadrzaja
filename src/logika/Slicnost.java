package logika;

public class Slicnost {

	String prviText;
	String drugiText;
	String valuePrvog;
	String valueDrugog;
	double slicnost;
	
	public Slicnost() {
		// TODO Auto-generated constructor stub
	}
	public Slicnost(String prviText, String drugiText){
		this.prviText = prviText;
		this.drugiText = drugiText;
	}
	public Slicnost(String prviText, String drugiText, double slicnost,String valuePrvog,String valueDrugog){
		this.prviText = prviText;
		this.drugiText = drugiText;
		this.slicnost = slicnost;
		this.valuePrvog = valuePrvog;
		this.valueDrugog = valueDrugog;
	}
	public String getValuePrvog() {
		return valuePrvog;
	}
	public void setValuePrvog(String valuePrvog) {
		this.valuePrvog = valuePrvog;
	}
	public String getValueDrugog() {
		return valueDrugog;
	}
	public void setValueDrugog(String valueDrugog) {
		this.valueDrugog = valueDrugog;
	}
	public String getPrviText() {
		return prviText;
	}
	public void setPrviText(String prviText) {
		this.prviText = prviText;
	}
	public String getDrugiText() {
		return drugiText;
	}
	public void setDrugiText(String drugiText) {
		this.drugiText = drugiText;
	}
	public double getSlicnost() {
		return slicnost;
	}
	public void setSlicnost(double slicnost) {
		this.slicnost = slicnost;
	}
	
	@Override
	public String toString() {

		return "Prvi text: "+prviText+"\n drugi text: "+drugiText+"\n imaju slicnost : "+slicnost+"\nValue prvog je:"
				+valuePrvog+"\ndok je drugog: "+valueDrugog;
		
	}
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebScrapper {

	public static WebDriver driver = new FirefoxDriver();

	/**
	 * Open the test website.
	 */
	public List<String> napuniIVratiListu(){
		List<String> listaAdresa = new ArrayList<>();
		listaAdresa.add("http://www.theguardian.com/uk/rss");
		listaAdresa.add("http://www.ft.com/rss/home/us");
		listaAdresa.add("http://www.telegraph.co.uk/sport/rss.xml");
		listaAdresa.add("http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
		listaAdresa.add("http://feeds.bbci.co.uk/news/rss.xml");
		return listaAdresa;
	}
	public void openTestSite(String link) {
		//driver.navigate().to("http://testing-ground.scraping.pro/login");
		driver.navigate().to(link);
	}

	public void getText(String nazivFajla) throws IOException {
		//String text = driver.findElement(By.xpath("//div[@id='case_login']/h3")).getText();
		String text2 = driver.findElement(By.tagName("body")).getText();
		//System.out.println(text2);
		if(nazivFajla.contains("http://www") || nazivFajla.contains("https://www"))
		{
			nazivFajla = nazivFajla.substring(11); 
			System.out.println(nazivFajla+"****");
		}
		File file = new File("/Users/Radomir/Documents/workspace/SeleniumProjekat/fajlovi/"+nazivFajla+".txt");
		file.getParentFile().mkdirs();
		//FileWriter writer = new FileWriter(file);
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fajlovi/"+nazivFajla+".txt"), "utf-8"));
		writer.write(text2);
		writer.close();

	}

	/**
	 * Saves the screenshot
	 * 
	 * @throws IOException
	 */
	public void saveScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screenshot.png"));
	}

	public void closeBrowser() {
		driver.close();
	}

	public static void main(String[] args) throws IOException {
		WebScrapper webSrcapper = new WebScrapper();
		String textMoj = driver.findElement(By.cssSelector("body")).getText();
		System.out.println(textMoj);
		RSSReader rss = new RSSReader();
		
		List<String> listaAdresa = webSrcapper.napuniIVratiListu();//lista rss feedova 
		for(String adresa : listaAdresa){
		List<String> listaLinkova = rss.readRSS(adresa);//lista clanaka iz jednog rss feeda
		
		for(int i=2;i<listaLinkova.size()-2;i++){
			String link = listaLinkova.get(i);
			webSrcapper.openTestSite(link);
			webSrcapper.getText(link);
		}
		
		//webSrcapper.saveScreenshot();
		}
		webSrcapper.closeBrowser();
	}
}
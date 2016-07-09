
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public class RSSReader {

	public static void main(String[] args) throws IOException{
		
		//String[] feedovi = new String[5];
		//napuniNiz(feedovi);
		//System.out.println(feedovi[2]);
		for(String adresa : readRSS("http://feeds.bbci.co.uk/news/rss.xml")){
			System.out.println(adresa);
			}
		
	}

	public static List<String> readRSS(String adresa) throws IOException {
		URL rssUrl = new URL(adresa);
		BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
		//String sourceCode ="";
		List<String> nizAdresa=new ArrayList<>();
		String line;
		while((line=in.readLine())!=null){
			/*
			if(line.contains("<title>")){
				int firstPos = line.indexOf("<title>");
				String temp = line.substring(firstPos);
				temp = temp.replace("<title>", "");
				int lastPos = temp.indexOf("</title>");
				temp = temp.substring(0, lastPos);
				sourceCode += temp+"\n";
			}
			*/
			if(line.contains("<link>")){
				int firstPos = line.indexOf("<link>");
				String temp = line.substring(firstPos);
				temp = temp.replace("<link>", "");
				int lastPos = temp.indexOf("</link>");
				temp = temp.substring(0, lastPos);
				//sourceCode += temp+"\n";
				nizAdresa.add(temp);
			}
		}
		in.close();
		return nizAdresa;
	}
	
}

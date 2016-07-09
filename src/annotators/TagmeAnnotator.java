/**
 * @author "Nikola Milikic"
 * nikola.milikic@gmail.com
 * 
 * @author "Uros Krcadinac"
 * uros@krcadinac
 *
 * Intelligent Systems (FON, University of Belgrade) - http://is.fon.rs/
 * GOOD OLD AI Research Lab - http://goodoldai.org
 * 
 * TagMe Website:
 * http://tagme.di.unipi.it/
 * http://tagme.di.unipi.it/tagme_help.html 
 * 
 * December, 2013.
 * 
 */
package annotators;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import util.HttpExecutor;

public class TagmeAnnotator implements Annotator {
	
	/*
	 * In order to access this service you need for a key, that is a simple alphanumeric code.
	 * http://tagme.di.unipi.it/tagme_help.html
	 * 
	 * The current key is expired. In order to obtain a new key, 
	 * you should send an email to: tagme@di.unipi.it
	 */
	private static final String apiKey = "abc2020ZtQl192835As";
	
	/*
	 * This parameter can be used to finely tune the disambiguation process: 
	 * an higher value will favor the most-common topics for a spot, 
	 * whereas a lower value will take more into account the context. 
	 * This parameter could be useful when annotating particularly fragmented text, 
	 * like tweets, where it would be better to favor most common topics 
	 * because the context is less reliable for disambiguation. 
	 * Supported values are floats in the range [0,0.5], default is 0.3.
	 * http://tagme.di.unipi.it/tagme_help.html
	 */
	public static final String epsilon = "0.5";
	
	@Override
	public String getConcepts(String text) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", apiKey);
		parameters.put("text", text);
		parameters.put("epsilon", epsilon);
		
		try {
			return HttpExecutor.executePost("http://tagme.di.unipi.it/tag", parameters);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

/**
 * @author "Nikola Milikic"
 * nikola.milikic@gmail.com
 *
 * GOOD OLD AI Research Lab - http://goodoldai.org
 * Intelligent Systems (FON, University of Belgrade) - http://is.fon.rs/
 * 
 * Important paper:
 * http://www.cs.waikato.ac.nz/~ihw/papers/12-DM-IHW-OStoolkit-wikimining.pdf 
 * 
 * JARs - Apache HttpComponents Library:
 * http://hc.apache.org/
 * 
 * December, 2013.
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.util.EntityUtils;

public class HttpExecutor {
	
	/*
	 * We are using the Apache HttpComponents library for the HTTP GET request.
	 */
	public static String executeHttpGet(String scheme, String host, String path, Map<String, String> parameters) throws ClientProtocolException, IOException, URISyntaxException {
		
		URIBuilder builder = new URIBuilder();
		builder.setScheme(scheme).setHost(host).setPath(path);
		
		for (Entry<String, String> paramEntry : parameters.entrySet()) {
			builder.setParameter(paramEntry.getKey(), paramEntry.getValue());
		}
		
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);
		httpget.setHeader("Content-Type", "application/xml");
		
		HttpClient client = new DefaultHttpClient();
		
		// Proxy configuration
//		HttpHost proxy = new HttpHost("proxy.fon.rs", 8080, "http");
//		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		HttpResponse response = client.execute(httpget);
		
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
//		System.out.println(responseString);
		
		return responseString;
	}
	
	/*
	 * We are using the Apache HttpComponents library for the HTTP POST.
	 */
	public static String executePost(String address, Map<String, String> parameters) throws ClientProtocolException, IOException {
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

		for (Entry<String, String> param : parameters.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}
		
		HttpPost post = new HttpPost(address);
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		return execute(post);
	}
	
	/*
	 * We execute a HTTP POST
	 */
	public static String execute(HttpPost post) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		
		HttpParams params = new SyncBasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, CharEncoding.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, true);
        post.setParams(params);
        
        // Proxy configuration
//      HttpHost proxy = new HttpHost("proxy.fon.rs", 8080, "http");
//		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	      
        StringBuffer buffer = new StringBuffer();
        String line = "";
	      
        while ((line = rd.readLine()) != null) {
        	buffer.append(line);
        }

        return buffer.toString();
    }
}

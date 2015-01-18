package fr.istic.gla.gwtPages;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;



public class HttpRequestGWT {
	
	/**
	 * 
	 * @param httpRequest
	 * @param callback
	 */
	public void executeGET(String httpRequest, RequestCallback callback) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, URL.encode(httpRequest));
	    rb.setCallback(callback);
	    try {
	        rb.send();
	    }
	    catch (RequestException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * @param httpRequest
	 * @param callback
	 * @param header
	 */
	public void executePOST(String httpRequest, RequestCallback callback, String header, String JSONToSend) {
		Window.alert("Dans executePOST");
		
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, URL.encode(httpRequest));
		
		String tab [] = header.split(",");
		
		Window.alert("Premier element : " + tab[0] + "\nDeuxieme element : " + tab[1]);
		
		rb.setHeader("Content-Type", "application/json");
		
		Window.alert("élément à insérer : " + JSONToSend);
		
		rb.setRequestData(JSONToSend);
		 
	    rb.setCallback(callback);
	    
	    
	    try {
	        rb.send();
	    }
	    catch (RequestException e) {
	        e.printStackTrace();
	    }
	}
}

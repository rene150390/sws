package org.mariano.sanabria.santander.utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class ResponseHeader {
	
	
	
	public ResponseHeader(){}
	
	
	
	public static String ultimoModificado(String url){
		Map<String, List<String>> map = null;
		try {
		   
			URL obj = new URL(url);
		    URLConnection conn = obj.openConnection();
		    map = conn.getHeaderFields();
		    System.out.println(""+map);
		   
		} catch (Exception e) {
		    	e.printStackTrace();
		}
		return map.get("Last-Modified").get(0);
	}

	
}

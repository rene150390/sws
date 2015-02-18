package org.mariano.sanabria.santander.utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class ResponseHeader {
	
	private URL obj;
	private URLConnection conn;
	private Map<String, List<String>> map;
	
	public ResponseHeader(){}
	
	
	
	public String prueba(String url){
	   
		try {
		   
		   obj = new URL(url);
		   conn = obj.openConnection();
		   map = conn.getHeaderFields();
		   
		} catch (Exception e) {
		    	e.printStackTrace();
		}
		return map.get("Last-Modified").get(0);
	}

	public URL getObj() {
		return obj;
	}

	public void setObj(URL obj) {
		this.obj = obj;
	}

	public URLConnection getConn() {
		return conn;
	}

	public void setConn(URLConnection conn) {
		this.conn = conn;
	}

	public Map<String, List<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}
}

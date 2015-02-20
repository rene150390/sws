package org.mariano.sanabria.santander.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mariano.sanabria.santander.pojo.ImgUpdatePOJO;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonParser {
	
	private Gson gson;
	private String json;
	
	public JsonParser(){}
	
	public void printJSON(HttpServletRequest r) throws IOException{
		
		ImgUpdatePOJO pojo = new ImgUpdatePOJO();
		pojo.setImgurl("url");
		pojo.setIsupdate("Si");
		gson = new Gson();
		json = gson.toJson(pojo);
		
	}
	public static void createJSON(HttpServletRequest r, List<ImgUpdatePOJO> list, String nameJSON) throws IOException{
		@SuppressWarnings("deprecation")
		String location = r.getRealPath("/");
		
		File file = new File(location+"/"+nameJSON+".json");
		JsonWriter writer = new JsonWriter(new FileWriter(file));
		writer.beginObject();
		writer.name("listaimagenes");
		writer.beginArray();
		for(int i = 0; i < list.size(); i++){
			ImgUpdatePOJO member = list.get(i);
			writer.beginObject();
			writer.name("imgurl").value(member.getImgurl());
			writer.name("isupdate").value(member.getIsupdate());
			writer.name("lastmodified").value(member.getLastmodified());
			writer.endObject();
		}
		writer.endArray();
		writer.endObject();
		writer.close();
	}
	
	public static List<String> readJSON(HttpServletRequest r, String nameJSON) throws FileNotFoundException{
		@SuppressWarnings("deprecation")
		String location = r.getRealPath("/");
		File file = new File(location+"/"+nameJSON+".json");
		JsonReader reader = new JsonReader(new FileReader(file));
		List<String> lista = new ArrayList<String>();
		try {
			reader.beginObject();
			while (reader.hasNext()) {
				String name = reader.nextName();
				if(name.equals("listaimagenes")){
					reader.beginArray();
					
					while (reader.hasNext()) {
						reader.beginObject();
						while(reader.hasNext()){
							String nombre = reader.nextName();
							if (nombre.equals("imgurl")) 
								  System.out.println(reader.nextString());
							 else if (nombre.equals("isupdate"))
								 	System.out.println(reader.nextString());
							 else if (nombre.equals("lastmodified")){
								 	String modificado = reader.nextString();
								 	System.out.println(modificado);
								 	lista.add(modificado);
							 	 }else{
							 		 reader.skipValue();
							 	 }
							 
							
						}
						reader.endObject();
						
					}
					reader.endArray();
					
				}
				
			}
			
			reader.endObject();
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
}

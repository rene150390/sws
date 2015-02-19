package org.mariano.sanabria.santander.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mariano.sanabria.santander.pojo.ImgUpdatePOJO;

import com.google.gson.Gson;
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
	public static void createJSON(HttpServletRequest r, List<ImgUpdatePOJO> list) throws IOException{
		@SuppressWarnings("deprecation")
		String location = r.getRealPath("/");
		
		
		File file = new File(location+"/file.json");
		
	
			JsonWriter writer = new JsonWriter(new FileWriter(file));
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
			writer.close();
		
	
		
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

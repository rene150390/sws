package org.mariano.sanabria.santander.controller;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mariano.sanabria.santander.pojo.ImgUpdatePOJO;
import org.mariano.sanabria.santander.utils.JsonParser;
import org.mariano.sanabria.santander.utils.ResponseHeader;
import org.mariano.sanabria.santander.utils.ValidaURL;
import org.mariano.sanabria.santander.wrapper.WSetImages;
import org.mariano.sanabria.santander.wrapper.WebServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
public class MainController {
	
	@Autowired ResponseHeader responseHeader;
	
	@RequestMapping(value="/")
	public String getHomePage() {
		return "index";
	}
	
	@RequestMapping(value="/a", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel() {
		String urlImg = "http://santander-wordpress.s3.amazonaws.com/wp-content/uploads/2014/12/santander_";
		String imgExt = ".jpg";
		String fullURL ="";
		
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		while(true){
			cont ++;
			if(cont < 10){
				fullURL = urlImg+"0"+cont+""+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
				responseHeader.prueba(fullURL);
			}else{
				fullURL = urlImg+cont+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
			}
		
		}
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	
	@RequestMapping(value="/b", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getImg() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("http://santander-wordpress.s3.amazonaws.com/wp-content/uploads/2014/11/promo-puntosS.jpg");
		lista.add("http://santander-wordpress.s3.amazonaws.com/wp-content/uploads/2014/11/promo-puntosB.jpg");
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, 2);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/c", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel1() {
		String urlImg = "http://santander-wordpress.s3.amazonaws.com/wp-content/uploads/2014/12/paso";
		String imgExt = ".jpg";
		String fullURL ="";
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		while(true){
			cont ++;
			
				fullURL = urlImg+cont+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
			
		
		}
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/d", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel2() {
		String urlImg = "http://santander-wordpress.s3.amazonaws.com/wp-content/uploads/2014/12/unlimited_";
		String imgExt = ".jpg";
		String fullURL ="";
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		while(true){
			cont ++;
			if(cont < 10){
				fullURL = urlImg+"0"+cont+""+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
			}else{
				fullURL = urlImg+cont+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
			}
		
		}
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/p", method=RequestMethod.GET)
	public void pueba(HttpServletRequest r) throws IOException {
		ResponseHeader res = new ResponseHeader();
		JsonParser json = new JsonParser();
		json.printJSON(r);
		System.out.println(res.prueba("http://54.193.182.168:8980/DummyHA/img/imagen2.png"));
	}
	@RequestMapping(value="/prueba", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse test(HttpServletRequest r) throws IOException {
		String urlImg = "http://54.193.182.168:8980/DummyHA/img/imagen";
		String imgExt = ".png";
		String fullURL ="";
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		while(true){
			
			cont ++;
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			
			fullURL = urlImg+cont+imgExt;
			pojo.setImgurl(fullURL);

			pojo.setIsupdate("");
			
			if(!ValidaURL.exists(fullURL))
				break;
			pojo.setLastmodified(ResponseHeader.prueba(fullURL));
			lista.add(fullURL);
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate);
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
}

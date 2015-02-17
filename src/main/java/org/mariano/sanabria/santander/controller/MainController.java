package org.mariano.sanabria.santander.controller;



import java.util.ArrayList;
import java.util.List;

import org.mariano.sanabria.santander.utils.ValidaURL;
import org.mariano.sanabria.santander.wrapper.WSetImages;
import org.mariano.sanabria.santander.wrapper.WebServiceResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class MainController {
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
			if(cont < 10){
				fullURL = urlImg+""+cont+""+imgExt;
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
}

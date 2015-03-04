package org.mariano.sanabria.santander.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mariano.sanabria.santander.pojo.ImgUpdatePOJO;
import org.mariano.sanabria.santander.utils.JsonParser;
import org.mariano.sanabria.santander.utils.ResponseHeader;
import org.mariano.sanabria.santander.utils.ValidaURL;
import org.mariano.sanabria.santander.wrapper.WSetImages;
import org.mariano.sanabria.santander.wrapper.WSetUpdateStatus;
import org.mariano.sanabria.santander.wrapper.WebServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
public class MainController {
	
	@Autowired ResponseHeader responseHeader;
	
	@RequestMapping(value="/")
	public String getHomePage() {
		return "index";
	}
	
	@RequestMapping(value="/a", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/santander/santander_";
		String imgExt = ".jpg";
		String fullURL ="";
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		
		while(true){
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			cont ++;
			if(cont < 10){
				fullURL = urlImg+"0"+cont+""+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
				pojo.setIsupdate("");
				pojo.setImgurl(fullURL);
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			}else{
				fullURL = urlImg+cont+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
				pojo.setIsupdate("");
				pojo.setImgurl(fullURL);
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			}
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "carrusel"+imei);
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/a1", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarruselUpdate(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/santander/santander_";
		String imgExt = ".jpg";
		String fullURL ="";
		List<String> modificadas = new ArrayList<String>();
		List<String> nuevas = new ArrayList<String>();
		int cont = 0;
		
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		List<String> cadenasModificado = JsonParser.readJSON(r, "carrusel"+imei);
		String isupdate = "No";
		while(true){
			
			cont ++;
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			if(cont < 10){
				fullURL = urlImg+"0"+cont+""+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
				pojo.setImgurl(fullURL);
				if(cadenasModificado.size() > cont -1){
					if(pojo.getLastmodified().equals(cadenasModificado.get(cont-1))){
						pojo.setIsupdate("no");
						//isupdate = "no";
					}else{
						pojo.setIsupdate("yes");
						modificadas.add(pojo.getImgurl());
						isupdate = "yes";
					}
				}else{
					pojo.setIsupdate("yes");
					nuevas.add(pojo.getImgurl());
					isupdate = "yes";
				}
				
			}else{
				fullURL = urlImg+cont+imgExt;
				pojo.setImgurl(fullURL);

				
				
				if(!ValidaURL.exists(fullURL))
					break;
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
				if(cadenasModificado.size() > cont -1){
					if(pojo.getLastmodified().equals(cadenasModificado.get(cont-1))){
						pojo.setIsupdate("no");
						//isupdate = "no";
					}else{
						pojo.setIsupdate("yes");
						modificadas.add(pojo.getImgurl());
						isupdate = "yes";
					}
				}else{
					pojo.setIsupdate("yes");
					nuevas.add(pojo.getImgurl());
					isupdate = "yes";
				}
			}
			
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "carrusel"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetUpdateStatus img = new WSetUpdateStatus("Ok", isupdate, modificadas, nuevas, cont-1);
		response.setRespuesta(img);
		return response;
	}
	
	@RequestMapping(value="/b", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getImg(HttpServletRequest r, @RequestParam String imei) throws IOException {
		
		List<String> lista = new ArrayList<String>();
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		ImgUpdatePOJO pojo = new ImgUpdatePOJO();
		lista.add("http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_01.jpg");
		pojo.setIsupdate("");
		pojo.setImgurl("http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_01.jpg");
		pojo.setLastmodified(ResponseHeader.ultimoModificado("http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_01.jpg"));
		listaUpdate.add(pojo);
		
		ImgUpdatePOJO pojo1 = new ImgUpdatePOJO();
		lista.add("http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_02.jpg");
		pojo1.setIsupdate("");
		pojo1.setImgurl("http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_02.jpg");
		pojo1.setLastmodified(ResponseHeader.ultimoModificado("http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_02.jpg"));
		listaUpdate.add(pojo1);
		
		JsonParser.createJSON(r, listaUpdate, "dos"+imei);
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, 2);
		response.setRespuesta(img);
		return response;
	}
	
	@RequestMapping(value="/b1", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getImgUpdate(HttpServletRequest r, @RequestParam String imei) throws IOException {
		
		String isupdate = "no";
		
		List<String> lista = new ArrayList<String>();
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		List<String> cadenasModificado = JsonParser.readJSON(r, "dos"+imei);
		List<String> modificadas = new ArrayList<String>();
		List<String> nuevas = new ArrayList<String>();
		ImgUpdatePOJO pojo = new ImgUpdatePOJO();
		
		String urlS = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_01.jpg"; 
		lista.add(urlS);
		pojo.setImgurl(urlS);
		pojo.setLastmodified(ResponseHeader.ultimoModificado(urlS));
		listaUpdate.add(pojo);
		
		if(pojo.getLastmodified().equals(cadenasModificado.get(0))){
			pojo.setIsupdate("no");
			//isupdate = "no";
		}else{
			pojo.setIsupdate("yes");
			modificadas.add(pojo.getImgurl());
			isupdate = "yes";
		}
		ImgUpdatePOJO pojo1 = new ImgUpdatePOJO();
		String urlB = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_02.jpg"; 
		lista.add(urlB);
		pojo1.setImgurl(urlB);
		pojo1.setLastmodified(ResponseHeader.ultimoModificado(urlB));
		listaUpdate.add(pojo1);
		
		if(pojo1.getLastmodified().equals(cadenasModificado.get(1))){
			pojo1.setIsupdate("no");
			//isupdate = "no";
		}else{
			pojo1.setIsupdate("yes");
			modificadas.add(pojo1.getImgurl());
			isupdate = "yes";
		}
		
		JsonParser.createJSON(r, listaUpdate, "dos"+imei);
		WebServiceResponse response = new WebServiceResponse();
		WSetUpdateStatus img = new WSetUpdateStatus("Ok", isupdate, modificadas, nuevas, 2);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/c", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel1(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/instructions/paso";
		String imgExt = ".jpg";
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
			pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			lista.add(fullURL);
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "carrusel1"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/c1", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel1Update(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/instructions/paso";
		String imgExt = ".jpg";
		String fullURL ="";
		int cont = 0;
		List<String> modificadas = new ArrayList<String>();
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		List<String> cadenasModificado = JsonParser.readJSON(r, "carrusel1"+imei);
		List<String> nuevas = new ArrayList<String>();
		String isupdate = "No";
		while(true){
			
			cont ++;
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			
			fullURL = urlImg+cont+imgExt;
			pojo.setImgurl(fullURL);

			
			
			if(!ValidaURL.exists(fullURL))
				break;
			pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			if(cadenasModificado.size() > cont -1){
				if(pojo.getLastmodified().equals(cadenasModificado.get(cont-1))){
					pojo.setIsupdate("no");
					//isupdate = "no";
				}else{
					pojo.setIsupdate("yes");
					isupdate = "yes";
					modificadas.add(pojo.getImgurl());
				}
			}else{
				pojo.setIsupdate("yes");
				isupdate = "yes";
				nuevas.add(pojo.getImgurl());
			}
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "carrusel1"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetUpdateStatus img = new WSetUpdateStatus("Ok", isupdate, modificadas, nuevas, cont-1);
		response.setRespuesta(img);
		return response;
	}
	
	@RequestMapping(value="/d", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel2(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/unlimited/unlimited_";
		String imgExt = ".jpg";
		String fullURL ="";
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		
		while(true){
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			cont ++;
			if(cont < 10){
				fullURL = urlImg+"0"+cont+""+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
				pojo.setIsupdate("");
				pojo.setImgurl(fullURL);
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			}else{
				fullURL = urlImg+cont+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				lista.add(fullURL);
				pojo.setIsupdate("");
				pojo.setImgurl(fullURL);
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			}
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "carrusel2"+imei);
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/d1", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getCarrusel2Update(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/unlimited/unlimited_";
		String imgExt = ".jpg";
		String fullURL ="";
		
		int cont = 0;
		List<String> modificadas = new ArrayList<String>();
		List<String> nuevas = new ArrayList<String>();
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		List<String> cadenasModificado = JsonParser.readJSON(r, "carrusel2"+imei);
		String isupdate = "No";
		while(true){
			
			cont ++;
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			if(cont < 10){
				fullURL = urlImg+"0"+cont+""+imgExt;
				if(!ValidaURL.exists(fullURL))
					break;
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
				pojo.setImgurl(fullURL);
				if(cadenasModificado.size() > cont -1){
					if(pojo.getLastmodified().equals(cadenasModificado.get(cont-1))){
						pojo.setIsupdate("no");
						//isupdate = "no";
					}else{
						pojo.setIsupdate("yes");
						isupdate = "yes";
						modificadas.add(fullURL);
					}
				}else{
					isupdate = "yes";
					nuevas.add(fullURL);
				}
				
			}else{
				fullURL = urlImg+cont+imgExt;
				pojo.setImgurl(fullURL);

				
				
				if(!ValidaURL.exists(fullURL))
					break;
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
				if(cadenasModificado.size() > cont -1){
					if(pojo.getLastmodified().equals(cadenasModificado.get(cont-1))){
						pojo.setIsupdate("no");
						//isupdate = "no";
					}else{
						pojo.setIsupdate("yes");
						isupdate = "yes";
						modificadas.add(pojo.getImgurl());
					}
				}else{
					pojo.setIsupdate("yes");
					isupdate = "yes";
					nuevas.add(pojo.getImgurl());
				}
			}
			
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "carrusel2"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetUpdateStatus img = new WSetUpdateStatus("Ok", isupdate, modificadas, nuevas, cont-1);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/e", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getPromos(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_";
		String imgExt = ".jpg";
		String fullURL ="";
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		int cont = 0;
		List<String> lista = new ArrayList<String>();
		boolean flag = true;
		while(flag){
			
			cont ++;
			for(int i = 1; i < 3; i++){
				ImgUpdatePOJO pojo = new ImgUpdatePOJO();
				fullURL = urlImg+""+cont+""+i+""+imgExt;
				if(!ValidaURL.exists(fullURL)){
					flag = false;
					break;
				}
				lista.add(fullURL);
				pojo.setIsupdate("");
				pojo.setImgurl(fullURL);
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
				listaUpdate.add(pojo);
			}
			
		
		}
		JsonParser.createJSON(r, listaUpdate, "promos"+imei);
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, (cont-1)*2);
		response.setRespuesta(img);
		return response;
	}
	@RequestMapping(value="/e1", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse getPromosUpdate(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://s3-us-west-1.amazonaws.com/anzen-mobile-apps/img_catalog/promos/promo_";
		String imgExt = ".jpg";
		String fullURL ="";
		int cont = 0;
		List<String> modificadas = new ArrayList<String>();
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();
		List<String> cadenasModificado = JsonParser.readJSON(r, "promos"+imei);
		List<String> nuevas = new ArrayList<String>();
		String isupdate = "No";
		boolean flag = true;
		int contador = 0;
		while(flag){
			
			cont ++;
			
			for(int i = 1; i < 3; i++){
				contador ++;
				ImgUpdatePOJO pojo = new ImgUpdatePOJO();
				fullURL = urlImg+cont+i+imgExt;
				pojo.setImgurl(fullURL);
				if(!ValidaURL.exists(fullURL)){
					flag = false;	
					break;
				}
				pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			
				
					if(cadenasModificado.size() > contador -1){
						if(pojo.getLastmodified().equals(cadenasModificado.get(contador-1))){
							pojo.setIsupdate("no");
							//isupdate = "no";
						}else{
							pojo.setIsupdate("yes");
							isupdate = "yes";
							modificadas.add(pojo.getImgurl());
						}
					}else{
						pojo.setIsupdate("yes");
						isupdate = "yes";
						nuevas.add(pojo.getImgurl());
					}
			
				
				listaUpdate.add(pojo);
			}
			
		
		}
		JsonParser.createJSON(r, listaUpdate, "promos"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetUpdateStatus img = new WSetUpdateStatus("Ok", isupdate, modificadas, nuevas, (cont-1)*2);
		response.setRespuesta(img);
		return response;
	}
	/*@RequestMapping(value="/prueba", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse test(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://54.193.182.168:8980/DummyHA/img/imagen";
		String imgExt = ".jpg";
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
			pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			lista.add(fullURL);
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "file"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetImages img = new WSetImages("Ok", lista, cont-1);
		response.setRespuesta(img);
		return response;
	}
	
	@RequestMapping(value="/prueba1", method=RequestMethod.GET)
	public @ResponseBody WebServiceResponse test1(HttpServletRequest r, @RequestParam String imei) throws IOException {
		String urlImg = "http://54.193.182.168:8980/DummyHA/img/imagen";
		String imgExt = ".png";
		String fullURL ="";
		List<String> modificadas = new ArrayList<String>();
		List<String> nuevas = new ArrayList<String>();
		int cont = 0;
		
		List<ImgUpdatePOJO> listaUpdate = new ArrayList<ImgUpdatePOJO>();	
		List<String> cadenasModificado = JsonParser.readJSON(r, "file"+imei);
		String isupdate = "No";
		while(true){
			
			cont ++;
			ImgUpdatePOJO pojo = new ImgUpdatePOJO();
			
			fullURL = urlImg+cont+imgExt;
			pojo.setImgurl(fullURL);

			
			
			if(!ValidaURL.exists(fullURL))
				break;
			pojo.setLastmodified(ResponseHeader.ultimoModificado(fullURL));
			if(cadenasModificado.size() > cont -1){
				if(pojo.getLastmodified().equals(cadenasModificado.get(cont-1))){
					pojo.setIsupdate("no");
					//isupdate = "no";
				}else{
					pojo.setIsupdate("yes");
					isupdate = "yes";
					modificadas.add(pojo.getImgurl());
				}
			}else{
				pojo.setIsupdate("yes");
				isupdate = "yes";
				nuevas.add(pojo.getImgurl());
			}
			listaUpdate.add(pojo);
		
		}
		JsonParser.createJSON(r, listaUpdate, "file"+imei);
		
		WebServiceResponse response = new WebServiceResponse();
		WSetUpdateStatus img = new WSetUpdateStatus("Ok", isupdate, modificadas, nuevas, cont-1);
		response.setRespuesta(img);
		return response;
	}*/
}

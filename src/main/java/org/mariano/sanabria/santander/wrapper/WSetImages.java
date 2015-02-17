package org.mariano.sanabria.santander.wrapper;


import java.util.List;



public class WSetImages extends WSimpleResult{
	
	private List<String> urlimgs;
	private String cantidad;
	public WSetImages() {
		super("Fail");
	}
	public WSetImages(String result, List<String> urlimgsLista, int cont) {
		super(result);
		if(getStatus().getIdError().equals("200")){
			setUrlimgs(urlimgsLista);
			setCantidad(""+cont);
		
		}
	}
	public List<String> getUrlimgs() {
		return urlimgs;
	}
	public void setUrlimgs(List<String> urlimgs) {
		this.urlimgs = urlimgs;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

}

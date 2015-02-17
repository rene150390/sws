package org.mariano.sanabria.santander.wrapper;

public class WResponse {
	
	
	
	//String contants for Error
	public final static String ERROR = "Error";
	public final static String ID_INTERNAL_ERROR = "400";
	public final static String MESSAGE_INTERNAL_ERROR = "Error interno del servidor";
	
	public final static String OK = "Ok";
	public final static String ID_OK = "200";
	public final static String MESSAGE_OK = "Imágenes.";
	

	

	public WResponse() {
		
	}
	
	String[] splitBySpaces(String cadena){
		return cadena.split("\\|");
	}
}

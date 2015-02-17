package org.mariano.sanabria.santander.wrapper;

public class WStatus {
	private String status="";
	private String idError="";
	private String descripcion="";
	
	static private final int IDERRORSIZE= 3;	
	static private final String LARGEERROR="100";	

	public WStatus(String status, String idError, String descripcion){
		setStatus(status != null ? status.trim() : "");
		setIdError(idError != null ? idError.trim() : "");
		setDescripcion(descripcion != null ? descripcion.trim() : "");
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIdError() {
		return idError;
	}
	
	public void setIdError(String idError) {
		if(idError.length()>IDERRORSIZE){
			this.idError = LARGEERROR;
		}
		else{
			this.idError = idError;
		}
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

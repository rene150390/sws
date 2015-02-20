package org.mariano.sanabria.santander.wrapper;

import java.util.List;

public class WSetUpdateStatus extends WSimpleResult{
	
	private String isupdate;
	private List<String> modificadas;
	private List<String> nuevas;
	
	public WSetUpdateStatus(){
		super("Fail");
	}
	public WSetUpdateStatus(String result, String isupdate, List<String> modificadas, List<String> nuevas){
		super(result);
		setIsupdate(isupdate);
		if(!modificadas.isEmpty())
			setModificadas(modificadas);
		if(!nuevas.isEmpty())
			setNuevas(nuevas);
	}
	public String getIsupdate() {
		return isupdate;
	}
	public void setIsupdate(String isupdate) {
		this.isupdate = isupdate;
	}
	public List<String> getModificadas() {
		return modificadas;
	}
	public void setModificadas(List<String> modificadas) {
		this.modificadas = modificadas;
	}
	public List<String> getNuevas() {
		return nuevas;
	}
	public void setNuevas(List<String> nuevas) {
		this.nuevas = nuevas;
	}
}

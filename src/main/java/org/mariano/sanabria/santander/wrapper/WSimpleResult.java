package org.mariano.sanabria.santander.wrapper;


public class WSimpleResult extends WSpaces{
	
	private WStatus status;

	
	public WSimpleResult(String statuscadena){
		super(statuscadena);
		createStatus();
	}

	public WStatus getStatus() {
		return status;
	}

	public void setStatus(WStatus status) {
		this.status = status;
	}

	private void createStatus(){
		if(separated != null && !separated.isEmpty()){			
			if(separated.get(0).equals("Error")||separated.get(0).equals("error")){
				String idError;
				if(separated.size() > 2) {
					idError = separated.get(1).trim();
					setStatus(new WStatus(separated.get(0).trim(),idError,separated.get(2).trim()));
				} else {
					
				}				
			}
			else
				if(separated.get(0).equals("Ok")||separated.get(0).equals("ok")||separated.get(0).equals("OK") ){
					setStatus(new WStatus(OK, ID_OK, MESSAGE_OK));
				}
				else{
					
				}
				if(separated.get(0).equals("PagoTarjetaOK")){
				
				}
				else{
					if(separated.get(0).equals("PagoPaypalOK")){
					
					}
					/*else{
						setStatus(new WStatus(ERROR,ID_INTERNAL_ERROR,MESSAGE_INTERNAL_ERROR));
					}*/
				}
				
				
			
		}
		else{
			setStatus(new WStatus(ERROR,ID_INTERNAL_ERROR,MESSAGE_INTERNAL_ERROR));
		}
		
	}

}

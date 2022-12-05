package banca.conti;

import Exception.NoPrelievoException;
import banca.Persona;

public class ContoDeposito extends ContoCorrente{
	
	
	public ContoDeposito(Persona p, String iban) {
		super(p, iban);
	}

	
	
	public void operazione(double value) throws Exception{
		if(value>=0) {
			deposita(value); 
			}
		if(value<0) {		
			throw new NoPrelievoException("Prelievo non autorizzato.");
		}
	}
}


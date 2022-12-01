package banca.conti;

import Exception.NoPrelievoException;
import banca.Persona;

public class ContoDeposito extends ContoCorrente{
	
	
	public ContoDeposito(Persona p, String iban) {
		super(p, iban);
	}

	public void preleva(double value) throws NoPrelievoException{
		throw new NoPrelievoException();
		
	}
}

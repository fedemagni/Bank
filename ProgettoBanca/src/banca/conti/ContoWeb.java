package banca.conti;

import Exception.NoPrelievoException;
import banca.Persona;

public class ContoWeb extends ContoCorrente{
	private String password;
	private boolean attivo;
	
	public ContoWeb(Persona p, String iban) {
		super(p, iban);
	}

	
}

package banca.conti;

import Exception.ContoInattivoException;
import Exception.SaldoInsufficienteException;
import banca.Persona;

public class ContoWeb extends ContoCorrente{
	private String password;
	private boolean attivo;
	private boolean logged;
	
	public ContoWeb(Persona p, String iban) {
		
		super(p, iban);
		this.password="changeme";
		this.attivo=false;	
	}	
	
	public void preleva (double value) throws Exception{
		if(this.attivo==false) {
			throw new ContoInattivoException();
		}else if(this.logged==false){
			//doLogIn();	                            //FARE    <============
		}else if(saldo-value>=0) {
			saldo=saldo-value;	
		}else {
			throw new SaldoInsufficienteException();	
		}

	}

}

package banca.conti;

import Exception.ImpostaPasswordException;
import Exception.LogInException;
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
		this.logged=false;
	}
	public void primoAccesso() throws ImpostaPasswordException{
		try{
			if(attivo==false) {
				throw new ImpostaPasswordException();
			}
		}catch (ImpostaPasswordException a) {
			String pass=a.impostaPassword();
			setPassword(pass);
			setLogged(true);   //login automatico
		}
	}
	
	public void logIn() throws LogInException{
		try{
			if(logged==false) {
				throw new LogInException();
			}
		}catch (LogInException a) {
			
			setLogged(true);
		}
	}
	
	public void operazione(double value) throws Exception{
		if(this.attivo==false) {
			primoAccesso();
		}else if(this.logged==false){
			logIn();	                            //FARE    <============
		}else if(value>=0) {
				deposita(value); 
			}
		else if(value<0) {					
			preleva(Math.abs(value));
		}
	}
	
	public void preleva (double value) throws Exception{
		if(saldo-value>=0) {
			saldo=saldo-value;	
		}else {
			throw new SaldoInsufficienteException();	
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	
}

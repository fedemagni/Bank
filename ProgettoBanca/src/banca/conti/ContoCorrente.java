package banca.conti;

import java.util.ArrayList;

import Exception.LogInException;
import Exception.SaldoInsufficienteException;
import banca.Persona;
import banca.accountable.Accountable;
import banca.accountable.Stipendio;

public class ContoCorrente {
	private Persona p;
	private String iban;
	protected double saldo;
	private boolean logged;
	private ArrayList<Accountable> accountable; 
	
	public void addAccountable(Accountable r) {
		accountable.add(r);
	}
	
	public void fineMese() throws Exception {
		
		for(Accountable acc:this.accountable) {
			operazione(acc.getValue());
		}
	}
	
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	public ContoCorrente(Persona p, String iban) {
		
		this.p = p;
		this.iban = iban;
		saldo = 0;
		this.accountable= new ArrayList<Accountable>();
		
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
	 
	public void operazione(double value) throws SaldoInsufficienteException,Exception{
		if(value>=0) {
			deposita(value); 
			}
		if(value<0) {					
			preleva(Math.abs(value));
		}
	}
	public void preleva (double value) throws SaldoInsufficienteException{
		if(saldo-value>=0) {
			saldo=saldo-value;	
		}else {
			throw new SaldoInsufficienteException();	
		}
	}
	public void deposita(double value) throws Exception{ //!!!!!!!!!!!
		if(value>=0) {
			saldo=saldo+value;	
		} else 
		throw new Exception();	//!!!!!!!!!!!!!!!!!!!!!!!!
	}
	public double getSaldo() {
		return saldo;
	}
	

	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}
	public Persona getPersona() {
		return p;
	}
	public String toString() {
		return "Nome: "+p.getNome()+", Cognome: "+p.getCognome()+", CF: "+p.getCf()+", IBAN: "+iban+", Saldo: "+saldo;
	}
}

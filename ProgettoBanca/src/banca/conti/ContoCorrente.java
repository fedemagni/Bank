package banca.conti;

import Exception.SaldoInsufficienteException;
import banca.Persona;

public class ContoCorrente {
	private Persona p;
	private String iban;
	private double saldo;
	
	public ContoCorrente(Persona p, String iban) {
		
		this.p = p;
		this.iban = iban;
		saldo = 0;
		
	} 
	 
	
	public void preleva (double value) throws Exception{//!!!!!!!!!!!!!!!
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

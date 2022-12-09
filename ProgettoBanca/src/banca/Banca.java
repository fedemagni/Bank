package banca;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Exception.ContoInattivoException;
import Exception.InvalidIbanException;
import Exception.LogInException;
import Exception.SaldoInsufficienteException;
import banca.conti.ContoCorrente;
import banca.conti.ContoDeposito;
import banca.conti.ContoWeb;
import banca.conti.TypeConto;

public class Banca {
	private String nome;
	private int numeroMaxConti;
	private Map<String,ContoCorrente> elenco;
	private String radiceIban;
	private int contiAttivi;

	public Banca(String nome,String radice,int nmax) {
		
		this.nome=nome;
		radiceIban=radice;
		numeroMaxConti=nmax;
		contiAttivi=0;
		elenco=new HashMap<String,ContoCorrente>();
	}
	public String genIban() {
		int ca=contiAttivi+1;
		int l= Integer.toString(ca).length();
		int r= Integer.toString(numeroMaxConti).length();
		String s ="";
		for(int i=0;i<r-l;i++) {
			s=s+0;
		}
		return (radiceIban+s+ca);
	}
	private String nuovoConto(Persona p,TypeConto r) {
		String iban=this.genIban();
		ContoCorrente cc;		
		switch(r) {
			case CONTOWEB:
				cc=new ContoWeb(p,iban);
				break;
				
			case CONTODEPOSITO:
				cc=new ContoDeposito(p,iban);
				break;
				
			default:
				cc=new ContoCorrente(p,iban);
		}
		elenco.put(iban, cc);
		this.contiAttivi=contiAttivi+1;
		return iban;
		
	}
	public void logIn(ContoCorrente c) throws LogInException {
		c.logIn();
	}
	
	public void movimentoConto(ContoCorrente c,double value)throws Exception{
		if(value!=0) {
			c.operazione(value);	
		}else
			throw new Exception();
	}
	
//dato iban trovare conto
	public ContoCorrente getConto(String iban) throws InvalidIbanException{
		ContoCorrente c=elenco.get(iban);
		if(c==null) {
			throw new InvalidIbanException();
		}else	
		return c;
	}
	public void fineMese() throws SaldoInsufficienteException,Exception {
		for(String iban:elenco.keySet()) {
			elenco.get(iban).fineMese();
			
		}
	}

	public boolean bonifico(Banca a,String iban1, String iban2, double value)  throws Exception{
		if(a.getConto(iban2)==null || this.getConto(iban1)==null) {    //CHIEDERE SPIEGAZIONE!!!!!!!!!!!!!!!!!!!!!!!!!!! ==null
			return false;
		}	
		if(value<=0)
			return false;
		if(value>0) {

			double x=this.getConto(iban1).getSaldo();
			this.movimentoConto(this.getConto(iban1), -value);//.getConto(iban1).preleva(value);
			double y=this.getConto(iban1).getSaldo();
			if(x!=y) {
				a.movimentoConto(a.getConto(iban2), value); //getConto(iban2).deposita(value);
			}else {
				return false;
			}
			
		}
		return true;
	}
//	public double totaleSaldi2() {
//
//		double saldoBanca=0;
//		Iterator it= elenco.entrySet().iterator();
//		while(it.hasNext()) {
//			Map.Entry<String,ContoCorrente> entry = (Map.Entry)it.next();
//			
//			ContoCorrente c = entry.getValue();
//			saldoBanca=saldoBanca+c.getSaldo();
//		}
//		return saldoBanca;
//	}
	public double totaleSaldi() {
		double totale=0;
		for(String iban:elenco.keySet()) {
			double saldo=elenco.get(iban).getSaldo();
			totale +=saldo;
		}
		return totale;
	}
	
 	public void stampaElencoConti() {
		
		Iterator it= elenco.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,ContoCorrente> entry = (Entry<String, ContoCorrente>)it.next();
			
			ContoCorrente c = entry.getValue();
			
			System.out.println(c.toString());
		}
	}
	
	public void LogInContoWeb(ContoWeb c) throws ContoInattivoException {
		if(c.isAttivo()==false) {
			throw new ContoInattivoException();
		}else if(c.isLogged()==false){
			
		}
	}
	public void LogOutContoWeb(ContoWeb c) {
		
	}

	public static void main(String[] args) throws Exception {
		
		Banca b=new Banca("BCC","IT00000000012",10000);
		Persona Nico=new Persona("Nico","Galli","nclgll1234567");
		String ibanni=b.nuovoConto(Nico,TypeConto.CONTOCORRENTE);
		Banca b2=new Banca("ISP","IT23456787654", 99999);
		Persona Fede=new Persona("Banca","Magni","mgnfrc23456wer");
		String ibanfe=b2.nuovoConto(Fede,TypeConto.CONTOCORRENTE);
		ContoCorrente c=b.getConto(ibanni);
		
		System.out.println(ibanfe +"\n"+ibanni);		
			
	}

}

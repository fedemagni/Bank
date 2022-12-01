package banca;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Exception.InvalidIbanException;
import banca.conti.ContoCorrente;

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
	public ContoCorrente nuovoConto(Persona p) {
		
		String iban=this.genIban();
		ContoCorrente c=new ContoCorrente(p,iban);
		elenco.put(iban, c);
		this.contiAttivi=contiAttivi+1;
		return c;
		
	}

	public void movimentoConto(ContoCorrente c,double value)throws Exception{
		
		if(value>=0) {
			c.deposita(Math.abs(value)); //(Perchè abs? Perchè no.) Da cancellare.
			}
		if(value<0) {					 //uguale a zero non ha senso può creare problemi
			c.preleva(Math.abs(value));
		}	
	}
	
//dato iban trovare conto
	public ContoCorrente getConto(String iban) throws InvalidIbanException{
		ContoCorrente c=elenco.get(iban);
		if(c==null) {
			throw new InvalidIbanException();
		}else	
		return c;
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
	public double totaleSaldi() {
		double saldoBanca=0;
		Iterator it= elenco.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,ContoCorrente> entry = (Map.Entry)it.next();
			
			ContoCorrente c = entry.getValue();
			saldoBanca=saldoBanca+c.getSaldo();
		}
		return saldoBanca;
	}
	public void stampaElencoConti() {
		
		Iterator it= elenco.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,ContoCorrente> entry = (Entry<String, ContoCorrente>)it.next();
			
			ContoCorrente c = entry.getValue();
			
			System.out.println(c.toString());
		}
	}
	

	public static void main(String[] args) throws Exception {
		
		Banca b=new Banca("BCC","IT00000000012",10000);
		Persona Nico=new Persona("Nico","Galli","nclgll1234567");
		ContoCorrente ni=b.nuovoConto(Nico);
		Banca b2=new Banca("ISP","IT23456787654", 99999);
		Persona Fede=new Persona("Banca","Magni","mgnfrc23456wer");
		ContoCorrente fe=b2.nuovoConto(Fede);
		
		Persona[] elencoPersona=new Persona[11];
		ContoCorrente[] elencoContiGen=new ContoCorrente[11];
		for(int i=0;i<10;i++) {
			elencoPersona[i]=new Persona("Fede"+i,"Magni"+i,"mgnfrc23456wer"+i);
			elencoContiGen[i]=b2.nuovoConto(elencoPersona[i]);
		}
		
		String ibanni=ni.getIban();
		String ibanfe=fe.getIban();
		System.out.println(ibanni);
		System.out.println(ibanfe);
		System.out.println(b.numeroMaxConti);
		System.out.println(b2.getConto(ibanfe).getPersona().getCf());
		
		ni.deposita(1000);
		fe.deposita(10000);
		
		
		System.out.println(b2.totaleSaldi());
		b2.stampaElencoConti();		
			
	}

}

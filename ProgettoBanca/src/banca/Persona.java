package banca;

public class Persona {
	private String nome,cognome,cf;

	public Persona(String nome, String cognome, String cf) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
	}
	
	public String getCf() {
		return cf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	

}

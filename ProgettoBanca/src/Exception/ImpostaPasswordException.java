package Exception;

public class ImpostaPasswordException extends Exception{

	public ImpostaPasswordException() {
		super();	
	}
	
	public String impostaPassword() {
		String pass="password"; //interfaccia grafica
		return pass;
	}
}

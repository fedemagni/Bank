package banca.accountable;

public class Stipendio implements Accountable{
	private double value;
	
	

	public Stipendio(double v) {
		super();
		this.value = v;
		
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}

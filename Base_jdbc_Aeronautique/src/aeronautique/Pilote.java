package aeronautique;

public class Pilote {
	
	private int numPil;
	private String nomPil;
	private String adr;
	private float sal;
	
	public Pilote (String nomPil, String adr, float sal) {
		this.nomPil = nomPil;
		this.adr = adr;
		this.sal = sal;
	}
	
	

	public Pilote(int numPil, String nomPil, String adr, float sal) {
		super();
		this.numPil = numPil;
		this.nomPil = nomPil;
		this.adr = adr;
		this.sal = sal;
	}



	public int getNumPil() {
		return numPil;
	}



	public void setNumPil(int numPil) {
		this.numPil = numPil;
	}



	public String getNomPil() {
		return nomPil;
	}

	public void setNomPil(String nomPil) {
		this.nomPil = nomPil;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}



	@Override
	public String toString() {
		return "NumPil: "+this.getNumPil()+ " | NomPil: "+this.getNomPil()+" | Adr: "+this.getAdr()+" | sal: "+this.getSal();
	}
	
	

}

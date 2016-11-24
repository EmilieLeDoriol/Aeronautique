package aeronautique;

public class Avion {
	
	private int numAv;
	private String nomAv;
	private int capacite;
	private String loc;
	
	public Avion(String nomAv, int capacite, String location) {
		super();
		this.nomAv = nomAv;
		this.capacite = capacite;
		this.loc = location;
	}
	
	
	
	public Avion(int numAv, String nomAv, int capacite, String location) {
		super();
		this.numAv = numAv;
		this.nomAv = nomAv;
		this.capacite = capacite;
		this.loc = location;
	}



	public int getNumAv() {
		return numAv;
	}



	public void setNumAv(int numAv) {
		this.numAv = numAv;
	}



	public String getNomAv() {
		return nomAv;
	}
	public void setNomAv(String nomAv) {
		this.nomAv = nomAv;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getLocation() {
		return loc;
	}
	public void setLocation(String location) {
		this.loc = location;
	}
	
	

}

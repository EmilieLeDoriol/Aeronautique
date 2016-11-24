package aeronautique;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * étape 0 : les classes métier : le vol
 * on regarde les types de la table VOL
 * @author abi
 *
 */

public class Vol {
	
	private int numVol;
	private int numPil;
	private int numAv;
	private String villeDep;
	private String villeArr;
	private GregorianCalendar hDep = new GregorianCalendar();
	private GregorianCalendar hArr = new GregorianCalendar();

	/* !!! DATES : pour les bases de données, on passera par java.sql.Timestamp 
	 * Pour le find :
	 * GregorianCalendar hArr = new GregorianCalendar();
	 * hArr.setTimesInMillis (rs.getTimeStamp("harr").getTime());
	 * Pour le create :
	 * Timestamp ts = new Timestamp (vol.gethDep().getTimeInMillis());
	 * pst.setTimestamp(3,ts); 
	 */
	
	// Constructeur sur les champs.
	public Vol (int numPil, int numAv, String villeDep, String villeArr, GregorianCalendar hDep, GregorianCalendar hArr) {
		this.numPil = numPil;
		this.numAv = numAv;
		this.villeDep = villeDep;
		this.villeArr = villeArr;
		this.hDep = hDep;
		this.hArr = hArr;
	}
	
	
	
	public Vol(int numVol, int numPil, int numAv,  String villeDep, String villeArr, GregorianCalendar hDep,
			GregorianCalendar hArr) {
		super();
		this.numVol = numVol;
		this.numAv = numAv;
		this.numPil = numPil;
		this.villeDep = villeDep;
		this.villeArr = villeArr;
		this.hDep = hDep;
		this.hArr = hArr;
	}



	public int getNumVol() {
		return numVol;
	}

	public void setNumVol(int numVol) {
		this.numVol = numVol;
	}

	public String getVilleDep() {
		return villeDep;
	}

	public void setVilleDep(String villeDep) {
		this.villeDep = villeDep;
	}

	public String getVilleArr() {
		return villeArr;
	}

	public void setVilleArr(String villeArr) {
		this.villeArr = villeArr;
	}

	public GregorianCalendar gethDep() {
		
		return hDep;
	}

	public void sethDep(GregorianCalendar hDep) {
		this.hDep = hDep;
	}

	public GregorianCalendar gethArr() {
		return hArr;
	}

	public void sethArr(GregorianCalendar hArr) {
		this.hArr = hArr;
	}

	public int getNumPil() {
		return numPil;
	}

	public void setNumPil(int numPil) {
		this.numPil = numPil;
	}

	public int getNumAv() {
		return numAv;
	}

	public void setNumAv(int numAv) {
		this.numAv = numAv;
	}

	/**
	 * éventuellement utiliser java.sql.TimeStamp et getTimeInMillis pour afficher les dates 
	 */
	@Override
	public String toString() {
		Timestamp hDep = new Timestamp (this.gethDep().getTimeInMillis());
		Timestamp hArr = new Timestamp (this.gethArr().getTimeInMillis());
		
		return "Numvol: "+this.getNumVol() +" | NumPil: "+this.getNumPil() +" | NumAv: "+this.getNumAv()+" | villeDep:"+this.getVilleDep()
				+" | villeArr:"+this.getVilleArr()+" | hDep:"+hDep+" | hArr:"+hArr ;
	}
	

}

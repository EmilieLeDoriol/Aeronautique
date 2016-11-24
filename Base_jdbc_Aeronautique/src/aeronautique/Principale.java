package aeronautique;

import java.util.GregorianCalendar;

import controleur.Controleur;
import dao.Connexion;
import dao.VolDAO;


/**
 * Il faut commencer par faire le métier, puis regarder la classe Connexion,
 * puis le Design Pattern DAO sur la table VOL
 * Ensuite on étend aux autres tables AVION et PILOTE
 * Puis il faut essayer des requêtes plus complexes.
 * @author abi
 *
 */
public class Principale {

	public static void main(String[] args) {
				
		new Controleur();	
		//Connexion.afficheSelectEtoile("pilote", "WHERE sal > 10000");
		//Connexion.afficheSelectEtoile("vol", "");
		/*GregorianCalendar hDep = new GregorianCalendar(2016,8,22,8,11,21);
		GregorianCalendar hArr = new GregorianCalendar(2016,8,22,9,32,54);
		Vol obj = new Vol(9,3,3,"Rennes","Paris",hDep, hArr);
		VolDAO vol = new VolDAO();
		Vol obj = vol.find(6);
		System.out.println(obj);*/
		Connexion.fermer();
	}

}

package controleur;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import aeronautique.Avion;
import aeronautique.Pilote;
import aeronautique.Vol;
import dao.AvionDAO;
import dao.Connexion;
import dao.PiloteDAO;
import dao.VolDAO;
import ihm.Menu;

public class Controleur {

	// TODO Vous pouvez compléter ces enchainements de menu avec les deux autres tables.
	// Liste des constantes privées permettant d'enchainer les menus.
	// Menu principal
	// VOL
	private static final int MENU_AJOUT_VOL=0;
	private static final int MENU_SUPPR_VOL=3;
	private static final int MENU_PRINCIPAL=6;
	private static final int MENU_AJOUT_VRAIMENT_VOL = 7;
	private static final int MENU_AFFICHER_VOL = 8;
	private static final int MENU_SUPPR_VRAIMENT_VOL = 13;
	// PILOTE
	private static final int MENU_AJOUT_PILOTE=1;
	private static final int MENU_SUPPR_PILOTE=2;
	private static final int MENU_AJOUT_VRAIMENT_PILOTE = 4;
	private static final int MENU_AFFICHER_PILOTE = 5;
	private static final int MENU_SUPPR_VRAIMENT_PILOTE = 9;
	// AVION
	private static final int MENU_AJOUT_AVION=10;
	private static final int MENU_SUPPR_AVION=11;
	private static final int MENU_AJOUT_VRAIMENT_AVION = 12;
	private static final int MENU_AFFICHER_AVION=14;
	private static final int MENU_SUPPR_VRAIMENT_AVION=15;

	public Controleur() {
		this.sgbdJava();
	}

	private int getChoix(int menuAAfficher){
		int rep;
		switch (menuAAfficher) {
		case MENU_PRINCIPAL:
			rep = gererMenuPpl(); 			
			break;
		case MENU_AJOUT_VOL:
			rep = gererAjoutVol(); 			
			break;
		case MENU_SUPPR_VOL:
			rep = gererSupprVol(); 			
			break;
		case MENU_AJOUT_PILOTE:
			rep = gererAjoutPilote();
			break;
		case MENU_SUPPR_PILOTE:
			rep = gererSupprPilote();
			break;
		case MENU_AJOUT_AVION:
			rep = gererAjoutAvion();
			break;
		case MENU_SUPPR_AVION:
			rep = gererSupprAvion();
			break;
		default:
			rep=-1;
			break;
		} 
		return rep;
	}


	

	/**
	 * Cette méthode gère les enchainements des menus.
	 * 
	 */
	public void sgbdJava(){
		boolean fini=false;
		int choix = this.getChoix(Controleur.MENU_PRINCIPAL);
		int menuPrecedent=choix;
		while (!fini) {
			switch (choix) {
			case -1 :
				fini = true;
				break;
				// Les cas de base ou on appelle simplement le menu demandé
			case Controleur.MENU_PRINCIPAL :
			case Controleur.MENU_AJOUT_VOL :
			case Controleur.MENU_SUPPR_VOL :
			case Controleur.MENU_AJOUT_PILOTE:
			case Controleur.MENU_SUPPR_PILOTE:
			case Controleur.MENU_AJOUT_AVION:
			case Controleur.MENU_SUPPR_AVION:
				menuPrecedent=choix;
				choix = this.getChoix(choix);
				break;

				// Les cas où on ne change rien, on ne fait que de l'affichage
			case Controleur.MENU_AFFICHER_VOL :
				Connexion.afficheSelectEtoile("Vol", null);
				choix = menuPrecedent;
				break;

				// Les cas où on demande des informations pour l'ajout
			case Controleur.MENU_AJOUT_VRAIMENT_VOL :
				this.effectuerAjoutVol();
				choix = menuPrecedent;
				break;
				
				// Les cas où on demande une clé pour suppression
			case Controleur.MENU_SUPPR_VRAIMENT_VOL :
				this.effectuerSupprVol();
				choix = menuPrecedent;
				break;
				
			case Controleur.MENU_AFFICHER_PILOTE:
				Connexion.afficheSelectEtoile("Pilote", null);
				choix = menuPrecedent;
				break;
				
			case Controleur.MENU_AJOUT_VRAIMENT_PILOTE:
				this.effectuerAjoutPilote();
				choix = menuPrecedent;
				break;
				
			case Controleur.MENU_SUPPR_VRAIMENT_PILOTE:
				this.effectuerSupprPilote();
				choix = menuPrecedent;
				break;
				
			case Controleur.MENU_AFFICHER_AVION:
				Connexion.afficheSelectEtoile("Avion", null);
				choix = menuPrecedent;
				break;
			
			case Controleur.MENU_AJOUT_VRAIMENT_AVION:
				this.effectuerAjoutAvion();
				choix = menuPrecedent;
				break;
				
			case Controleur.MENU_SUPPR_VRAIMENT_AVION:
				this.effectuerSupprAvion();
				choix = menuPrecedent;
				break;
				
			default:
				// Code inaccessible selon nos vérifications
				Menu.afficheMsg(" ## Ré-essayez");
				break;
			}
		}
		Menu.afficheMsg("Au revoir");
	}


	private void effectuerSupprAvion() {
		Connexion.afficheSelectEtoile("Avion", null);
		Menu.afficheMsg("Quel numéro d'avion voulez-vous supprimer ?");
		int id = Menu.lireInt();
		AvionDAO dao = new AvionDAO();
		Avion obj = dao.find(id);
		dao.delete(obj);
	}

	private void effectuerAjoutAvion() {
		Menu.afficheMsg("Nom de l'avion:");
		String nomAv = Menu.lireString();
		Menu.afficheMsg("Capacité de l'avion:");
		int cap = Menu.lireInt();
		Menu.afficheMsg("Location de l'avion:");
		String loc = Menu.lireString();
		Avion obj = new Avion(nomAv,cap,loc);
		AvionDAO dao = new AvionDAO();
		dao.create(obj);
	}

	private void effectuerSupprPilote() {
		Connexion.afficheSelectEtoile("Pilote", null);
		Menu.afficheMsg("Quel numéro de pilote voulez-vous supprimer ?");
		int id = Menu.lireInt();
		PiloteDAO dao = new PiloteDAO();
		Pilote obj = dao.find(id);
		dao.delete(obj);
	}

	private void effectuerAjoutPilote() {
		Menu.afficheMsg("Nom du pilote:");
		String nomPil = Menu.lireString();
		Menu.afficheMsg("Adresse du pilote:");
		String adr = Menu.lireString();
		Menu.afficheMsg("Salaire du pilote:");
		int sal = Menu.lireInt();
		Pilote obj = new Pilote(nomPil,adr,sal);
		PiloteDAO dao = new PiloteDAO();
		dao.create(obj);
		
	}

	private void effectuerSupprVol() {
		Connexion.afficheSelectEtoile("Vol", null);
		Menu.afficheMsg("Quel numéro de vol voulez-vous supprimer ?");
		int id = Menu.lireInt();
		VolDAO dao = new VolDAO();
		Vol obj = dao.find(id);
		dao.delete(obj);
	}
	
	/**
	 * Méthode d'interaction pour saisir les données au clavier, création d'un Vol
	 * puis utilisation de create dans VolDAO pour écriture dans la table 
	 */
	private void effectuerAjoutVol() {
		Menu.afficheMsg("Numéro du pilote:");
		int numPil = Menu.lireInt();
		Menu.afficheMsg("Numéro de l'avion:");
		int numAv = Menu.lireInt();
		Menu.afficheMsg("Ville de départ:");
		String villeDep = Menu.lireString();
		Menu.afficheMsg("Ville d'arrivée:");
		String villeArr = Menu.lireString();
		GregorianCalendar hDep = this.lireDate("départ");
		GregorianCalendar hArr = this.lireDate("arrivée");
		Vol obj = new Vol(numPil,numAv,villeDep,villeArr,hDep,hArr);
		VolDAO dao = new VolDAO();
		dao.create(obj);
	}

	/** Cette méthode permet de lire une date
	 * 
	 * @param msg, un petit message permettant de savoir quelle date on entre
	 * @return une date au format Gregorian Calendar
	 */
	private GregorianCalendar lireDate(String msg){
		Menu.afficheMsg("date heure "+msg);		
		String dateActuelle=""; 
		Menu.afficheMsg("quelle année "+msg+" : "+dateActuelle);
		int annee = Menu.lireInt();
		dateActuelle+=annee+"/";
		Menu.afficheMsg("quelle mois "+msg+" : "+dateActuelle);
		int mois = Menu.lireInt();
		dateActuelle+=mois+"/";
		Menu.afficheMsg("quelle jour "+msg+" : "+dateActuelle);
		int jour = Menu.lireInt();
		dateActuelle+=jour+" ";
		Menu.afficheMsg("quelle heure "+msg+" : "+dateActuelle);
		int heure = Menu.lireInt();
		dateActuelle+=heure+":";
		Menu.afficheMsg("quelle minute "+msg+" : "+dateActuelle);
		int minute = Menu.lireInt();
		dateActuelle+=minute+":";
		Menu.afficheMsg("quelle seconde "+msg+" : "+dateActuelle);
		int seconde = Menu.lireInt();
		dateActuelle+=seconde;
		Menu.afficheMsg("date heure "+msg+" "+dateActuelle);		
		return new GregorianCalendar(annee, mois-1, jour, heure, minute, seconde);
	}


	private int gererSupprVol() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Supprimer un Vol");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Vol");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_SUPPR_VRAIMENT_VOL;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_VOL;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	
	private int gererAjoutVol() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajouter un Vol");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Vol");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VRAIMENT_VOL;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_VOL;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}
	
	private int gererSupprPilote() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Supprimer un pilote");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Pilote");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_SUPPR_VRAIMENT_PILOTE;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_PILOTE;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererAjoutPilote() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajouter un pilote");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Pilote");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VRAIMENT_PILOTE;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_PILOTE;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}
	
	private int gererSupprAvion() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Supprimer un Avion");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Avion");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_SUPPR_VRAIMENT_AVION;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_AVION;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererAjoutAvion() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajouter un Avion");
		leMenu.add("Menu Principal");
		leMenu.add("Voir la table Avion");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VRAIMENT_AVION;
			break;
		case 1:
			rep=MENU_PRINCIPAL;
			break;
		case 2:
			rep=MENU_AFFICHER_AVION;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}

	private int gererMenuPpl() {
		int rep;
		ArrayList<String> leMenu = new ArrayList<String>(4);
		leMenu.add("Ajout Vol");
		leMenu.add("Supprimer Vol");
		leMenu.add("Ajout Pilote");
		leMenu.add("Supprimer Pilote");
		leMenu.add("Ajout Avion");
		leMenu.add("Supprimer Avion");
		switch (Menu.getChoix(leMenu)) {
		case 0:
			rep=MENU_AJOUT_VOL;
			break;
		case 1:
			rep=MENU_SUPPR_VOL;
			break;
		case 2:
			rep=MENU_AJOUT_PILOTE;
			break;
		case 3:
			rep=MENU_SUPPR_PILOTE;
			break;
		case 4:
			rep=MENU_AJOUT_AVION;
			break;
		case 5:
			rep=MENU_SUPPR_AVION;
			break;
		default:
			rep=-1;
			break;
		}
		return rep;
	}
	
	

}

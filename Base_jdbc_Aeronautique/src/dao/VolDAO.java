package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import aeronautique.Vol;

/**
 * étape 2 : le patron de conception DAO, le lien vers notre table PILOTE
 * On étend la classe DAO avec un Pilote
 * @author abi
 *
 */
public class VolDAO extends DAO<Vol> {

	// 2 constantes de classe : le nom de la table, la clé primaire
	public static final String TABLE = new String("Vol");
	public static final String CLE_PRIMAIRE = new String("numVol");

	/**
	 * On donne un vol en entrée qu'on va écrire dans la base de données
	 * La requête à utiliser est un INSERT INTO
	 * On utilise encore TimeStamp
	 */
	@Override
	public boolean create(Vol obj) {

		boolean succes=true;
		try {
			String insertTableSQL = "INSERT INTO "+TABLE+" (numPil, numAv, villeDep, villeArr, hDep, hArr)"
					+ "VALUES (?,?,?,?,?,?)";
			
			
			PreparedStatement pst = Connexion.getInstance().prepareStatement(insertTableSQL);
			Timestamp ts = new Timestamp (obj.gethDep().getTimeInMillis());
			pst.setInt(1, obj.getNumPil());
			pst.setInt(2, obj.getNumAv());
			pst.setString(3, obj.getVilleDep());
			pst.setString(4, obj.getVilleArr());
			pst.setTimestamp(5,ts);
			ts = new Timestamp (obj.gethArr().getTimeInMillis());
			pst.setTimestamp(6, ts);
			pst.executeUpdate();
			obj.setNumVol(Connexion.getMaxId(CLE_PRIMAIRE, TABLE));
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	/**
	 * On donne un vol en entrée qu'on va supprimer de la base de données
	 * La requête à utiliser est un DELETE FROM
	 * 
	 */
	@Override
	public boolean delete(Vol obj) {
		boolean succes=true;
		int id = obj.getNumVol();
		try {
			String deleteTableSQL = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"=?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(deleteTableSQL);
			pst.setInt(1,id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}

	/**
	 * On donne un vol en entrée qu'on va mettre à jour dans la base de données
	 * La requête à utiliser est un UPDATE SET
	 * 
	 */
	@Override
	public boolean update(Vol obj) {
		boolean succes=true;
		int id = obj.getNumVol();
		try {
			String updateTableSQL = "UPDATE "+TABLE+" SET numPil=?, numAv=?, villeDep=?, villeArr=?, hDep=?, hArr=? WHERE numVol=?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(updateTableSQL);
			pst.setInt(1,obj.getNumPil());
			pst.setInt(2, obj.getNumAv());
			pst.setString(3, obj.getVilleDep());
			pst.setString(4, obj.getVilleArr());
			Timestamp ts = new Timestamp (obj.gethDep().getTimeInMillis());
			pst.setTimestamp(5,ts);
			ts = new Timestamp (obj.gethArr().getTimeInMillis());
			pst.setTimestamp(6, ts);
			pst.setInt(7, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	/**
	 * On donne un identifiant entier en entrée qu'on cherche dans la base de données
	 * La requête à utiliser est un SELECT FROM WHERE avec la clé primaire
	 * 
	 */
	@Override
	public Vol find(int id) {
		Vol vol = null;
		try {
			String selectTableSQL = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
			ResultSet rs = Connexion.executeQuery(selectTableSQL);
			if (rs.next()) {
				GregorianCalendar hDep = new GregorianCalendar();
				hDep.setTimeInMillis(rs.getTimestamp("hDep").getTime());
				GregorianCalendar hArr = new GregorianCalendar();
				hArr.setTimeInMillis(rs.getTimestamp("harr").getTime());
				vol = new Vol(rs.getInt("numVol"),rs.getInt("numPil"),rs.getInt("numAv"),rs.getString("villeDep"),rs.getString("villeArr"),hDep,hArr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vol;
	}



}

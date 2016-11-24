package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeronautique.Avion;
import aeronautique.Pilote;

public class AvionDAO extends DAO<Avion>{
	
	// 2 constantes de classe : le nom de la table, la clé primaire
		public static final String TABLE = new String("Avion");
		public static final String CLE_PRIMAIRE = new String("numAv");

	@Override
	public boolean create(Avion obj) {
		boolean succes=true;
		try {
			String insertTableSQL = "INSERT INTO "+TABLE+" (nomAv, capacite, loc)"
					+ "VALUES (?,?,?)";
			
			PreparedStatement pst = Connexion.getInstance().prepareStatement(insertTableSQL);
			pst.setString(1, obj.getNomAv());
			pst.setInt(2, obj.getCapacite());
			pst.setString(3, obj.getLocation());
			pst.executeUpdate();
			obj.setNumAv(Connexion.getMaxId(CLE_PRIMAIRE, TABLE));
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Avion obj) {
		boolean succes=true;
		int id = obj.getNumAv();
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

	@Override
	public boolean update(Avion obj) {
		boolean succes=true;
		int id = obj.getNumAv();
		try {
			String updateTableSQL = "UPDATE "+TABLE+" SET nomAv=?, capacite=?, loc=? WHERE "+CLE_PRIMAIRE+"=?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(updateTableSQL);
			pst.setString(1,obj.getNomAv());
			pst.setInt(2, obj.getCapacite());
			pst.setString(3, obj.getLocation());
			pst.setInt(4, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Avion find(int id) {
		Avion av = null;
		try {
			String selectTableSQL = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
			ResultSet rs = Connexion.executeQuery(selectTableSQL);
			if (rs.next()) {
				av = new Avion(rs.getInt("numAv"),rs.getString("nomAv"),rs.getInt("capacite"),rs.getString("loc"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return av;
	}

}

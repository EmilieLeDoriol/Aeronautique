package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeronautique.Pilote;

public class PiloteDAO extends DAO<Pilote> {
	
	// 2 constantes de classe : le nom de la table, la clé primaire
	public static final String TABLE = new String("Pilote");
	public static final String CLE_PRIMAIRE = new String("numPil");

	@Override
	public boolean create(Pilote obj) {
		boolean succes=true;
		try {
			String insertTableSQL = "INSERT INTO "+TABLE+" (nomPil, adr, sal)"
					+ "VALUES (?,?,?)";
			
			PreparedStatement pst = Connexion.getInstance().prepareStatement(insertTableSQL);
			pst.setString(1, obj.getNomPil());
			pst.setString(2, obj.getAdr());
			pst.setFloat(3, obj.getSal());
			pst.executeUpdate();
			obj.setNumPil(Connexion.getMaxId(CLE_PRIMAIRE, TABLE));
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Pilote obj) {
		boolean succes=true;
		int id = obj.getNumPil();
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
	public boolean update(Pilote obj) {
		boolean succes=true;
		int id = obj.getNumPil();
		try {
			String updateTableSQL = "UPDATE "+TABLE+" SET nomPil=?, adr=?, sal=? WHERE "+CLE_PRIMAIRE+"=?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(updateTableSQL);
			pst.setString(1,obj.getNomPil());
			pst.setString(2, obj.getAdr());
			pst.setFloat(3, obj.getSal());
			pst.setInt(4, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Pilote find(int id) {
		Pilote pilote = null;
		try {
			String selectTableSQL = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
			ResultSet rs = Connexion.executeQuery(selectTableSQL);
			if (rs.next()) {
				pilote = new Pilote(rs.getInt("numPil"),rs.getString("nomPil"),rs.getString("adr"),rs.getFloat("sal"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pilote;
	}

}

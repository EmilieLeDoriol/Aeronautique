package test.dao;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.junit.Test;

import aeronautique.Vol;
import dao.Connexion;
import dao.VolDAO;

public class VolDAOTest {
	
	private static final String CLE_PRIMAIRE = "numVol";
	private static final String TABLE = "vol";

	@Test
	public void testCreate() {
		int max = Connexion.getMaxId(CLE_PRIMAIRE, TABLE);
		GregorianCalendar hDep = new GregorianCalendar(2016,8,22,12,43,21);
		GregorianCalendar hArr = new GregorianCalendar(2016,8,22,14,22,54);
		Vol obj = new Vol(1,1,"Rennes","Toulon",hDep, hArr);
		VolDAO vol = new VolDAO();
		
		/*String req = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+obj.getNumVol();
		ResultSet rs = Connexion.executeQuery(req);
		int id = -1;
		try {
			if (rs.next()) {
				 id = rs.getInt(CLE_PRIMAIRE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		assertEquals(vol.create(obj), true);
	}
	
	@Test
	public void testDelete() {
		GregorianCalendar hDep = new GregorianCalendar(2016,8,22,12,43,21);
		GregorianCalendar hArr = new GregorianCalendar(2016,8,22,14,22,54);
		Vol obj = new Vol(19,1,1,"Rennes","Toulon",hDep, hArr);
		VolDAO vol = new VolDAO();
		assertEquals(vol.delete(obj), true);
	}
	
	@Test
	public void testUpdate() {
		GregorianCalendar hDep = new GregorianCalendar(2016,8,22,12,43,21);
		GregorianCalendar hArr = new GregorianCalendar(2016,8,22,14,22,54);
		Vol obj = new Vol(18,2,1,"Nice","Toulon",hDep, hArr);
		VolDAO vol = new VolDAO();
		assertEquals(vol.update(obj),true);
	}
	
	@Test
	public void testFind() {
		VolDAO vol = new VolDAO();
		Vol obj = vol.find(6);
		System.out.println(obj);
		assertEquals(obj.getNumVol(),6);
	}
	
	

}

package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import aeronautique.Avion;
import dao.AvionDAO;

public class AvionDAOTest {

	@Test
	public void testCreate() {
		Avion av = new Avion("Airbus",600,"Nice");
		AvionDAO dao = new AvionDAO();
		assertEquals(dao.create(av),true);
	}

	@Test
	public void testDelete() {
		Avion av = new Avion(4,"Airbus",600,"Nice");
		AvionDAO dao = new AvionDAO();
		assertEquals(dao.delete(av),true);
	}
	
	
	@Test
	public void testUpdate() {
		Avion av = new Avion(3,"Airbus",600,"Nice");
		AvionDAO dao = new AvionDAO();
		assertEquals(dao.update(av),true);
	}
	
	
	@Test
	public void testFind() {
		AvionDAO dao = new AvionDAO();
		Avion av = dao.find(3);
		assertEquals(av.getNumAv(),3);
	}
}

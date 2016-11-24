package test.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import aeronautique.Pilote;
import dao.PiloteDAO;

public class PiloteDAOTest {

	@Test
	public void testCreate() {
		Pilote pil = new Pilote("DOUSKI","Nice",10000);
		PiloteDAO dao = new PiloteDAO();
		assertEquals(dao.create(pil),true);
	}
	
	@Test
	public void testDelete() {
		Pilote pil = new Pilote(7,"CHAMORET","Vannes",8000);
		PiloteDAO dao = new PiloteDAO();
		assertEquals(dao.delete(pil),true);
	}
	
	@Test
	public void testUpdate() {
		Pilote pil = new Pilote(5,"CHAMORET","Vannes",9000);
		PiloteDAO dao = new PiloteDAO();
		assertEquals(dao.update(pil),true);
	}

	@Test
	public void testFind() {
		PiloteDAO dao = new PiloteDAO();
		Pilote pil = dao.find(5);
		assertEquals(pil.getNumPil(),5);
	}
	
}

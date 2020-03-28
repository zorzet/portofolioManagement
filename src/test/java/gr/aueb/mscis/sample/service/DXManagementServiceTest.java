package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class DXManagementServiceTest {
	protected static EntityManager em;

	@BeforeClass
	public static void setup() {
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();
		em = JPAUtil.getCurrentEntityManager();
	}

	@AfterClass
	public static void tearDown() {
		em.close();
	}

	@Test
	public void test_findDXbyIdNull() {
		String exp_message = "NO DX FOUND";
		DXManagementService d = new DXManagementService(em);
		try {
			d.findDXById(400000);
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findDXbyId() {
		DX exp_result = em.createQuery("select dx from DX dx", DX.class).setMaxResults(1).getSingleResult();
		DXManagementService d = new DXManagementService(em);
		try {
			DX result = d.findDXById(exp_result.getDXId());
			assertEquals(exp_result, result);
		} catch (java.lang.RuntimeException message) {

		}
	}

	@Test
	public void test_findDXbyLastNull() {
		String exp_message = "NO DX FOUND";
		DXManagementService d = new DXManagementService(em);
		try {
			d.findDXsByLastName("askdj");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findDXbyLast() {
		List<DX> exp_result = em.createQuery("select dx from DX dx", DX.class).setMaxResults(1).getResultList();
		DXManagementService d = new DXManagementService(em);
		System.out.println(exp_result.get(0).getSurname());
		List<DX> result = d.findDXsByLastName(exp_result.get(0).getSurname());
		assertEquals(exp_result, result);
	}

	@Test
	public void test_findDXbyFirstNull() {
		String exp_message = "NO DX FOUND";
		DXManagementService d = new DXManagementService(em);
		try {
			d.findDXsByfirstname("askdj");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findDXbyFirst() {
		List<DX> exp_result = em.createQuery("select dx from DX dx", DX.class).setMaxResults(1).getResultList();
		DXManagementService d = new DXManagementService(em);
		try {
			List<DX> result = d.findDXsByfirstname(exp_result.get(0).getName());
			assertEquals(exp_result, result);
		} catch (java.lang.RuntimeException message) {

		}
	}

	@Test
	public void test_findAllDXs() {
		List<DX> exp_result = em.createQuery("select dx from DX dx", DX.class).getResultList();
		DXManagementService d = new DXManagementService(em);
		List<DX> result = d.findAllDXs();
		assertEquals(exp_result, result);
	}

	@Test
	public void test_updates() {
		DX dx = em.createQuery("select dx from DX dx", DX.class).setMaxResults(1).getSingleResult();
		DXManagementService d = new DXManagementService(em);
		assertTrue(d.saveOrUpdateDX(dx));
		assertFalse(d.saveOrUpdateDX(null));
		
		dx = new DX("AH252687", "12345670", "Eleni", "Papa", "6999999997", "epapa@gmail.com", "13-04-1987", "elpapa", "admin2");
		assertTrue(d.createDX(dx));
		assertFalse(d.createDX(null));
		assertTrue(d.RemoveDX(dx));
		assertFalse(d.RemoveDX(null));
	}
}

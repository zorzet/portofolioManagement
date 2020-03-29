package gr.aueb.mscis.sample.service;

import java.util.*;

import javax.persistence.*;
import org.junit.*;
import static org.junit.Assert.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.PelatologioManagement;

public class PelatologioManagementTest {
	protected static EntityManager em;
	
	@BeforeClass
	public static void setup(){
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();	
		em = JPAUtil.getCurrentEntityManager();
	}
	
	@AfterClass
	public static void tearDown(){
		em.close();
	}
	
	@Test
	public void test_findCustomersbyIdNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			Customer results = p.findCustomersById(40000);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyId() {
		Customer exp_result = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			Customer result = p.findCustomersById(exp_result.getCustomerId());
			assertEquals(exp_result, result);
		} catch(java.lang.RuntimeException message) {

		}
	}
	
	@Test
	public void test_findCustomersbyLastNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByLastName("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyLast() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByLastName(c.getSurname());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyFirstNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByFirstName("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyFirst() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByFirstName(c.getName());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyAFMNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByAFM("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyAFM() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByAFM(c.getAFM());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyADTNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByADT("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyADT() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByADT(c.getADT());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyTelNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByTel("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyTel() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByTel(c.getTel());
		assertEquals(exp_result, result);
	}
	
	
	@Test
	public void test_findCustomersbyEmailNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByEmail("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyEmail() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByEmail(c.getEmail());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyInvestAmountNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByInvestAmount(1000);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyInvestAmount() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByInvestAmount(c.getInvestAmount());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyBankAccountNoNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByBankAccountNo("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyBankAccountNo() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(c);
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByBankAccountNo(c.getBankAccountNo());
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findAllCustomer() {
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		List<Customer> exp_result = new ArrayList<>();
		for (Xartofulakio x : xlist ) {
			exp_result.add(x.getCus());
		}

		List<Customer> result = new ArrayList<>();
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findAllCustomer();
		assertEquals(exp_result,result);
	}
	
	@Test
	public void test_ShowCustomers() {
		String exp_results = "Id 1 FirstName  Maria LastName Papadopoulos ADT AS12345 AFM 12345678 EMAIL mp@gmail.com Telephone 2121212121BirthDate06-07-1980investAmoun12345.0bankAccountNoGE075 1234 1234 1234 1234\r\n" + 
				"Id 2 FirstName  Marios LastName Papas ADT AE12345 AFM 123456789 EMAIL msp@gmail.com Telephone 2121212120BirthDate16-07-1980investAmoun12000.0bankAccountNoGE075 5678 5678 5678 5678\r\n" +
				"Id 3 FirstName  Giannis LastName Pappas ADT AE12346 AFM 123456780 EMAIL gsp@gmail.com Telephone 2121212122BirthDate16-07-1982investAmoun12050.0bankAccountNoGE075 5678 5678 5678 5679\r\n";
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();    
		System.setOut(new java.io.PrintStream(out)); 
		PelatologioManagement p = new PelatologioManagement(em);
		p.ShowCustomers();
		
		String results = out.toString();
		assertEquals(exp_results, results);
	}
	
	@Test
	public void test_updates() {
		PelatologioManagement p = new PelatologioManagement(em);
		Xartofulakio x = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult();
		Customer c = x.getCus();
		assertTrue(p.saveOrUpdateCustomer(x, c));
		assertFalse(p.saveOrUpdateCustomer(x, null));
	}
	
	@Test
	public void test_login() {
		DX dx = em.createQuery("select dx from DX dx", DX.class).setMaxResults(1).getSingleResult();
		LoginService l = new LoginService(em);
		assertEquals(0, l.LoginUser(dx.getUsername(), dx.getPassword()));
		assertEquals(-1, l.LoginUser("mpapa", "asdasd"));
	}
}

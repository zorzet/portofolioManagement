package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;
import org.junit.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class XartofulakioServiceTest {
	
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
	public void test_xartofulakiaByID() {
		List<Xartofulakio> exp_results = null;
		List<Xartofulakio> results = null;
		XartofulakioService xs = new XartofulakioService(em);
		results = xs.findXartofulakiaById(1);
		
        exp_results = results;
        exp_results.clear();
        exp_results.add(new Xartofulakio("created", new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234")));
        
		assertEquals(exp_results, results);
	}
	
	@Test
	public void test_showCustomers() {
		String exp_results = "CID 1 XID 1 NAME  Maria SURNAME Papadopoulos ADT AS12345 AFM 12345678 EMAIL mp@gmail.com INVESTED AMOUNT 12345.0\r\n";
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();    
		System.setOut(new java.io.PrintStream(out));    
		XartofulakioService xs = new XartofulakioService(em);
		xs.ShowCustomers(1);
		
		String results = out.toString();
		assertEquals(exp_results,results);		
	}
	
	@Test
	public void test_betaOfStocks() {
		Double exp_beta = 0.6;
		XartofulakioService xs = new XartofulakioService(em);
		Double beta = xs.betaOfStock("AEGN", "22/02/2020");
		assertEquals(exp_beta, beta, 0.0);
	}
	
	@Test
	public void test_priceOfStocks() {
		Double exp_price = 8.69;
		XartofulakioService xs = new XartofulakioService(em);
		Double price = xs.priceOfStock("AEGN", "22/02/2020");
		assertEquals(exp_price, price, 0.0);
	}
	
	@Test 
	public void test_ShowXartofulakioPelath() {
		String exp_results = "name Maria surname Papadopoulos ADT AS12345 AFM 12345678 Birthday 06/07/1980 email mp@gmail.com";
		XartofulakioService xs = new XartofulakioService(em);
		String results = xs.ShowXartofulakioPelath(1, 1);
		assertEquals(exp_results, results);
	}
	
	@Test 
	public void test_ShowXartofulakioPelathEmpty() {
		String exp_results = "Xartofulakio for this customer could not be retrieved";
		XartofulakioService xs = new XartofulakioService(em);
		try {
			xs.ShowXartofulakioPelath(1, 2);
		} catch(java.lang.RuntimeException e) {
			assertEquals(exp_results, e.getMessage());
		}
	}
	
	@Test
	public void test_createTransaction() {
		Transaction exp_t = new Transaction("Buy", "AEGN", 100, 30.6, "20/02/2020", "open");
		XartofulakioService xs = new XartofulakioService(em);
		Transaction t = xs.createTransaction("Buy", "AEGN", 100, 30.6, "20/02/2020");
		assertEquals(exp_t, t);
	}
}

package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;

import java.util.*;

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
	
	@Test
	public void test_calculateBeta() {
		double exp_beta = 0.75;
		XartofulakioService xs = new XartofulakioService(em);
		double beta = xs.CalculateBeta(1, 1, "23/02/2020");
		assertEquals(exp_beta, beta, 0.0);
		
		String exp_message = "Action not valid, no stocks in portofolio";
		try {
			xs.CalculateBeta(1, 1, "25/02/2020");
		} catch(java.lang.RuntimeException e) {
			assertEquals(exp_message, e.getMessage());
		}
	}
	
	@Test
	public void test_ShowTransactionsPerPortofolio() {
		Set<Transaction> exp_t = new HashSet<Transaction>();
		exp_t.add(new Transaction("Buy", "AEGN", 100, 30.6, "20/02/2020", "Open"));
		XartofulakioService xs = new XartofulakioService(em);
		Set<Transaction> t = xs.ShowTransactionsPerPortofolio(1, 1);
		assertEquals(exp_t, t);
	}
	
	@Test
	public void test_ShowTransactionsPerPortofolioNull() {
		String exp_msg = "Transactions for this customer could not be retrieved";
		XartofulakioService xs = new XartofulakioService(em);
		try {
			xs.ShowTransactionsPerPortofolio(2, 2);
		} catch(java.lang.RuntimeException e) {
			assertEquals(exp_msg, e.getMessage());
		}
	}
	
	@Test
	public void test_returnCustomerImage() {
		String exp_msg = "[Transaction [TransId=1, CmdType=Buy, Stock=AEGN, Units=100, price=30.6, date=20/02/2020, state=open]] "
				+ "name Maria surname Papadopoulos ADT AS12345 AFM 12345678 Birthday 06/07/1980 email mp@gmail.com AMUNTIKO XARTOFULAKIO";
		XartofulakioService xs = new XartofulakioService(em);
		String msg = xs.ReturnCustomerImage(1, 1, "23/02/2020");
		assertEquals(exp_msg, msg);

		exp_msg = "[Transaction [TransId=1, CmdType=Buy, Stock=AEGN, Units=100, price=30.6, date=20/02/2020, state=open]] "
				+ "name Maria surname Papadopoulos ADT AS12345 AFM 12345678 Birthday 06/07/1980 email mp@gmail.com EPI8ETIKO XARTOFULAKIO";
		msg = xs.ReturnCustomerImage(1, 1, "24/02/2020");
		assertEquals(exp_msg, msg);
		
		exp_msg = "[Transaction [TransId=1, CmdType=Buy, Stock=AEGN, Units=100, price=30.6, date=20/02/2020, state=open]] "
				+ "name Maria surname Papadopoulos ADT AS12345 AFM 12345678 Birthday 06/07/1980 email mp@gmail.com OUDETERO XARTOFULAKIO";
		msg = xs.ReturnCustomerImage(1, 1, "25/02/2020");
		assertEquals(exp_msg, msg);
	}
	
	@Test
	public void test_CheckForCustomersBalance() {
		XartofulakioService xs = new XartofulakioService(em);
		Customer c = new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234");
		assertTrue(xs.CheckForCustomersBalance(c, 30.00, 20.00));
		assertFalse(xs.CheckForCustomersBalance(c, 30.00, 600.00));
	}
	
	@Test
	public void test_CheckForOpenTransaction() {
		XartofulakioService xs = new XartofulakioService(em);
		Transaction exp_t = new Transaction("Buy", "AEGN", 100, 30.6, "20/02/2020", "open");
		Xartofulakio x = (Xartofulakio) em.createQuery("select x from Xartofulakio x where XId = 1").getSingleResult();
		Transaction t = xs.CheckForOpenTransaction("AEGN", x);
		assertEquals(exp_t, t);
		
		t = xs.CheckForOpenTransaction("OPAP", x);
		assertEquals(null, t);
	}
	
	@Test
	public void test_transact() {
		XartofulakioService xs = new XartofulakioService(em);
		String exp_msg = "Action not valid. Unavailable units in stock";
		Xartofulakio x = (Xartofulakio) em.createQuery("select x from Xartofulakio x where XId = 1").getSingleResult();
		
		try {
			xs.transact("sell", "OPAP", 100, 3.54, "23/02/2020", "open", x);
		} catch(java.lang.RuntimeException e) {
			assertEquals(null, e.getMessage());
		}
		
		Transaction exp_t = new Transaction("Buy", "AEGN", 80, 30.6, "25/02/2020", "open");
		Transaction t = xs.transact("sell", "AEGN", 20, 3.54, "25/02/2020", "open", x);
		assertEquals(exp_t, t);
		
		exp_t = new Transaction("Buy", "AEGN", 0, 30.6, "25/02/2020", "closed");
		t = xs.transact("sell", "AEGN", 80, 3.54, "25/02/2020", "open", x);
		assertEquals(exp_t, t);
		
		exp_t = new Transaction("Buy", "AEGN", 100, 30.6, "25/02/2020", "open");
		t = xs.transact("Buy", "AEGN", 100, 30.6, "25/02/2020", "open", x);
		assertEquals(exp_t, t);
	}
}


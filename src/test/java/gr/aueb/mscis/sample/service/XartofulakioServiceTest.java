package gr.aueb.mscis.sample.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class XartofulakioServiceTest {
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
	public void test_xartofulakiaByID() {
		List<Xartofulakio> exp_results = new ArrayList<Xartofulakio>();
		List<Xartofulakio> results = new ArrayList<Xartofulakio>();
		XartofulakioService xs = new XartofulakioService(em);
		exp_results = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getResultList();
		for (Xartofulakio x : exp_results) {
			results = xs.findXartofulakiaById(x.getDX().getDXId());
		}
		assertEquals(exp_results, results);
	}

	@Test
	public void test_showCustomers() {
		String exp_results = null;
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(out));
		XartofulakioService xs = new XartofulakioService(em);
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio x : xlist) {
			if (x.getCus().getCustomerId() == 1) {
				xs.ShowCustomers(x.getDX().getDXId());
				exp_results = "CID 1 XID " + x.getXid()
						+ " NAME  Maria SURNAME Papadopoulos ADT AS12345 AFM 12345678 EMAIL mp@gmail.com INVESTED AMOUNT 12345.0\r\n";
			}
		}

		String results = out.toString();
		assertEquals(exp_results, results);
	}

	@Test
	public void test_betaOfStocks() {
		Double exp_beta = 0.6;
		XartofulakioService xs = new XartofulakioService(em);
		Double beta = xs.betaOfStock("AEGN", "22-02-2020");
		assertEquals(exp_beta, beta, 0.0);
	}

	@Test
	public void test_priceOfStocks() {
		Double exp_price = 8.69;
		XartofulakioService xs = new XartofulakioService(em);
		Double price = xs.priceOfStock("AEGN", "22-02-2020");
		assertEquals(exp_price, price, 0.0);
	}

	@Test
	public void test_ShowXartofulakioPelath() {
		String exp_results = "name Maria surname Papadopoulos ADT AS12345 AFM 12345678 Birthday 06-07-1980 email mp@gmail.com";
		XartofulakioService xs = new XartofulakioService(em);
		String results = null;
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio x : xlist) {
			if (x.getCus().getCustomerId() == 1)
				results = xs.ShowXartofulakioPelath(x.getDX().getDXId(), 1);
		}
		assertEquals(exp_results, results);
	}

	@Test
	public void test_ShowXartofulakioPelathEmpty() {
		String exp_results = "Xartofulakio for this customer could not be retrieved";
		XartofulakioService xs = new XartofulakioService(em);
		try {
			xs.ShowXartofulakioPelath(1, 2);
		} catch (java.lang.RuntimeException e) {
			assertEquals(exp_results, e.getMessage());
		}
	}

	@Test
	public void test_createTransaction() {
		Transaction exp_t = new Transaction("Buy", "AEGN", 100, 30.6, "20-02-2020", "open");
		XartofulakioService xs = new XartofulakioService(em);
		Transaction t = xs.createTransaction("Buy", "AEGN", 100, 30.6, "20-02-2020");
		assertEquals(exp_t, t);
	}

	@Test
	public void test_calculateBeta() {
		double exp_beta = 1.71;
		XartofulakioService xs = new XartofulakioService(em);
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		int dxid = 0;
		String date = null;
		for (Xartofulakio xl : xlist) {
			if (xl.getCus().getCustomerId() == 2) {
				Set<Transaction> trans = xl.getTransactions();
				for (Transaction t : trans) {
					date = t.getDate();
					break;
				}
				dxid = xl.getDX().getDXId();
				break;
			}
		}

		System.out.println(date);

		double beta = xs.CalculateBeta(dxid, 2, date);
		assertEquals(exp_beta, beta, 0.1);

		String exp_message = "Action not valid, no stocks in portofolio";
		try {
			xs.CalculateBeta(dxid, 2, "30-02-2020");
		} catch (java.lang.RuntimeException e) {
			assertEquals(exp_message, e.getMessage());
		}
	}

	@Test
	public void test_ShowTransactionsPerPortofolio() {
		Set<Transaction> exp_t = new HashSet<Transaction>();
		Xartofulakio x = null;
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio xl : xlist) {
			if (xl.getCus().getCustomerId() == 2)
				x = xl;
		}
		exp_t = x.getTransactions();
		XartofulakioService xs = new XartofulakioService(em);
		Set<Transaction> t = xs.ShowTransactionsPerPortofolio(x.getDX().getDXId(), x.getCus().getCustomerId());
		assertEquals(exp_t, t);
	}

	@Test
	public void test_ShowTransactionsPerPortofolioNull() {
		String exp_msg = "Transactions for this customer could not be retrieved";
		XartofulakioService xs = new XartofulakioService(em);
		try {
			xs.ShowTransactionsPerPortofolio(3029, 2);
		} catch (java.lang.RuntimeException e) {
			assertEquals(exp_msg, e.getMessage());
		}
		try {
			xs.ShowTransactionsPerPortofolio(2, 3029);
		} catch (java.lang.RuntimeException e) {
			assertEquals(exp_msg, e.getMessage());
		}
	}

	@Test
	public void test_returnCustomerImage() {
		String exp_msg = "[Transaction [CmdType=Buy, Stock=OPAP, Units=100, price=11.4, date=20-02-2020, state=open]] "
				+ "name Marios surname Papas ADT AE12345 AFM 123456789 Birthday 16-07-1980 email msp@gmail.com EPI8ETIKO XARTOFULAKIO";
		XartofulakioService xs = new XartofulakioService(em);
		Xartofulakio x = null;
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio xl : xlist) {
			if (xl.getCus().getCustomerId() == 2)
				x = xl;
		}
		String msg = xs.ReturnCustomerImage(x.getDX().getDXId(), 2, "23-02-2020");
		assertEquals(exp_msg, msg);

		exp_msg = "[Transaction [CmdType=Buy, Stock=OPAP, Units=100, price=11.4, date=20-02-2020, state=open]] "
				+ "name Marios surname Papas ADT AE12345 AFM 123456789 Birthday 16-07-1980 email msp@gmail.com AMUNTIKO XARTOFULAKIO";
		msg = xs.ReturnCustomerImage(x.getDX().getDXId(), 2, "24-02-2020");
		assertEquals(exp_msg, msg);

		exp_msg = "[Transaction [CmdType=Buy, Stock=OPAP, Units=100, price=11.4, date=20-02-2020, state=open]] "
				+ "name Marios surname Papas ADT AE12345 AFM 123456789 Birthday 16-07-1980 email msp@gmail.com OUDETERO XARTOFULAKIO";
		msg = xs.ReturnCustomerImage(x.getDX().getDXId(), 2, "25-02-2020");
		assertEquals(exp_msg, msg);
	}

	@Test
	public void test_CheckForCustomersBalance() {
		XartofulakioService xs = new XartofulakioService(em);
		Customer c = new Customer(1, "AS12345", "12345678", "Maria", "Papadopoulos", "2121212121", "mp@gmail.com",
				"06/07/1980", 12345, "GE075 1234 1234 1234 1234");
		assertTrue(xs.CheckForCustomersBalance(c, 30.00, 20.00));
		assertFalse(xs.CheckForCustomersBalance(c, 30.00, 600.00));
	}

	@Test
	public void test_CheckForOpenTransaction() {
		XartofulakioService xs = new XartofulakioService(em);
		Xartofulakio x = null;
		Transaction exp_t = null;

		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio xl : xlist) {
			if (xl.getCus().getCustomerId() == 2)
				x = xl;
		}
		Set<Transaction> trans = x.getTransactions();
		for (Transaction t : trans) {
			exp_t = t;
			break;
		}

		Transaction t = xs.CheckForOpenTransaction("OPAP", x);
		assertEquals(exp_t, t);

		t = xs.CheckForOpenTransaction("AEGNN", x);
		assertEquals(null, t);
	}

	@Test
	public void test_transact() {
		int output;
		XartofulakioService xs = new XartofulakioService(em);
		Xartofulakio x = null;
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio xl : xlist) {
			if (xl.getCus().getCustomerId() == 1)
				x = xl;
		}

		try {
			xs.transact("sell", "OPAP", 100, 3.54, "23-02-2020", "open", x);
		} catch (java.lang.RuntimeException e) {
			assertEquals(null, e.getMessage());
		}

		output = xs.transact("sell", "AEGN", 20, 3.54, "25-02-2020", "open", x);
		assertEquals(2, output);

		output = xs.transact("Buy", "AEGN", 100, 10.7, "25-02-2020", "open", x);
		assertEquals(3, output);

		output = xs.transact("sell", "AEGN", 180, 3.54, "25-02-2020", "open", x);
		assertEquals(1, output);

		for (Xartofulakio xl : xlist) {
			if (x.getCus().getCustomerId() == 2)
				x = xl;
		}
		output = xs.transact("Buy", "ACC", 100, 10.7, "25-02-2020", "open", x);
		assertEquals(4, output);
	}
}

package gr.aueb.mscis.sample.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class EvaluateFuturePositionsServiceTest {
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
	public void test_EvaLuateFuturePositionPerStock() {
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		int dxid = 0;
		for (Xartofulakio x : xlist) {
			if (x.getCus().getCustomerId() == 1) {
				dxid = x.getDX().getDXId();
			}
		}

		String exp_results = "Desicion buy\r\n"
				+ "Name AEGN Date 22-02-2020 Beta 0.68.69 MKO15 8.98 MKO80: 8.72 XK20 8.4 YK2O 8.2\r\n"
				+ "UNITS OF STOCK IN PORTOFOLIO 100\r\n"
				+ "Percentage of Units of Stock In Portofolio 0.07039287160793843\r\n" + "BETA of Portofolio 0.6\r\n";

		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(out));
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		e.EvaLuateFuturePositionPerStock("AEGN", dxid, 1, "22-02-2020");
		String results = out.toString();
		assertEquals(exp_results, results);
	}

	@Test
	public void test_findAllMetoxes() {
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		List<Metoxh> exp_m = null;
		List<Metoxh> m = e.findAllMetoxes();
		exp_m = m;
		exp_m.clear();
		exp_m.add(new Metoxh("AEGN", "22-02-2020", 8.70, 8.60, 8.69, 0.6, 132000));
		exp_m.add(new Metoxh("INTRK", "22-02-2020", 0.0, 0.80, 0.88, 0.0, 69000));
		exp_m.add(new Metoxh("MOH", "22-02-2020", 20.6, 0.0, 20.52, 1.3, 80200));
		exp_m.add(new Metoxh("AEGN", "23-02-2020", 8.41, 8.20, 8.36, 0.75, 123000));
		exp_m.add(new Metoxh("OPAP", "22-02-2020", 11.46, 11.39, 0.0, 1.71, 654000));

		assertEquals(exp_m, m);
	}

	@Test
	public void test_informationOfStock() {
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		Metoxh exp_m = new Metoxh("INTRK", "22-02-2020", 0.0, 0.80, 0.88, 0.0, 69000);
		Metoxh m = e.findInformationOfStock("INTRK", "22-02-2020");
		assertEquals(exp_m, m);

		String exp_message = "NO STOCK FOUND";
		try {
			e.findInformationOfStock("OPAP", "01-02-2020");
		} catch (java.lang.RuntimeException msg) {
			assertEquals(exp_message, msg.getMessage());
		}
	}

	@Test
	public void test_deiktesPerStock() {
		Deiktes exp_deikths = new Deiktes("22-02-2020", 8.98, 8.72, 8.20, 8.40);
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		Deiktes deikths = e.findDeiktesPerStock("AEGN", "22-02-2020");
		assertEquals(exp_deikths, deikths);

		String exp_message = "NO DEIKTES RECORD FOUND";
		try {
			e.findDeiktesPerStock("AEGN", "23-02-2020");
		} catch (java.lang.RuntimeException msg) {
			assertEquals(exp_message, msg.getMessage());
		}
	}

	@Test
	public void test_StockImage() {
		String exp_image = "Name AEGN Date 22-02-2020 Beta 0.68.69 MKO15 8.98 MKO80: 8.72 XK20 8.4 YK2O 8.2";
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		String image = e.StockImage("AEGN", "22-02-2020");
		assertEquals(exp_image, image);
	}

	@Test
	public void test_BuyOrSell() {
		String exp_msg = "buy";
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		String msg = e.BuyOrSellPerStock("AEGN", "22-02-2020");
		assertEquals(exp_msg, msg);

		exp_msg = "sell";
		msg = e.BuyOrSellPerStock("INTRK", "22-02-2020");
		assertEquals(exp_msg, msg);

		exp_msg = "none";
		msg = e.BuyOrSellPerStock("MOH", "22-02-2020");
		assertEquals(exp_msg, msg);
	}

	@Test
	public void test_showUnitsofStocksperPortofolio() {
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		int dxid = 0;
		for (Xartofulakio x : xlist) {
			if (x.getCus().getCustomerId() == 1) {
				dxid = x.getDX().getDXId();
			}
		}

		int exp_units = 100;
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		int units = e.showUnitsofStocksperPortofolio("AEGN", dxid, 1);
		assertEquals(exp_units, units);
	}

	@Test
	public void test_showPosostoofStocksperPortofolio() {
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		int dxid = 0;
		for (Xartofulakio x : xlist) {
			if (x.getCus().getCustomerId() == 1) {
				dxid = x.getDX().getDXId();
			}
		}

		double exp_pososto = 0.07;
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		double pososto = e.showPosostoofStocksperPortofolio("AEGN", dxid, 1, "22-02-2020");
		assertEquals(exp_pososto, pososto, 0.01);
	}

	@Test
	public void test_printing() {
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		int dxid = 0;
		for (Xartofulakio x : xlist) {
			if (x.getCus().getCustomerId() == 1) {
				dxid = x.getDX().getDXId();
			}
		}

		String exp_results = "UNITS OF STOCK IN PORTOFOLIO 100\r\n"
				+ "Percentage of Units of Stock In Portofolio 0.07039287160793843\r\n" + "BETA of Portofolio 0.6\r\n";
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(out));

		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		e.printing("AEGN", dxid, 1, "22-02-2020");
		String results = out.toString();
		assertEquals(exp_results, results);
	}
}

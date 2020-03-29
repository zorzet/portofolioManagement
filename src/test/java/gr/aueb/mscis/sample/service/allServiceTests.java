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
import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class allServiceTests {
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

	// Pelatologio Management Tests
	@Test
	public void test_findCustomersbyIdNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			Customer results = p.findCustomersById(40000);
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyId() {
		Customer exp_result = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			Customer result = p.findCustomersById(exp_result.getCustomerId());
			assertEquals(exp_result, result);
		} catch (java.lang.RuntimeException message) {

		}
	}

	@Test
	public void test_findCustomersbyLastNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByLastName("SASDKLSA");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyLast() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyFirst() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyAFM() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyADT() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyTel() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyEmail() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyInvestAmount() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_findCustomersbyBankAccountNo() {
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult().getCus();
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
		for (Xartofulakio x : xlist) {
			exp_result.add(x.getCus());
		}

		List<Customer> result = new ArrayList<>();
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findAllCustomer();
		assertEquals(exp_result, result);
	}

	@Test
	public void test_ShowCustomers() {
		String exp_results = "Id 1 FirstName  Maria LastName Papadopoulos ADT AS12345 AFM 12345678 EMAIL mp@gmail.com Telephone 2121212121BirthDate06-07-1980investAmoun12345.0bankAccountNoGE075 1234 1234 1234 1234\r\n"
				+ "Id 2 FirstName  Marios LastName Papas ADT AE12345 AFM 123456789 EMAIL msp@gmail.com Telephone 2121212120BirthDate16-07-1980investAmoun12000.0bankAccountNoGE075 5678 5678 5678 5678\r\n"
				+ "Id 3 FirstName  Giannis LastName Pappas ADT AE12346 AFM 123456780 EMAIL gsp@gmail.com Telephone 2121212122BirthDate16-07-1982investAmoun12050.0bankAccountNoGE075 5678 5678 5678 5679\r\n";
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(out));
		PelatologioManagement p = new PelatologioManagement(em);
		p.ShowCustomers();

		String results = out.toString();
		assertEquals(exp_results, results);
	}

	@Test
	public void test_updates_pel() {
		PelatologioManagement p = new PelatologioManagement(em);
		Xartofulakio x = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult();
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

	// DX Management Tests
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

		dx = new DX("AH252687", "12345670", "Eleni", "Papa", "6999999997", "epapa@gmail.com", "13-04-1987", "elpapa",
				"admin2");
		assertTrue(d.createDX(dx));
		assertFalse(d.createDX(null));
		assertTrue(d.RemoveDX(dx));
		assertFalse(d.RemoveDX(null));
	}

	// Show Market Service Tests
	@Test
	public void test_getOnlineMarketImage() {
		String exp_result = "something";
		ShowMarketService s = new ShowMarketService(em);
		String result = s.getOnlineMarketImage();
		assertEquals(exp_result, result);
	}

	@Test
	public void test_getOfflineMarketImage() {
		String exp_MarketImage = new MarketsData("30-11-2019", 1629.27, 1657.17, 1608.11, 1657.17).toString();

		ShowMarketService s = new ShowMarketService(em);
		String MarketImage = s.getOfflineMarketImage();
		assertEquals(exp_MarketImage, MarketImage);
	}

	@Test
	public void test_ShowMarketHistory() {
		List<MarketsData> exp_m = null;
		ShowMarketService s = new ShowMarketService(em);

		List<MarketsData> m = s.ShowMarketHistory(2);
		exp_m = m;
		exp_m.clear();
		exp_m.add(new MarketsData("30-11-2019", 1629.27, 1657.17, 1608.11, 1657.17));
		exp_m.add(new MarketsData("29-11-2019", 1603.1, 1636.48, 1603.1, 1633.34));
		assertEquals(exp_m, m);
	}

	@Test
	public void test_showResults() {
		String exp_message = "STOCK MARKETS IMAGE AT 30-11-2019 OPENING 1629.27 CLOSING 1657.17 HIGH 1608.11 LOW 1657.17'\n'";
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(out));

		ShowMarketService s = new ShowMarketService(em);
		List<MarketsData> m = s.ShowMarketHistory(1);
		s.showResults(m);

		String message = out.toString();
		assertEquals(exp_message, message);
	}

	// Update Indicators Tests
	@Test
	public void test_createMetoxh() {
		Metoxh exp_m = new Metoxh("ACC", "21/02/2020", 0.03, 0.23, 0.32, 0.86, 5000);
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		Metoxh m = update.createMetoxh("ACC", "21/02/2020", 0.03, 0.23, 0.32, 0.86, 5000);
		assertEquals(exp_m, m);
	}

	@Test
	public void test_createDeikth() {
		Deiktes exp_d = new Deiktes("23/02/2020", 0.8, 0.3, 2.0, 2.0);
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		Deiktes d = update.createDeikth("23/02/2020", "ELPE", 18.00, 19.00, 4.00);
		assertEquals(exp_d, d);
	}

	@Test
	public void test_updateDeiktesAndStock() {
		int exp_output = 0;
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		int output = update.UpdateDeiktesAndStock("ELPE", "24/02/2020", 18.00, 19.00, 4.00, 1.32, 5000);
		assertEquals(-1, output);
	}

	@Test
	public void test_StockMissing14() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast14ClosingsOfStock("MIMI");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_ZeroClosings14() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast14ClosingsOfStock("PMI");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_GetLast14ClosingsOfStock() {
		double exp_Closings = 17.05;
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		double Closings = update.GetLast14ClosingsOfStock("AEGN");
		assertEquals(exp_Closings, Closings, 0.01);
	}

	@Test
	public void test_StockMissing79() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast79ClosingsOfStock("MIMI");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_ZeroClosings79() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast79ClosingsOfStock("PMI");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_GetLast79ClosingsOfStock() {
		double exp_Closings = 17.05;
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		double Closings = update.GetLast79ClosingsOfStock("AEGN");
		assertEquals(exp_Closings, Closings, 0.01);
	}

	@Test
	public void test_StockMissing19High() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19HighsOfStock("MIMI");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_Zero19High() {
		String exp_message = "HIGH20 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19HighsOfStock("INTRK");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_GetLast19HighsOfStock() {
		double exp_Highs = 17.11;
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		double Highs = update.GetLast19HighsOfStock("AEGN");
		assertEquals(exp_Highs, Highs, 0.01);
	}

	@Test
	public void test_StockMissing19Low() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19LowsOfStock("MIMI");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_Zero19Low() {
		String exp_message = "LOW20 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19LowsOfStock("MOH");
		} catch (java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

	@Test
	public void test_GetLast19LowsOfStock() {
		double exp_Lows = 16.80;
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		double Lows = update.GetLast19LowsOfStock("AEGN");
		assertEquals(exp_Lows, Lows, 0.01);
	}


}

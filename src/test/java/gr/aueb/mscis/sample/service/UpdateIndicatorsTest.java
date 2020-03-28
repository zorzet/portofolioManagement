package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;
import org.junit.*;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.UpdateIndicatorsService;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.Deiktes;

public class UpdateIndicatorsTest {
	
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
		assertEquals(exp_output, output);
	}
	
	@Test
	public void test_StockMissing14() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast14ClosingsOfStock("MIMI");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_ZeroClosings14() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast14ClosingsOfStock("PMI");
		} catch(java.lang.RuntimeException message) {
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
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_ZeroClosings79() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast79ClosingsOfStock("PMI");
		} catch(java.lang.RuntimeException message) {
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
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_Zero19High() {
		String exp_message = "HIGH20 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19HighsOfStock("INTRK");
		} catch(java.lang.RuntimeException message) {
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
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_Zero19Low() {
		String exp_message = "LOW20 COULD NOT BE CALCULATED";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19LowsOfStock("MOH");
		} catch(java.lang.RuntimeException message) {
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

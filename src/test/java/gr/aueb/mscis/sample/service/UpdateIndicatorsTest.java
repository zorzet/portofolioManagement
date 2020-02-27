package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;
import org.junit.*;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.UpdateIndicatorsService;
import gr.aueb.mscis.sample.model.Metoxh;;

public class UpdateIndicatorsTest {
	
	protected EntityManager em;
	
	@Before
	public void setup(){
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();
		em = JPAUtil.getCurrentEntityManager();
	}
	
	@After
	public void tearDown(){
		em.close();
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
			update.GetLast14ClosingsOfStock("OPAP");
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
			update.GetLast79ClosingsOfStock("OPAP");
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

package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;
import javax.persistence.*;
import org.junit.*;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.UpdateIndicatorsService;

public class UpdateIndicatorsTest {
	
	protected EntityManager em;
	
	@Before
	public void setup(){
		em = JPAUtil.getCurrentEntityManager();
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	@After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void test_StockMissing() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast14ClosingsOfStock(99999);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}

}

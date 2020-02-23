package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;
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
			update.GetLast14ClosingsOfStock(9999);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_GetLast14ClosingsOfStock() {
		double exp_Closings = 8.69;
	    int m_id = em.createQuery("select m.StockId from Metoxh m").getFirstResult();
		System.out.println(m_id);
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		
		try {			
			double Closings = update.GetLast14ClosingsOfStock(m_id);
			assertEquals(exp_Closings, Closings, 0.0);
		} catch(java.lang.RuntimeException message) {
			System.out.println(message.getMessage());
		}
	}

	
	@Test
	public void test_StockMissing79() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast79ClosingsOfStock(9999);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_GetLast79ClosingsOfStock() {
		double exp_Closings = 8.69;
	    int m_id = em.createQuery("select m.StockId from Metoxh m where m.Name = 'AEGN' ").getFirstResult();
				
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		
		try {			
			double Closings = update.GetLast14ClosingsOfStock(m_id);
			assertEquals(exp_Closings, Closings, 0.0);
		} catch(java.lang.RuntimeException message) {
			System.out.println(message.getMessage());
		}
	}
}

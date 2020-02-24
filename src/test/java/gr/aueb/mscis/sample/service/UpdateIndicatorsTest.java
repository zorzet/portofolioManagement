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
			update.GetLast14ClosingsOfStock(9999);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_ZeroClosings14() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		int m_id = em.createQuery("select m.StockId from Metoxh m where m.Name = 'OPAP'").getFirstResult();
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast14ClosingsOfStock(m_id);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_GetLast14ClosingsOfStock() {
		double exp_Closings = 8.69;
	    int m_id = em.createQuery("select m.StockId from Metoxh m where m.Name = 'AEGN' ").getFirstResult();
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		
		try {			
			double Closings = update.GetLast14ClosingsOfStock(m_id);
			assertEquals(exp_Closings, Closings, 0.0);
		} catch(java.lang.RuntimeException message) {
			
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
	public void test_ZeroClosings79() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		int m_id = em.createQuery("select m.StockId from Metoxh m where m.Name = 'OPAP' ").getFirstResult();
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast79ClosingsOfStock(m_id);
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
			double Closings = update.GetLast79ClosingsOfStock(m_id);
			assertEquals(exp_Closings, Closings, 0.0);
		} catch(java.lang.RuntimeException message) {

		}
	}
	
	@Test
	public void test_StockMissing19High() {
		String exp_message = "NO RECORDS FOUND";
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19HighsOfStock(9999);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_Zero19High() {
		String exp_message = "MKO30 COULD NOT BE CALCULATED";
		int m_id = em.createQuery("select m.StockId from Metoxh m where m.Name = 'OPAP' ").getFirstResult();
		UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		try {
			update.GetLast19HighsOfStock(m_id);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_GetLast19HighsOfStock() {
		double exp_Highs = 8.70;
		int m_id=0;
		List <Metoxh> mlist=null;
			mlist=em.createQuery(
				"select m from Metoxh m where m.StockId like : StockId")
	    		.setParameter("StockId", 1).getResultList();
			m_id=mlist.get(0).getStockId();
			UpdateIndicatorsService update = new UpdateIndicatorsService(em);
		
		try {			
			double Highs = update.GetLast19HighsOfStock(m_id);
			assertEquals(exp_Highs, Highs, 0.0);
		} catch(java.lang.RuntimeException message) {

		}
	}
}

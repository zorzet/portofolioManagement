package gr.aueb.mscis.sample.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.sample.service.ShowMarketService;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class ShowMarketServiceTest {

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
	public void test_getOnlineMarketImage() {
		String exp_result="something";
		ShowMarketService s = new ShowMarketService(em);
		String result = s.getOnlineMarketImage();
		assertEquals(exp_result,result);
	}
	
	@Test
	public void test_getOfflineMarketImage() {
		String exp_result="something";
		ShowMarketService s = new ShowMarketService(em);
		String result = s.getOnlineMarketImage();
		assertEquals(exp_result,result);
	}
}

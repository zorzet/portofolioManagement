package gr.aueb.mscis.sample.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.sample.service.ShowMarketService;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class ShowMarketServiceTest {

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
	public void test_getOfflineMarketImage() {
		
	}
}

package gr.aueb.mscis.sample.service;

import javax.persistence.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

import gr.aueb.mscis.sample.service.ShowMarketService;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class ShowMarketServiceTest {
	
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
	public void test_getOnlineMarketImage() {
		String exp_result="something";
		ShowMarketService s = new ShowMarketService(em);
		String result = s.getOnlineMarketImage();
		assertEquals(exp_result,result);
	}
	
	@Test
	public void test_getOfflineMarketImage() {
		String exp_MarketImage = new MarketsData("30-11-2019",1629.27,1657.17,1608.11,1657.17).toString();
        
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
		exp_m.add(new MarketsData("30-11-2019",1629.27,1657.17,1608.11,1657.17));
		exp_m.add(new MarketsData("29-11-2019",1603.1,1636.48,1603.1,1633.34));		
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
		assertEquals(exp_message,message);
	}
	

}

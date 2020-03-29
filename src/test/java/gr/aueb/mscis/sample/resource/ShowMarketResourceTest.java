package gr.aueb.mscis.sample.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.ShowMarketService;

public class ShowMarketResourceTest extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(ShowMarketResource.class);
	}
	
	public ShowMarketResourceTest() {
		super();
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}

	@Test
	public void getOnlineMarketImageTest() {
		String exp_output = "something";
		String output = target("/ShowMarket/getOnlineMarket").request().get(String.class);
		assertEquals(exp_output, output);
	}
	
	@Test
	public void getOfflineMarketImageTest() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		ShowMarketService s = new ShowMarketService(em);
		String exp_output = s.getOfflineMarketImage();
		String output = target("/ShowMarket/getOfflineMarket").request().get(String.class);
		assertEquals(exp_output, output);
	}
	
	@Test
	public void showMarketHistoryTest() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		ShowMarketService s = new ShowMarketService(em);
		List<MarketsData> exp_output = s.ShowMarketHistory(2);
		List<MarketsData> output = new ArrayList<MarketsData>();

		List<MarketsDataInfo> outputinfo = target("/ShowMarket/ShowMarketHistory/2").request().get(new GenericType<List<MarketsDataInfo>>() {});
		for(MarketsDataInfo m : outputinfo) {
			output.add(m.getMarketsData(em));
		}
		
		assertEquals(exp_output,output);
		
		try {
			target("/ShowMarket/ShowMarketHistory/2").request().get(new GenericType<List<MarketsDataInfo>>() {});
		} catch(java.lang.RuntimeException msg) {
			assertNull(msg);
		}
	}
}

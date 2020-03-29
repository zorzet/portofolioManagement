package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.XartofulakioService;

public class XartofulakioResourceTest extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(XartofulakioResource.class);
	}
	
	public XartofulakioResourceTest() {
		super();
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	@Test
	public void test_findXartofulakiaById() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		XartofulakioService x = new XartofulakioService(em);
		
	}
}

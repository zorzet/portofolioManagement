package gr.aueb.mscis.sample.resource;

import javax.ws.rs.core.Application;

import org.junit.*;
import static org.junit.Assert.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.resource.ShowMarketResource;

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
	
}

package gr.aueb.mscis.sample.resource;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import org.junit.*;
import static org.junit.Assert.*;
import gr.aueb.mscis.sample.persistence.Initializer;

public class UpdateIndicatorsResourceTest extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(UpdateIndicatorsResource.class);
	}
	
	public UpdateIndicatorsResourceTest() {
		super();
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	@Test
	public void test_UpdateDeiktesAndStock() {
		Response response = target("UpdateIndicators/ELPE/18.00/19.00/4.00/1.32/5000/24-02-2020").request().post(Entity.text(""));
		assertEquals(Status.ACCEPTED.getStatusCode(), response.getStatus());
	}
}

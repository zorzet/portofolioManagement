package gr.aueb.mscis.sample.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import org.junit.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import gr.aueb.mscis.sample.persistence.Initializer;

public class LoginResourceTest extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(LoginResource.class);
	}
	
	public LoginResourceTest() {
		super();
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	@Test
	public void test_login() {
		String exp_output = "SUCCESS";
		String output = target("/Login/mpapa/123456").request().get(String.class);
		assertEquals(exp_output, output);
		exp_output = "FAIL";
		output = target("/Login/mpapa/1234567").request().get(String.class);
		assertEquals(exp_output, output);
	}
	
}

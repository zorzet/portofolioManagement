package gr.aueb.mscis.sample.service;

import static org.junit.Assert.*;
import javax.persistence.*;
import org.junit.*;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;

public class XartofulakioServiceTest {
	
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
	
}

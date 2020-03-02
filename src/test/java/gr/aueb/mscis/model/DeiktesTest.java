package gr.aueb.mscis.model;

import static org.junit.Assert.*;

import org.junit.*;

import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;

public class DeiktesTest {

	Deiktes d;

	@Before
    public void setUp() {
		d = new Deiktes();
    }
	
	@Test
	public void test_Id() {
		int exp_Id = 1;
		d.setId(1);
		int Id = d.getId();
		assertEquals(exp_Id, Id);
	}

	@Test
	public void test_date() {
		String exp_date = "20/02/2020";
		d.setDate("20/02/2020");
		String date = d.getDate();
		assertEquals(exp_date, date);
	}
	
	@Test
	public void test_MKO15() {
		double exp_MKO15 = 12.33;
		d.setMKO15(12.33);
		double MKO15 = d.getMKO15();
		assertEquals(exp_MKO15, MKO15, 0.0);
	}
	
	@Test
	public void test_MKO80() {
		double exp_MKO80 = 12.33;
		d.setMKO80(12.33);
		double MKO80 = d.getMKO80();
		assertEquals(exp_MKO80, MKO80, 0.0);
	}
	
	@Test
	public void test_Yk20() {
		double exp_Yk20 = 12.33;
		d.setYk20(12.33);
		double Yk20 = d.getYk20();
		assertEquals(exp_Yk20, Yk20, 0.0);
	}
	
	@Test
	public void test_Χk20() {
		double exp_Xk20 = 12.33;
		d.setXk20(12.33);
		double Xk20 = d.getXk20();
		assertEquals(exp_Xk20, Xk20, 0.0);
	}
	
	@Test
	public void test_metoxh() {
		
	}
	
	@Test
	public void test_Deiktes() {
		String exp_date = "20/02/2020";
		double exp_ΜΚΟ15 = 12.33;
		double exp_ΜΚΟ80 = 12.33;
		double exp_yk20 = 12.33;
		double exp_xk20 = 12.33;
		Deiktes test = new Deiktes("20/02/2020", 12.33, 12.33, 12.33, 12.33);
		String date = test.getDate();
		double ΜΚΟ15 = test.getMKO15();
		double MKO80 = test.getMKO80();
		double yk20 = test.getYk20();
		double xk20 = test.getXk20();
		assertEquals(exp_date, date);
		assertEquals(exp_ΜΚΟ15, ΜΚΟ15, 0.000);
		assertEquals(exp_ΜΚΟ80, MKO80, 0.000);
		assertEquals(exp_yk20, yk20, 0.000);
		assertEquals(exp_xk20, xk20, 0.000);
	}
	
	@Test
	public void test_equals() {
		Deiktes test = new Deiktes("20/02/2020", 12.33, 12.33, 12.33, 12.33);
		Deiktes exp = new Deiktes("20/02/2020", 12.33, 12.33, 12.33, 12.33);
		assertTrue(test.equals(exp));
		assertFalse(test.equals(null));
		assertFalse(test.equals(new Metoxh("AEGN", "22/02/2020", 8.70, 8.60, 8.69, 0.6, 132000)));
		
		test = new Deiktes("20/02/2020", null, 12.33, 12.33, 12.33);
		assertFalse(test.equals(exp));
		test = new Deiktes("20/02/2020", 12.34, 12.33, 12.33, 12.33);
		assertFalse(test.equals(exp));
		
		test = new Deiktes("20/02/2020", 12.33, null, 12.33, 12.33);
		assertFalse(test.equals(exp));
		test = new Deiktes("20/02/2020", 12.34, 12.34, 12.33, 12.33);
		assertFalse(test.equals(exp));
		
		test = new Deiktes(null, 12.33, 12.33, 12.33, 12.33);
		assertFalse(test.equals(exp));
		test = new Deiktes("21/02/2020", 12.34, 12.33, 12.33, 12.33);
		assertFalse(test.equals(exp));
		
		test = new Deiktes("20/02/2020", 12.33, 12.33, null, 12.33);
		assertFalse(test.equals(exp));
		test = new Deiktes("20/02/2020", 12.34, 12.33, 12.34, 12.33);
		assertFalse(test.equals(exp));
		
		test = new Deiktes("20/02/2020", 12.33, 12.33, 12.33, null);
		assertFalse(test.equals(exp));
		test = new Deiktes("20/02/2020", 12.34, 12.33, 12.34, 12.34);
		assertFalse(test.equals(exp));
	}
}

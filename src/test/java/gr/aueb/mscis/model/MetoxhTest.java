package gr.aueb.mscis.model;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.HashSet;
import java.util.Set;

import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;

public class MetoxhTest {

	Metoxh m;
	
	@Before
    public void setUp() {
		m = new Metoxh();
    }
	
	@Test
	public void test_StockId() {
		int exp_StockId = 1;
		m.setStockId(1);
		int StockId = m.getStockId();
		assertEquals(exp_StockId, StockId);
	}
	
	@Test
	public void test_Name() {
		String exp_Name = "ACC";
		m.setName("ACC");
		String Name = m.getName();
		assertEquals(exp_Name, Name);		
	}
	
	@Test
	public void test_Date() {
		String exp_date = "20.02.2020";
		m.setDate("20.02.2020");
		String date = m.getDate();
		assertEquals(exp_date, date);
	}
	
	@Test
	public void test_High() {
		double exp_High = 12.33;
		m.setHigh(12.33);
		double High = m.getHigh();
		assertEquals(exp_High, High, 0.0);
	}
	
	@Test
	public void test_Low() {
		double exp_Low = 12.33;
		m.setLow(12.33);
		double Low = m.getLow();
		assertEquals(exp_Low, Low, 0.0);
	}
	
	@Test
	public void test_Closing() {
		double exp_Closing = 12.33;
		m.setClosing(12.33);
		double Closing = m.getClosing();
		assertEquals(exp_Closing, Closing, 0.0);
	}
	
	@Test
	public void test_Beta() {
		double exp_Beta = 12.33;
		m.setBeta(12.33);
		double Beta = m.getBeta();
		assertEquals(exp_Beta, Beta, 0.0);
	}
	
	@Test
	public void test_Volume() {
		int exp_Volume = 500;
		m.setVolume(500);
		int Volume = m.getVolume();
		assertEquals(exp_Volume, Volume);
	}
	
	@Test
	public void test_deikths() {
		Metoxh test = new Metoxh("ACC", "20/02/2020", 12.33, 12.33, 12.33, 12.33, 500);
		Deiktes exp_d = new Deiktes("20/02/2020", 12.33, 12.33, 12.33, 12.33);
		test.setDeikth(new Deiktes("20/02/2020", 12.33, 12.33, 12.33, 12.33));
		Deiktes d = test.getDeikth();
		assertEquals(exp_d, d);
	}
	
	@Test
	public void test_DX() {
		Metoxh test = new Metoxh("ACC", "20/02/2020", 12.33, 12.33, 12.33, 12.33, 500);
		DX dx = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26/05/1990", "mpapa", "123456");
		test.addDX(dx);
		Set<DX> dxs = test.getDXs();
		Set<DX> exp_dxs = new HashSet<DX>();
		exp_dxs.add(dx);
		assertEquals(exp_dxs, dxs);
		
		test.removeDX(dx);
		dxs = test.getDXs();
		exp_dxs.remove(dx);
	}
	
	@Test
	public void test_Metoxh() {
		String exp_name = "ACC";
		String exp_date = "20.02.2020";
		double exp_High = 12.33;
		double exp_Low = 12.33;
		double exp_Closing = 12.33;
		double exp_Beta = 12.33;
		int exp_volume = 500;
		Metoxh test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.33, 12.33, 500);
		String name = test.getName();
		String date = test.getDate();
		double High = test.getHigh();
		double Low = test.getLow();
		double Closing = test.getClosing();
		double Beta = test.getBeta();
		int volume = test.getVolume();
		assertEquals(exp_name, name);
	}
	
	@Test
	public void test_equals() {
		Metoxh test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.33, 12.33, 500);
		Metoxh exp = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.33, 12.33, 500);
		assertTrue(test.equals(exp));
		assertFalse(test.equals(null));
		assertFalse(test.equals(new Deiktes("20/02/2020", 12.33, 12.33, 12.33, 12.33)));
		
		test = new Metoxh(null, "12.02.2020", 12.33, 12.33, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		test = new Metoxh("ACC1", "12.02.2020", 12.33, 12.33, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		
		test = new Metoxh("ACC", null, 12.33, 12.33, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		test = new Metoxh("ACC", "13.02.2020", 12.33, 12.33, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		
		test = new Metoxh("ACC", "12.02.2020", null, 12.33, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		test = new Metoxh("ACC", "12.02.2020", 12.34, 12.33, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		
		test = new Metoxh("ACC", "12.02.2020", 12.33, null, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		test = new Metoxh("ACC", "12.02.2020", 12.33, 12.34, 12.33, 12.33, 500);
		assertFalse(test.equals(exp));
		
		test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, null, 12.33, 500);
		assertFalse(test.equals(exp));
		test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.34, 12.33, 500);
		assertFalse(test.equals(exp));
		
		test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.33, null, 500);
		assertFalse(test.equals(exp));
		test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.33, 12.34, 500);
		assertFalse(test.equals(exp));
		
		test = new Metoxh("ACC", "12.02.2020", 12.33, 12.33, 12.33, 12.33, 501);
		assertFalse(test.equals(exp));
		
	}
}

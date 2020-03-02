package gr.aueb.mscis.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Xartofulakio;

public class TransactionTest {
	
	Transaction t;
	
	@Before
    public void setUp() {
		t = new Transaction();
    }
	
	@Test
	public void test_TransIdId() {
		int exp_TransId = 1;
		t.setTransId(1);
		int TransId = t.getTransId();
		assertEquals(exp_TransId, TransId);
	}

	@Test
	public void test_CmdType() {
		String exp_CmdType = "Αγορά";
		t.setCmdType("Αγορά");
		String CmdType = t.getCmdType();
		assertEquals(exp_CmdType, CmdType);
	}
	
	@Test
	public void test_Stock() {
		String exp_Stock = "ACC";
		t.setStock("ACC");
		String Stock = t.getStock();
		assertEquals(exp_Stock, Stock);
	}
	
	@Test
	public void test_Units() {
		int exp_Units = 100;
		t.setUnits(100);
		int Units = t.getUnits();
		assertEquals(exp_Units, Units);
	}
	
	@Test
	public void test_Price() {
		double exp_Price = 5.22;
		t.setPrice(5.22);
		double Price = t.getPrice();
		assertEquals(exp_Price, Price, 0.0);
	}
	
	@Test
	public void test_Date() {
		String exp_Date = "20.02.2020";
		t.setDate("20.02.2020");
		String Date = t.getDate();
		assertEquals(exp_Date, Date);
	}
	
	@Test
	public void test_State() {
		String exp_State = "Pending";
		t.setState("Pending");
		String State = t.getState();
		assertEquals(exp_State, State);
	}
	
	@Test
	public void test_Xartofulakio() {
		Xartofulakio exp_x = new Xartofulakio("created", new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		t.setXartofulakio(new Xartofulakio("created", new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234")));
		Xartofulakio x = t.getXartofulakio();
		assertEquals(exp_x, x);
	}
	
	@Test
	public void test_Transaction() {
		String exp_CmdType = "Αγορά";
		String exp_Stock = "ACC";
		int exp_Units = 100;
		double exp_Price = 5.22;
		String exp_Date = "20.02.2020";
		String exp_State = "Pending";
		Transaction test = new Transaction("Αγορά", "ACC", 100, 5.22, "20.02.2020", "Pending");
		String CmdType = test.getCmdType();
		String Stock = test.getStock();
		int Units = test.getUnits();
		double Price = test.getPrice();
		String Date = test.getDate();
		String State = test.getState();
		assertEquals(exp_CmdType, CmdType);
		assertEquals(exp_Stock, Stock);
		assertEquals(exp_Units, Units);
		assertEquals(exp_Price, Price, 0.0);
		assertEquals(exp_Date, Date);
		assertEquals(exp_State, State);
	}
	
	@Test
	public void test_isPending() {
		Transaction test = new Transaction("Αγορά", "ACC", 100, 5.22, "20.02.2020", "Pending");
		assertTrue(test.isPending());
	}
	
	@Test
	public void test_xartofulakio() {
		Xartofulakio exp_x = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		Transaction test = new Transaction("Αγορά", "ACC", 100, 5.22, "20.02.2020", "Pending");
		test.setXartofulakio(exp_x);
		Xartofulakio x = test.getXartofulakio();
		assertEquals(exp_x, x);
		
		test.setXartofulakio(null);
		x = null;
		x = test.getXartofulakio();
		assertEquals(null, x);
	}	
	
	@Test
	public void test_equals() {
		Transaction test = new Transaction("Buy", "ACC", 100, 5.22, "20.02.2020", "Pending");
		Transaction exp = new Transaction("Buy", "ACC", 100, 5.22, "20.02.2020", "Pending");
		assertTrue(test.equals(exp));
		assertFalse(test.equals(null));
		assertFalse(test.equals(new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890")));
		
		test = new Transaction(null, "ACC", 100, 5.22, "20.02.2020", "Pending");
		assertFalse(test.equals(exp));
		test = new Transaction("Buy1", "ACC", 100, 5.22, "20.02.2020", "Pending");
		assertFalse(test.equals(exp));
		
		test = new Transaction("Buy", null, 100, 5.22, "20.02.2020", "Pending");
		assertFalse(test.equals(exp));
		test = new Transaction("Buy", "ACC1", 100, 5.22, "20.02.2020", "Pending");
		assertFalse(test.equals(exp));
		
		test = new Transaction("Buy", "ACC", 1000, 5.22, "20.02.2020", "Pending");
		assertFalse(test.equals(exp));
		
		test = new Transaction("Buy", "ACC", 100, 5.23, "20.02.2020", "Pending");
		assertFalse(test.equals(exp));
		
		test = new Transaction(null, "ACC", 100, 5.22, null, "Pending");
		assertFalse(test.equals(exp));
		test = new Transaction("Buy", "ACC", 100, 5.22, "21.02.2020", "Pending");
		assertFalse(test.equals(exp));
		
		test = new Transaction(null, "ACC", 100, 5.22, "20.02.2020", null);
		assertFalse(test.equals(exp));
		test = new Transaction("Buy", "ACC", 100, 5.22, "20.02.2020", "Pending1");
		assertFalse(test.equals(exp));
	}
}

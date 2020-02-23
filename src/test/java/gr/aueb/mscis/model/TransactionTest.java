package gr.aueb.mscis.model;

import static org.junit.Assert.*;

import org.junit.*;
import gr.aueb.mscis.sample.model.Transaction;

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
	public void test_Transaction() {
		String exp_CmdType = "Αγορά";
		String exp_Stock = "ACC";
		int exp_Units = 100;
		double exp_Price = 5.22;
		String exp_Date = "20.02.2020";
		String exp_State = "Pending";
		Transaction test = new Transaction(1, "Αγορά", "ACC", 100, 5.22, "20.02.2020", "Pending");
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
	}
	
	@Test
	public void test_isPending() {
		Transaction test = new Transaction(1, "Αγορά", "ACC", 100, 5.22, "20.02.2020", "Pending");
		assertTrue(test.isPending());
	}
}
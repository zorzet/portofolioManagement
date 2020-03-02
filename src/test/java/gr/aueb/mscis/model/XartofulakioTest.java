package gr.aueb.mscis.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.DX;

public class XartofulakioTest {

	Xartofulakio x;

	@Before
    public void setUp() {
		x = new Xartofulakio();
    }
	
	@Test
	public void test_Xid() {
		int exp_Xid = 1;
		x.setXid(1);
		int Xid = x.getXid();
		assertEquals(exp_Xid, Xid);
	}
	
	@Test
	public void test_TrexousaThesi() {
		String exp_TrexousaThesi = "ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ";
		x.setTrexousathesi("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ");
		String TrexousaThesi = x.getTrexousathesi();
		assertEquals(exp_TrexousaThesi, TrexousaThesi);
	}
	
	@Test
	public void test_Customer() {
		Customer exp_customer = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		x.setCus(new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		Customer customer = x.getCus();
		assertEquals(exp_customer, customer);
	}
	
	@Test
	public void test_Xartofulakio() {
		String exp_TrexousaThesi = "ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ";
		Customer exp_c = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		Xartofulakio test = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		String TrexousaThesi = test.getTrexousathesi();
		Customer c = test.getCus();
		assertEquals(exp_TrexousaThesi, TrexousaThesi);
		assertEquals(exp_c, c);
	}
	
	@Test
	public void test_countPendingStocks() {
		Xartofulakio test = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		test.addTransaction(new Transaction("Buy", "AEGN", 100, 30.6, "20/02/2020", "Pending"));
		int pend = test.countPendingStocks();
		assertEquals(1, pend);
	}
	
	@Test
	public void test_transactions() {
		Set<Transaction> exp_trans = new HashSet<Transaction>();
		Transaction t = new Transaction("Buy", "AEGN", 100, 30.6, "20/02/2020", "Pending");
		exp_trans.add(t);
		Xartofulakio test = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		test.addTransaction(t);
		Set<Transaction> trans = test.getTransactions();
		assertEquals(exp_trans, trans);
		
		test.removeTransaction(t);
		trans.clear();
		exp_trans.remove(t);
		trans = test.getTransactions();
		assertEquals(exp_trans, trans);
	}
	
	@Test
	public void test_DX() {
		DX exp_dx = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26/05/1990");
		Xartofulakio test = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		test.setDX(new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26/05/1990"));
		DX dx = test.getDX();
		assertEquals(exp_dx, dx);
	}
	
	@Test
	public void test_equals() {
		Xartofulakio x1 = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		assertTrue(x1.equals(new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"))));
		assertFalse(x1.equals(null));
		assertFalse(x1.equals(new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26/05/1990")));
		assertFalse(x1.equals(new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", null)));
		assertFalse(x1.equals(new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252689", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"))));
		assertFalse(x1.equals(new Xartofulakio(null, new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"))));
		assertFalse(x1.equals(new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ1", new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"))));
	
		x1 = new Xartofulakio(null, new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		assertFalse(x1.equals(new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252689", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"))));
		
		x1 = new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", null);
		assertFalse(x1.equals(new Xartofulakio("ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", new Customer(1, "AH252689", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"))));
		
	}
}

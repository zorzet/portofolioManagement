package gr.aueb.mscis.model;

import static org.junit.Assert.*;
import org.junit.*;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Xartofulakio;

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
		int pend = test.countPendingStocks();
		assertEquals(1, pend);
	}
}

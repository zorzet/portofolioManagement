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
		Customer exp_customer = new Customer(10, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		x.setCus(new Customer(10, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890"));
		Customer customer = x.getCus();
		assertEquals(exp_customer, customer);
	}
	
	@Test
	public void test_Xartofulakio() {
		String exp_TrexousaThesi = "ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ";
		int exp_CustomerId = 10;
		String exp_ADT = "AH252687";
		String exp_AFM = "12345678";
		String exp_Name = "Maria";
		String exp_Surname = "Papadopoulou";
		String exp_Tel = "6999999999";
		String exp_Email = "mpapadopoulou@gmail.com";
		String exp_BirthDate = "26.05.1990";
		int exp_InvestAmount = 5000;
		String exp_BankAccountNo = "1234567890";
		Xartofulakio test = new Xartofulakio(1, "ΑΔΕΙΟ ΧΑΡΤΟΦΥΛΑΚΙΟ", 10, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		String TrexousaThesi = test.getTrexousathesi();
		int CustomerId = test.getCus().getCustomerId();
		String ADT = test.getCus().getADT();
		String AFM = test.getCus().getAFM();
		String Name = test.getCus().getName();
		String Surname = test.getCus().getSurname();
		String Tel = test.getCus().getTel();
		String Email = test.getCus().getEmail();
		String BirthDate = test.getCus().getBirthDate();
		int InvestAmount = test.getCus().getInvestAmount();
		String BankAccountNo = test.getCus().getBankAccountNo();
		assertEquals(exp_TrexousaThesi, TrexousaThesi);
		assertEquals(exp_CustomerId, CustomerId);
		assertEquals(exp_ADT, ADT);
		assertEquals(exp_AFM, AFM);
		assertEquals(exp_Name, Name);
		assertEquals(exp_Surname, Surname);
		assertEquals(exp_Tel, Tel);
		assertEquals(exp_Email, Email);
		assertEquals(exp_BirthDate, BirthDate);
		assertEquals(exp_InvestAmount, InvestAmount);
		assertEquals(exp_BankAccountNo, BankAccountNo);
	}
	
	@Test
	public void test_countPendingStocks() {
		
	}
}

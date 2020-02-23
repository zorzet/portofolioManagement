package gr.aueb.mscis.model;

import static org.junit.Assert.*;

import org.junit.*;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.DX;

public class CustomerTest {

	Customer c;

	@Before
    public void setUp() {
		c = new Customer();
    }
	
	@Test
	public void test_CustomerId() {
		int exp_Id = 1;
		c.setCustomerId(1);
		int Id = c.getCustomerId();
		assertEquals(exp_Id, Id);
	}

	@Test
	public void test_ADT() {
		String exp_ADT = "AH252687";
		c.setADT("AH252687");
		String ADT = c.getADT();
		assertEquals(exp_ADT, ADT);
	}
	
	@Test
	public void test_AFM() {
		String exp_AFM = "12345678";
		c.setAFM("12345678");
		String AFM = c.getAFM();
		assertEquals(exp_AFM, AFM);
	}
	
	@Test
	public void test_Name() {
		String exp_Name = "Maria";
		c.setName("Maria");
		String Name = c.getName();
		assertEquals(exp_Name, Name);		
	}
	
	@Test
	public void test_Surname() {
		String exp_Surname = "Papadopoulou";
		c.setSurname("Papadopoulou");
		String Surname = c.getSurname();
		assertEquals(exp_Surname, Surname);
	}
	
	@Test
	public void test_Tel() {
		String exp_Tel = "6999999999";
		c.setTel("6999999999");
		String Tel = c.getTel();
		assertEquals(exp_Tel, Tel);
	}
	
	@Test
	public void test_Email() {
		String exp_Email = "mpapadopoulou@gmail.com";
		c.setEmail("mpapadopoulou@gmail.com");
		String Email = c.getEmail();
		assertEquals(exp_Email, Email);
	}
	
	@Test
	public void test_BirthDate() {
		String exp_BirthDate = "26.05.1990";
		c.setBirthDate("26.05.1990");
		String BirthDate = c.getBirthDate();
		assertEquals(exp_BirthDate, BirthDate);
	}
	
	@Test
	public void test_InvestAmount() {
		double exp_InvestAmount = 5000;
		c.setInvestAmount(5000);
		double InvestAmount = c.getInvestAmount();
		assertEquals(exp_InvestAmount, InvestAmount, 0.0);
	}
	
	@Test
	public void test_BankAccountNo() {
		String exp_BankAccountNo = "1234567890";
		c.setBankAccountNo("1234567890");
		String BankAccountNo = c.getBankAccountNo();
		assertEquals(exp_BankAccountNo, BankAccountNo);
	}
	
	@Test
	public void test_toString() {
		Customer test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		String str = test.toString();
	}
	
	@Test
	public void test_Customer() {
		String exp_ADT = "AH252687";
		String exp_AFM = "12345678";
		String exp_Name = "Maria";
		String exp_Surname = "Papadopoulou";
		String exp_Tel = "6999999999";
		String exp_Email = "mpapadopoulou@gmail.com";
		String exp_BirthDate = "26.05.1990";
		double exp_InvestAmount = 5000;
		String exp_BankAccountNo = "1234567890";
		Customer test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		String ADT = test.getADT();
		String AFM = test.getAFM();
		String Name = test.getName();
		String Surname = test.getSurname();
		String Tel = test.getTel();
		String Email = test.getEmail();
		String BirthDate = test.getBirthDate();
		double InvestAmount = test.getInvestAmount();
		String BankAccountNo = test.getBankAccountNo();
		assertEquals(exp_ADT, ADT);
		assertEquals(exp_AFM, AFM);
		assertEquals(exp_Name, Name);
		assertEquals(exp_Surname, Surname);
		assertEquals(exp_Tel, Tel);
		assertEquals(exp_Email, Email);
		assertEquals(exp_BirthDate, BirthDate);
		assertEquals(exp_InvestAmount, InvestAmount, 0.0);
		assertEquals(exp_BankAccountNo, BankAccountNo);
	}
	
	@Test
	public void test_Equals() {
		Customer exp_cust = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		Customer test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertTrue(test.equals(exp_cust));
		assertFalse(test.equals(null));
		
		test = null;
		test = new Customer(2, "AH252699", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, null, "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252699", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", null, "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345699", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", null, "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria1", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", null, "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou1", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", null, "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999998", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", null, "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.gr", "26.05.1990", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", null, 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1991", 5000, "1234567890");
		assertFalse(test.equals(exp_cust));
			
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5001, "1234567890");
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, null);
		assertFalse(test.equals(exp_cust));
		
		test = null;
		test = new Customer(1, "AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990", 5000, "1234567899");
		assertFalse(test.equals(exp_cust));

	}
}

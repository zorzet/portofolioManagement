package gr.aueb.mscis.sample.service;

import java.util.*;

import javax.persistence.*;
import org.junit.*;
import static org.junit.Assert.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.PelatologioManagement;

public class PelatologioManagementTest {
	protected static EntityManager em;
	
	@BeforeClass
	public static void setup(){
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();	
		em = JPAUtil.getCurrentEntityManager();
	}
	
	@AfterClass
	public static void tearDown(){
		em.close();
	}
	
	@Test
	public void test_findCustomersbyIdNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			Customer results = p.findCustomersById(3);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyId() {
		Customer exp_result = new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234");
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			Customer result = p.findCustomersById(1);
			assertEquals(exp_result, result);
		} catch(java.lang.RuntimeException message) {

		}
	}
	
	@Test
	public void test_findCustomersbyLastNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByLastName("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyLast() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByLastName("Papadopoulos");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyFirstNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByFirstName("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyFirst() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByFirstName("Maria");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyAFMNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByAFM("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyAFM() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByAFM("12345678");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyADTNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByADT("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyADT() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByADT("AS12345");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyTelNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByTel("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyTel() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByTel("2121212121");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyEmailNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByEmail("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyEmail() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByEmail("mp@gmail.com");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyInvestAmountNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByInvestAmount(1000);
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyInvestAmount() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByInvestAmount(12345);
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findCustomersbyBankAccountNoNull() {
		String exp_message = "NO CUSTOMER FOUND";
		PelatologioManagement p = new PelatologioManagement(em);
		try {
			p.findCustomerByBankAccountNo("SASDKLSA");
		} catch(java.lang.RuntimeException message) {
			assertEquals(exp_message, message.getMessage());
		}
	}
	
	@Test
	public void test_findCustomersbyBankAccountNo() {
		List<Customer> exp_result = new ArrayList<>();
		List<Customer> result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findCustomerByBankAccountNo("GE075 1234 1234 1234 1234");
		assertEquals(exp_result, result);
	}
	
	@Test
	public void test_findAllCustomer() {
		List<Customer> exp_result = new ArrayList<>();
		exp_result.add(new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		exp_result.add(new Customer(2, "AE12345", "123456789", "Marios","Papas", "2121212121", "msp@gmail.com", "16/07/1980",
    			12000, "GE075 5678 5678 5678 5678"));
		List<Customer> result = new ArrayList<>();
		PelatologioManagement p = new PelatologioManagement(em);
		result = p.findAllCustomer();
		assertEquals(exp_result,result);
	}
	
	@Test
	public void test_ShowCustomers() {
		String exp_results = "Id 1 FirstName  Maria LastName Papadopoulos ADT AS12345 AFM 12345678 EMAIL mp@gmail.com Telephone 2121212121BirthDate06/07/1980investAmoun12345.0bankAccountNoGE075 1234 1234 1234 1234\r\n" + 
				"Id 2 FirstName  Marios LastName Papas ADT AE12345 AFM 123456789 EMAIL msp@gmail.com Telephone 2121212121BirthDate16/07/1980investAmoun12000.0bankAccountNoGE075 5678 5678 5678 5678\r\n";
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();    
		System.setOut(new java.io.PrintStream(out)); 
		PelatologioManagement p = new PelatologioManagement(em);
		p.ShowCustomers();
		
		String results = out.toString();
		assertEquals(exp_results, results);
	}
}

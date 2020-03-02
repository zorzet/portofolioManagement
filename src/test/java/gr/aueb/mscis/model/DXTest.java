package gr.aueb.mscis.model;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.model.Metoxh;

public class DXTest {

	DX dx;
	
	@Before
    public void setUp() {
		dx = new DX();
    }
	
	@Test
	public void test_DXId() {
		int exp_DXId = 1;
		dx.setDXId(1);
		int DXId = dx.getDXId();
		assertEquals(exp_DXId, DXId);
	}

	@Test
	public void test_ADT() {
		String exp_ADT = "AH252687";
		dx.setADT("AH252687");
		String ADT = dx.getADT();
		assertEquals(exp_ADT, ADT);
	}
	
	@Test
	public void test_AFM() {
		String exp_AFM = "12345678";
		dx.setAFM("12345678");
		String AFM = dx.getAFM();
		assertEquals(exp_AFM, AFM);
	}
	
	@Test
	public void test_Name() {
		String exp_Name = "Maria";
		dx.setName("Maria");
		String Name = dx.getName();
		assertEquals(exp_Name, Name);		
	}
	
	@Test
	public void test_Surname() {
		String exp_Surname = "Papadopoulou";
		dx.setSurname("Papadopoulou");
		String Surname = dx.getSurname();
		assertEquals(exp_Surname, Surname);
	}
	
	@Test
	public void test_Tel() {
		String exp_Tel = "6999999999";
		dx.setTel("6999999999");
		String Tel = dx.getTel();
		assertEquals(exp_Tel, Tel);
	}
	
	@Test
	public void test_Email() {
		String exp_Email = "mpapadopoulou@gmail.com";
		dx.setEmail("mpapadopoulou@gmail.com");
		String Email = dx.getEmail();
		assertEquals(exp_Email, Email);
	}
	
	@Test
	public void test_BirthDate() {
		String exp_BirthDate = "26.05.1990";
		dx.setBirthDate("26.05.1990");
		String BirthDate = dx.getBirthDate();
		assertEquals(exp_BirthDate, BirthDate);
	}
	
	@Test
	public void test_Xartofulakia() {
		Set<Xartofulakio> exp_xartofulakia = new HashSet<>();
		Xartofulakio x1 = new Xartofulakio("created", new Customer(1, "AS12345", "12345678", "Maria","Papadopoulos", "2121212121", "mp@gmail.com", "06/07/1980",
    			12345, "GE075 1234 1234 1234 1234"));
		exp_xartofulakia.add(x1);
		Xartofulakio x2 = new Xartofulakio("created", new Customer(2, "AE12345", "123456789", "Marios","Papas", "2121212121", "msp@gmail.com", "16/07/1980",
    			12000, "GE075 5678 5678 5678 5678"));
		exp_xartofulakia.add(x2);
		dx.setXartofulakio(exp_xartofulakia);
		Set<Xartofulakio> xartofulakia = dx.getXartofulakio();
		assertEquals(exp_xartofulakia,xartofulakia);
	}
	
	@Test
	public void test_Equals() {
		DX exp_result = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		DX test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(null));
		assertTrue(test.equals(exp_result));

		test = null;
		test = new DX(null, "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252699", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", null, "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345699", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", null, "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria1", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", null, "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou1", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", null, "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999998", "mpapadopoulou@gmail.com", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", null, "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.gr", "26.05.1990");
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", null);
		assertFalse(test.equals(exp_result));
		
		test = null;
		test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1991");
		assertFalse(test.equals(exp_result));
	
	}
	
	@Test
	public void test_toString() {
		DX test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		String str = test.toString();
	}
	
	@Test
	public void test_DX() {
		String exp_ADT = "AH252687";
		String exp_AFM = "12345678";
		String exp_Name = "Maria";
		String exp_Surname = "Papadopoulou";
		String exp_Tel = "6999999999";
		String exp_Email = "mpapadopoulou@gmail.com";
		String exp_BirthDate = "26.05.1990";
		DX test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		String ADT = test.getADT();
		String AFM = test.getAFM();
		String Name = test.getName();
		String Surname = test.getSurname();
		String Tel = test.getTel();
		String Email = test.getEmail();
		String BirthDate = test.getBirthDate();
		assertEquals(exp_ADT, ADT);
		assertEquals(exp_AFM, AFM);
		assertEquals(exp_Name, Name);
		assertEquals(exp_Surname, Surname);
		assertEquals(exp_Tel, Tel);
		assertEquals(exp_Email, Email);
		assertEquals(exp_BirthDate, BirthDate);
	}
	
	@Test
	public void test_metoxh() {
		DX test = new DX("AH252687", "12345678", "Maria", "Papadopoulou", "6999999999", "mpapadopoulou@gmail.com", "26.05.1990");
		Metoxh m = new Metoxh("AEGN", "22/02/2020", 8.70, 8.60, 8.69, 0.6, 132000);
		test.addMetoxh(m);
		Set<Metoxh> metoxes = test.getMetoxh();
		Set<Metoxh> exp_metoxes = new HashSet<Metoxh>();
		exp_metoxes.add(m);
		assertEquals(exp_metoxes, metoxes);
		
		test.removeMetoxh(m);
		exp_metoxes.remove(m);
		assertEquals(exp_metoxes, metoxes);
		
	}
}

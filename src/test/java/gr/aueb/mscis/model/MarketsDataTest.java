package gr.aueb.mscis.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.*;
import gr.aueb.mscis.sample.model.MarketsData;

public class MarketsDataTest {

	MarketsData m;

	@Before
    public void setUp() {
		m = new MarketsData();
    }
    
	@Test
	public void test_Date() {
		String exp_date = "20.02.2020";
		m.setDate("20.02.2020");
		String date = m.getDate();
		assertEquals(exp_date, date);
	}
	
	@Test
	public void test_Opening() {
		double exp_opening = 3.123;
		m.setOpening(3.123);
		double opening = m.getOpening();
		assertEquals(exp_opening, opening, 0.000);
	}

	@Test
	public void test_Closing() {
		double exp_closing = 5.578;
		m.setClosing(5.578);
		double closing = m.getClosing();
		assertEquals(exp_closing, closing, 0.000);
	}

	@Test
	public void test_Max() {
		double exp_max = 8.234;
		m.setMax(8.234);
		double max = m.getMax();
		assertEquals(exp_max, max, 0.000);
	}
	
	@Test
	public void test_Min() {
		double exp_min = 1.987;
		m.setMin(1.987);
		double min = m.getMin();
		assertEquals(exp_min, min, 0.000);
	}
	
	@Test
	public void test_MarketsData() {
		String exp_date = "20.02.2020";
		double exp_opening = 3.123;
		double exp_closing = 5.578;
		double exp_max = 8.234;
		double exp_min = 1.987;
		MarketsData test = new MarketsData("20.02.2020", 3.123, 5.578, 8.234, 1.987);
		String date = test.getDate();
		double opening = test.getOpening();
		double closing = test.getClosing();
		double max = test.getMax();
		double min = test.getMin();
		assertEquals(exp_date, date);
		assertEquals(exp_opening, opening, 0.000);
		assertEquals(exp_closing, closing, 0.000);
		assertEquals(exp_max, max, 0.000);
		assertEquals(exp_min, min, 0.000);
	}
}
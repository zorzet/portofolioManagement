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
		float exp_opening = 3.123f;
		m.setOpening(3.123f);
		float opening = m.getOpening();
		assertEquals(exp_opening, opening, 0.0f);
	}

	@Test
	public void test_Closing() {
		float exp_closing = 5.578f;
		m.setClosing(5.578f);
		float closing = m.getClosing();
		assertEquals(exp_closing, closing, 0.0f);
	}

	@Test
	public void test_Max() {
		float exp_max = 8.234f;
		m.setMax(8.234f);
		float max = m.getMax();
		assertEquals(exp_max, max, 0.0f);
	}
	
	@Test
	public void test_Min() {
		float exp_min = 1.987f;
		m.setMin(1.987f);
		float min = m.getMin();
		assertEquals(exp_min, min, 0.0f);
	}
}

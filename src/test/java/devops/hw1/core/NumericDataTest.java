package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumericDataTest {
	long id;
	Long value;
	NumericData data;
	
	public NumericDataTest() {
		this.id = 2;
		this.value = Long.valueOf(2);
		this.data = new NumericData(this.id, this.value);
	}

	@Test
	public void testNumericData() {
		assertNotNull(this.data);
	}

	@Test
	public void testCompareToEqual() {
		NumericData other = new NumericData(this.id, this.value);
		assertEquals(this.data.compareTo(other), 0);
	}

	@Test
	public void testCompareToLesser() {
		NumericData other = new NumericData(this.id, this.value + 1);
		assertEquals(this.data.compareTo(other), -1);
	}

	@Test
	public void testCompareToGreater() {
		NumericData other = new NumericData(this.id, this.value - 1);
		assertEquals(this.data.compareTo(other), 1);
	}
}

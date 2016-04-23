package devops.hw1.core;

import static org.junit.Assert.*;
import org.junit.Test;

import back_end.AbstractData;
import back_end.IData;

public class AbstractDataTest {
	int id;
	Integer value;
	AbstractData<Integer> dataTestMock;
	
	private class IntegerDataMock extends AbstractData<Integer> {
		public IntegerDataMock(long id, Integer value) {
			super(id, value);
		}

		@Override
		public int compareTo(IData<Integer> o) {
			return 0;
		}		
	}
	
	private class DoubleDataMock extends AbstractData<Double> {
		public DoubleDataMock(long id, Double value) {
			super(id, value);
		}
		
		@Override
		public int compareTo(IData<Double> o) {
			return 0;
		}
	}

	public AbstractDataTest() {
		this.id = 1;
		this.value = Integer.valueOf(2);
		this.dataTestMock = new IntegerDataMock(this.id, this.value);	
	}

	@Test
	public void testHashCode() {
		final int prime = 31;
		int expected = 1;
		expected = prime * expected + (int)this.dataTestMock.getId();
		expected = prime * expected + this.dataTestMock.getValue();
		
		int actual = this.dataTestMock.hashCode();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testAbstractDataConstructor() {
		assertNotNull(this.dataTestMock);
	}

	@Test
	public void testGetId() {
		assertEquals(this.dataTestMock.getId(), this.id);
	}

	@Test
	public void testGetValue() {
		assertEquals(this.dataTestMock.getValue(), this.value);
	}

	@Test
	public void testEqualsObjectNull() {
		assertFalse(this.dataTestMock.equals(null));
	}

	@Test
	public void testEqualsObjectEqualRef() {
		assertTrue(this.dataTestMock.equals(this.dataTestMock));
	}

	@Test
	public void testEqualsObjectForUnEqualClass() {
		assertFalse(this.dataTestMock.equals(this.value));
	}

	@Test
	public void testEqualsObjectUnEqualValues() {
		AbstractData<Integer> otherTestMock1 = new IntegerDataMock(this.id, null);
		assertFalse(this.dataTestMock.equals(otherTestMock1));

		AbstractData<Integer> otherTestMock2 = new IntegerDataMock(2, this.value);
		assertFalse(this.dataTestMock.equals(otherTestMock2));
	}

	@Test
	public void testEqualsObjectEqualValue() {
		AbstractData<Integer> otherTestMock = new IntegerDataMock(this.id, this.value);
		assertTrue(this.dataTestMock.equals(otherTestMock));
	}
	
	@Test
	public void testNonNullObjectNullOther() {
		AbstractData<Integer> otherTestMock = new IntegerDataMock(this.id, null);
		try {
			assertFalse(this.dataTestMock.equals(otherTestMock));
		} catch (NullPointerException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testWrongTOtherObject() {
		AbstractData<Double> otherTestMock = new DoubleDataMock(this.id, (1.1*this.value));
		try {
			assertFalse(this.dataTestMock.equals(otherTestMock));
		} catch (ClassCastException e) {
			assertTrue(false);
		}
	}
}

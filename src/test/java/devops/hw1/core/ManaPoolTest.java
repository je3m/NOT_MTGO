package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ManaPoolTest {
	@Test
	public void testMana1(){
		assertEquals(ManaPool.RED1.getAmount(), 0);
	}
}

package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ManaPoolTest {
	@Test
	public void testMana1(){
		assertEquals(ManaPool.RED1.getAmount(), 0);
	}
	
	@Test
	public void testMana2(){
		ManaPool.RED1.add(2);
		assertEquals(ManaPool.RED1.getAmount(), 2);
	}
	
	@Test
	public void testMana3(){
		ManaPool.RED1.add(2);
		assertEquals(ManaPool.RED1.getAmount(), 4);
	}
	
	@Test
	public void testMana4(){
		ManaPool.RED1.remove(2);
		assertEquals(ManaPool.RED1.getAmount(), 2);
	}
}

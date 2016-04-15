package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

	@Test
	public void testMana5(){
		ManaPool.RED1.empty();
		assertEquals(ManaPool.RED1.getAmount(), 0);
	}

	@Test
	public void testMana6(){
		for(ManaPool m: ManaPool.values()){
			m.add(1);
		}
		ManaPool.emptyMana();
		for(ManaPool m: ManaPool.values()){
			assertEquals(m.getAmount(), 0);
		}
	}

	@Test
	public void testChoosePool(){
		assertEquals(ManaPool.WHITE1, ManaPool.getPool('w', true));
		assertEquals(ManaPool.WHITE2, ManaPool.getPool('w', false));
		assertEquals(ManaPool.RED2, ManaPool.getPool('r', false));


		try{
			ManaPool.getPool('o', true);
			fail("no exception thrown");
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "invalid color 'o'");
		}

		assertEquals(ManaPool.RED2, ManaPool.getPool('R', false));
		assertEquals(ManaPool.COLORLESS2, ManaPool.getPool('c', false));
	}

	@Test
	public void testMana7(){
		ManaPool.emptyMana();
		for(ManaPool m: ManaPool.values()){
			assertEquals(m.getAmount(), 0);
		}
	}
}

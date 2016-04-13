package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

public class ItemOnStackTest {
	@Test
	public void testGetCardAndPlayer1(){
		Card c = EasyMock.niceMock(Card.class);
		ItemOnStack item = new ItemOnStack(c,false);
		assertEquals(false,item.getPlayer());
		assertEquals(c, item.getCard());
	}
	
	@Test
	public void testGetCardAndPlayer2(){
		Card c = EasyMock.niceMock(Card.class);
		ItemOnStack item = new ItemOnStack(c,true);
		assertEquals(true,item.getPlayer());
		assertEquals(c, item.getCard());
	}
	
	@Test
	public void testGetCardAndPlayer3(){
		
		ItemOnStack item = new ItemOnStack(null,null);
		assertEquals(null,item.getPlayer());
		assertEquals(null, item.getCard());
	}
}

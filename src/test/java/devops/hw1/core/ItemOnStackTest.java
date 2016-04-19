package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ItemOnStackTest {
	
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[3][3];
		
		Card c1 = new Card("oogleyboogley");//EasyMock.niceMock(Card.class);
		ItemOnStack item1 = new ItemOnStack(c1,false);
		
		data[0][0] = item1;
		data[0][1] = false;
		data[0][2] = c1;
		
		
		Card c2 = new Card("aaaaaah!");//EasyMock.niceMock(Card.class);
		ItemOnStack item2 = new ItemOnStack(c2,true);
		
		data[1][0] = item2;
		data[1][1] = true;
		data[1][2] = c2;
		
		ItemOnStack item3 = new ItemOnStack(null, null);
		
		data[2][0] = item3;
		data[2][1] = null;
		data[2][2] = null;
		
		return Arrays.asList(data);
	}
	
	
	
	private ItemOnStack tInputItemOnStack;
	
	
	private Boolean tExpectedPlayer;
	
	
	private Card tExpectedCard;
	
	
	public ItemOnStackTest(ItemOnStack InputItemOnStack, Boolean ExpectedPlayer, Card ExpectedCard) {
		tInputItemOnStack = InputItemOnStack;
		tExpectedPlayer = ExpectedPlayer;
		tExpectedCard = ExpectedCard;
	}
	
	@Test
	public void test() {
		assertEquals(tInputItemOnStack.getPlayer(), tExpectedPlayer);
		assertEquals(tInputItemOnStack.getCard(), tExpectedCard);
	}
	
}

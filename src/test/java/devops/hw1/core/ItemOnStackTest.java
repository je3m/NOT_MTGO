package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ItemOnStackTest {
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[5][5];
		
		Card c1 = EasyMock.niceMock(Card.class);
		Card ct1 = EasyMock.niceMock(Card.class);
		Zone zt1 = Zone.HAND;
		ItemOnStack item1 = new ItemOnStack(c1,false, ct1, zt1);
		
		data[0][0] = item1;
		data[0][1] = false;
		data[0][2] = c1;
		data[0][3] = ct1;
		data[0][4] = zt1;
		
		
		
		Card c2 = EasyMock.niceMock(Card.class);
		Card ct2 = EasyMock.niceMock(Card.class);
		Zone zt2 = Zone.BATTLE_FIELD;
		ItemOnStack item2 = new ItemOnStack(c2,true, ct2, zt2);
		
		data[1][0] = item2;
		data[1][1] = true;
		data[1][2] = c2;
		data[1][3] = ct2;
		data[1][4] = zt2;
		
		Card ct3 = EasyMock.niceMock(Card.class);
		Zone zt3 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item3 = new ItemOnStack(null, null, ct3, zt3);
		
		data[2][0] = item3;
		data[2][1] = null;
		data[2][2] = null;
		data[2][3] = ct3;
		data[2][4] = zt3;
		
		Card c4 = EasyMock.niceMock(Card.class);
		ItemOnStack item4 = new ItemOnStack(c4,true, null, null);
		
		data[3][0] = item4;
		data[3][1] = true;
		data[3][2] = c4;
		data[3][3] = null;
		data[3][4] = null;
		
		
		ItemOnStack item5 = new ItemOnStack(null, null, null, null);
		
		data[4][0] = item5;
		data[4][1] = null;
		data[4][2] = null;
		data[4][3] = null;
		data[4][4] = null;
		
		
		return Arrays.asList(data);
	}
	
	
	
	private ItemOnStack tInputItemOnStack;
	
	
	private Boolean tExpectedPlayer;
	
	
	private Card tExpectedCard;
	
	private Card tExpectedTargetCard;
	
	private Zone tExpectedTargetZone;
	
	
	public ItemOnStackTest(ItemOnStack InputItemOnStack, Boolean ExpectedPlayer, Card ExpectedCard, Card ExpectedTargetCard, Zone ExpectedTargetZone) {
		tInputItemOnStack = InputItemOnStack;
		tExpectedPlayer = ExpectedPlayer;
		tExpectedCard = ExpectedCard;
		tExpectedTargetCard = ExpectedTargetCard;
		tExpectedTargetZone = ExpectedTargetZone;
	}
	
	@Test
	public void test() {
		assertEquals(tInputItemOnStack.getPlayer(), tExpectedPlayer);
		assertEquals(tInputItemOnStack.getCard(), tExpectedCard);
		assertEquals(tInputItemOnStack.getTarget(), tExpectedTargetCard);
		assertEquals(tInputItemOnStack.getTargetZone(), tExpectedTargetZone);
		
	}
	
}

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
		Object[][] data = new Object[9][5];
		
		Card c1 = EasyMock.niceMock(Card.class);
		Card ct1 = EasyMock.niceMock(Card.class);
		Zone zt1 = Zone.HAND;
		ItemOnStack item1 = new ItemOnStack(c1,false, ct1, zt1);
		
		data[0][0] = item1;
		data[0][1] = c1;
		data[0][2] = false;
		data[0][3] = ct1;
		data[0][4] = zt1;
		
		Card c2 = EasyMock.niceMock(Card.class);
		Card ct2 = EasyMock.niceMock(Card.class);
		Zone zt2 = Zone.BATTLE_FIELD;
		ItemOnStack item2 = new ItemOnStack(c2,true, ct2, zt2);
		
		data[1][0] = item2;
		data[1][1] = c2;
		data[1][2] = true;
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
		data[3][1] = c4;
		data[3][2] = true;
		data[3][3] = null;
		data[3][4] = null;
		
		
		ItemOnStack item5 = new ItemOnStack(null, null, null, null);
		
		data[4][0] = item5;
		data[4][1] = null;
		data[4][2] = null;
		data[4][3] = null;
		data[4][4] = null;
		
		Card ct6 = EasyMock.niceMock(Card.class);
		Zone zt6 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item6 = new ItemOnStack(null, true, ct6, zt6);
		
		data[5][0] = item6;
		data[5][1] = null;
		data[5][2] = true;
		data[5][3] = ct6;
		data[5][4] = zt6;
		
		Card c7 = EasyMock.niceMock(Card.class);
		Card ct7 = EasyMock.niceMock(Card.class);
		Zone zt7 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item7 = new ItemOnStack(c7, null, ct7, zt7);
		
		data[6][0] = item7;
		data[6][1] = c7;
		data[6][2] = null;
		data[6][3] = ct7;
		data[6][4] = zt7;
		
		Card c8 = EasyMock.niceMock(Card.class);
		Zone zt8 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item8 = new ItemOnStack(c8, true, null, zt8);
		
		data[7][0] = item8;
		data[7][1] = c8;
		data[7][2] = true;
		data[7][3] = null;
		data[7][4] = zt8;
		
		Card c9 = EasyMock.niceMock(Card.class);
		Card ct9 = EasyMock.niceMock(Card.class);
		
		ItemOnStack item9 = new ItemOnStack(c9, true, ct9, null);
		
		data[8][0] = item9;
		data[8][1] = c9;
		data[8][2] = true;
		data[8][3] = ct9;
		data[8][4] = null;
		
		
		return Arrays.asList(data);
	}
	
	
	
	private ItemOnStack tInputItemOnStack;
	
	
	private Boolean tExpectedPlayer;
	
	
	private Card tExpectedCard;
	
	private Card tExpectedTargetCard;
	
	private Zone tExpectedTargetZone;
	
	
	public ItemOnStackTest(ItemOnStack InputItemOnStack, Card ExpectedCard, Boolean ExpectedPlayer, Card ExpectedTargetCard, Zone ExpectedTargetZone) {
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

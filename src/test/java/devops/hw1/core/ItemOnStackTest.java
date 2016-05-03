package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import back_end.Ability;
import back_end.Card;
import back_end.ItemOnStack;
import back_end.Zone;

@RunWith(Parameterized.class)
public class ItemOnStackTest {
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[9][7];
		
		Card c1 = EasyMock.niceMock(Card.class);
		Ability a1 = EasyMock.niceMock(Ability.class);
		Card ct1 = EasyMock.niceMock(Card.class);
		Zone zt1 = Zone.HAND;
		ItemOnStack item1 = new ItemOnStack(c1,a1,false, ct1, false, zt1);
		
		data[0][0] = item1;
		data[0][1] = c1;
		data[0][2] = a1;
		data[0][3] = false;
		data[0][4] = ct1;
		data[0][5] = false;
		data[0][6] = zt1;
		
		Card c2 = EasyMock.niceMock(Card.class);
		Ability a2 = EasyMock.niceMock(Ability.class);
		Card ct2 = EasyMock.niceMock(Card.class);
		Zone zt2 = Zone.BATTLE_FIELD;
		ItemOnStack item2 = new ItemOnStack(c2,a2,true, ct2, false, zt2);
		
		data[1][0] = item2;
		data[1][1] = c2;
		data[1][2] = a2;
		data[1][3] = true;
		data[1][4] = ct2;
		data[1][5] = false;
		data[1][6] = zt2;
		
		Ability a3 = EasyMock.niceMock(Ability.class);
		Card ct3 = EasyMock.niceMock(Card.class);
		Zone zt3 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item3 = new ItemOnStack(null,a3, null, ct3, false, zt3);
		
		data[2][0] = item3;
		data[2][1] = null;
		data[2][2] = a3;
		data[2][3] = null;
		data[2][4] = ct3;
		data[2][5] = false;
		data[2][6] = zt3;
		
		Card c4 = EasyMock.niceMock(Card.class);
		Ability a4 = EasyMock.niceMock(Ability.class);
		ItemOnStack item4 = new ItemOnStack(c4,a4,true, null,false, null);
		
		data[3][0] = item4;
		data[3][1] = c4;
		data[3][2] = a4;
		data[3][3] = true;
		data[3][4] = null;
		data[3][5] = false;
		data[3][6] = null;
		
		
		ItemOnStack item5 = new ItemOnStack(null, null, null, null, null, null);
		
		data[4][0] = item5;
		data[4][1] = null;
		data[4][2] = null;
		data[4][3] = null;
		data[4][4] = null;
		data[4][5] = null;
		data[4][6] = null;
		
		Ability a6 = EasyMock.niceMock(Ability.class);
		Card ct6 = EasyMock.niceMock(Card.class);
		Zone zt6 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item6 = new ItemOnStack(null,a6, true, ct6,false, zt6);
		
		data[5][0] = item6;
		data[5][1] = null;
		data[5][2] = a6;
		data[5][3] = true;
		data[5][4] = ct6;
		data[5][5] = false;
		data[5][6] = zt6;
		
		Card c7 = EasyMock.niceMock(Card.class);
		Ability a7 = EasyMock.niceMock(Ability.class);
		Card ct7 = EasyMock.niceMock(Card.class);
		Zone zt7 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item7 = new ItemOnStack(c7,a7, null, ct7, false, zt7);
		
		data[6][0] = item7;
		data[6][1] = c7;
		data[6][2] = a7;
		data[6][3] = null;
		data[6][4] = ct7;
		data[6][5] = false;
		data[6][6] = zt7;
		
		Card c8 = EasyMock.niceMock(Card.class);
		Ability a8 = EasyMock.niceMock(Ability.class);
		Zone zt8 = Zone.BATTLE_FIELD1;
		
		ItemOnStack item8 = new ItemOnStack(c8,a8, true, null,false, zt8);
		
		data[7][0] = item8;
		data[7][1] = c8;
		data[7][2] = a8;
		data[7][3] = true;
		data[7][4] = null;
		data[7][5] = false;
		data[7][6] = zt8;
		
		Card c9 = EasyMock.niceMock(Card.class);
		Ability a9 = EasyMock.niceMock(Ability.class);
		Card ct9 = EasyMock.niceMock(Card.class);
		
		ItemOnStack item9 = new ItemOnStack(c9,a9, true, ct9, false, null);
		
		data[8][0] = item9;
		data[8][1] = c9;
		data[8][2] = a9;
		data[8][3] = true;
		data[8][4] = ct9;
		data[8][5] = false;
		data[8][6] = null;
		
		
		return Arrays.asList(data);
	}
	//TODO: Add more extensive tests
	
	
	private ItemOnStack tInputItemOnStack;
	
	private Card tExpectedCard;
	
	private Ability tExpectedAbility;
	
	private Boolean tExpectedPlayer;
	
	private Card tExpectedTargetCard;
	
	private Boolean tExpectedTargetPlayer;
	
	private Zone tExpectedTargetZone;
	
	
	public ItemOnStackTest(ItemOnStack InputItemOnStack, Card ExpectedCard, Ability ExpectedAbility, Boolean ExpectedPlayer, Card ExpectedTargetCard, Boolean ExpectedTargetPlayer, Zone ExpectedTargetZone) {
		tInputItemOnStack = InputItemOnStack;
		tExpectedPlayer = ExpectedPlayer;
		tExpectedAbility = ExpectedAbility;
		tExpectedCard = ExpectedCard;
		tExpectedTargetCard = ExpectedTargetCard;
		tExpectedTargetPlayer = ExpectedTargetPlayer;
		tExpectedTargetZone = ExpectedTargetZone;
	}
	
	@Test
	public void test() {
		assertEquals(tInputItemOnStack.getPlayer(), tExpectedPlayer);
		assertEquals(tInputItemOnStack.getAbility(), tExpectedAbility);
		assertEquals(tInputItemOnStack.getCard(), tExpectedCard);
		assertEquals(tInputItemOnStack.getTarget(), tExpectedTargetCard);
		assertEquals(tInputItemOnStack.getTargetPlayer(), tExpectedTargetPlayer);
		assertEquals(tInputItemOnStack.getTargetZone(), tExpectedTargetZone);
	}
	
}

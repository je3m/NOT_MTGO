package devops.hw1.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import back_end.Backend;
import back_end.Card;
import back_end.Zone;

@RunWith(Parameterized.class)
public class CanTargetTest {

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[6][5];
		
		
		data[0][0] = new Card("Vindicate", "", "", "Sorcery", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[0][1] = Zone.HAND;
		data[0][2] = new Card("Mountain", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[0][3] = Zone.BATTLE_FIELD1;
		data[0][4] = true;
		
		data[1][0] = new Card("Vindicate", "", "", "Sorcery", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[1][1] = Zone.HAND;
		data[1][2] = new Card("Forest", "", "", "Basic Land- Forest", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[1][3] = Zone.BATTLE_FIELD;
		data[1][4] = true;
		
		data[2][0] = new Card("Vindicate", "", "", "Sorcery", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[2][1] = Zone.HAND1;
		data[2][2] = new Card("Forest", "", "", "Basic Land- Forest", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[2][3] = Zone.BATTLE_FIELD;
		data[2][4] = true;
		
		data[3][0] = new Card("Vindicate", "", "", "Sorcery", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[3][1] = Zone.HAND1;
		data[3][2] = new Card("Mountain", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[3][3] = Zone.BATTLE_FIELD1;
		data[3][4] = true;
		
		data[4][0] = new Card("Vindicate", "", "", "Sorcery", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[4][1] = Zone.HAND;
		data[4][2] = new Card("Mountain", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[4][3] = Zone.HAND1;
		data[4][4] = false;
		
		data[5][0] = new Card("Vindicate", "", "", "Sorcery", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[5][1] = Zone.HAND1;
		data[5][2] = new Card("Forest", "", "", "Basic Land- Forest", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		data[5][3] = Zone.HAND;
		data[5][4] = false;
		
		
		
		
		return Arrays.asList(data);
	}
	
	private Card tInputSource;
	private Zone tInputSourceZone;
	
	private Card tInputTarget;
	private Zone tInputTargetZone;
	
	private Boolean tExpectedCanTarget;
	
	
	
	public CanTargetTest(Card inputSource, Zone inputSourceZone, Card inputTarget, Zone inputTargetZone, Boolean expectedCanTarget) {
		tInputSource = inputSource;
		tInputSourceZone = inputSourceZone;
		tInputTarget = inputTarget;
		tInputTargetZone = inputTargetZone;
		tExpectedCanTarget = expectedCanTarget;
	}
	
	
	
	
	@Test
	public void test() {
		
		
		assertEquals(Backend.canTarget(tInputSource, tInputSourceZone, tInputTarget, tInputTargetZone), 
				tExpectedCanTarget);
	}

}

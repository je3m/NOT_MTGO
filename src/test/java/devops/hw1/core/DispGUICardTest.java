package devops.hw1.core;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Test;

public class DispGUICardTest {

	@Test
	public void testConstructorIndex() {
		try {
			new DispGUICard(new Rectangle(100, 100), new Card("Storm Crow", "", "", "Creature- Bird", "", 
					new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), 10000, Zone.HAND);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "DispGUICard Storm Crow: 10000 is not a valid index for zone HAND");
		}
	}

}

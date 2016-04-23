package devops.hw1.core;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import back_end.Card;
import back_end.Zone;
import front_end.DispGUICard;

public class DispGUICardTest {

	@Test
	public void testConstructorIndex() {
		try {
			new DispGUICard(new Rectangle(100, 100), new Card("Storm Crow"), 10000, Zone.HAND);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "DispGUICard Storm Crow: 10000 is not a valid index for zone HAND");
		}
	}

}

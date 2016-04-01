package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class MTGComponentTest {

	@Test
	public void testConstructorWindowBounds() {
		
		try {
			MTGComponent comp = new MTGComponent(-10, 10);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -10 is not a valid window width");
		}
		
		try {
			MTGComponent comp = new MTGComponent(10, -10);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -10 is not a valid window height");
		}
		
		
	}
	
	@Test
	public void testSetWindowX() {
		MTGComponent comp = new MTGComponent(10, 10);
		
		try {
			comp.setWindowX(-1);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -1 is not a valid window width");
		}
	}

}

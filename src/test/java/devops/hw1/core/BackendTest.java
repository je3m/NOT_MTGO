package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BackendTest {

	@Test
	public void makeCardGetName(){
		Card c = new Card("Storm crow");
		assertEquals(c.getName(), "Storm crow");
	}

	@Test
	public void testAddCardAndGetContents() {

	}

	@Test
	public void testGetZoneContents(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow");
		bknd.addCard(Zone.HAND, c, 0);
		Card z = (bknd.getZoneContents(Zone.HAND)[0]);
		assertEquals(c, z);
	}
}

package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import back_end.AbilityType;

public class TestAbilityType {

	@Test
	public void test() {
		assertEquals(AbilityType.MANA, AbilityType.getTypeFromString("MANA"));
		
		assertEquals(null, AbilityType.getTypeFromString("STATED"));
	}
	
	
	
	@Test
	public void testNullType() {
		try {
			AbilityType.getTypeFromString(null);
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Ability type string cannot be null.");
		}
	}

}

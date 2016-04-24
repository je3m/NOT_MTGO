package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import back_end.AbilityType;

public class TestAbilityType {

	@Test
	public void test() {
		assertEquals(AbilityType.MANA, AbilityType.getTypeFromString("MANA"));

	}

}

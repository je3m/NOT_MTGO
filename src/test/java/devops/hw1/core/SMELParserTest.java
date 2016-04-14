package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class SMELParserTest {

	@Test
	public void testGetCost() {
		String smel = "COST {7U}";
		SMELParser parse = new SMELParser(smel);
		
		assertEquals(parse.getCost(), "7U");
		
		smel = "COST {B}";
		parse = new SMELParser(smel);
		
		assertEquals("B", parse.getCost());
	}

}

package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class SMELParserTest {

	@Test
	public void testGetCost() {
		String smel = "COST {7U}";
		SMELParser parse = new SMELParser(smel);
		
		assert(parse.getCost().equals("7U"));
	}

}

package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import back_end.SMELParser;

public class SMELParserTest {

	@Test
	public void testGetCost() {
		String smel = "COST {7U}";
		SMELParser parse = new SMELParser(smel);

		assertEquals(parse.getCost(), "7U");

		smel = "COST {B}";
		parse = new SMELParser(smel);

		assertEquals("B", parse.getCost());

		smel = "";
		parse = new SMELParser(smel);

		try{
			parse.getCost();
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}


		try{
			parse = new SMELParser(null);
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}
	}

	@Test
	public void testCostEfectParse(){
		String LlanowarElves = "COST { TAP } EFFECT { MANA G}";
		SMELParser parse = new SMELParser(LlanowarElves);

		assertEquals("TAP", parse.getCost());
		assertEquals("MANAG", parse.getEffect());



	}

}

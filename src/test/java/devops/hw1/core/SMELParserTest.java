package devops.hw1.core;

import static org.junit.Assert.*;

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
		
		parse = new SMELParser(null);
		try{
			parse.getCost();
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}
		
		
		
	}

}

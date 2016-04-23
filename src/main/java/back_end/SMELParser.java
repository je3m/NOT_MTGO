package back_end;

public class SMELParser {

	String cost;
	
	public SMELParser(String smel) {
		if (smel == "" || smel == null)
			return;
		
		int start = smel.indexOf('{') + 1;
		int end = smel.indexOf('}');
		this.cost = (String) smel.subSequence(start, end);
		

	}

	public String getCost() {
		if (this.cost == null)
			throw new RuntimeException("SMEL: no cost");
		
		return this.cost;
	}

}

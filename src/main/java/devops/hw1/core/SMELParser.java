package devops.hw1.core;

public class SMELParser {

	String cost;
	
	public SMELParser(String smel) {
		int start = smel.indexOf('{') + 1;
		int end = smel.indexOf('}');
		this.cost = (String) smel.subSequence(start, end);
		

	}

	public String getCost() {
		
		return this.cost;
	}

}

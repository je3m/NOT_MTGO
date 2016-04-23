package back_end;

public class SMELParser {

	private String cost;
	public String effect;

	public SMELParser(String smel) {
		if (smel == null)
			throw new IllegalArgumentException("SMEL: no cost");

		smel = smel.replaceAll("\\s+", "");

		String[] arr = smel.split("(?:(\\{|\\}))");

		for(int i = 0; i < arr.length; i++){
			if(arr[i].equals("COST")){
				this.cost = arr[i+1];
				i++;
			} else if (arr[i].equals("EFFECT")){
				this.effect = arr[i+1];
				i++;
			}
		}




	}

	public String getCost() {
		if (this.cost == null)
			throw new RuntimeException("SMEL: no cost");

		return this.cost;
	}

	public String getEffect() {
		// TODO Auto-generated method stub
		return this.effect;
	}

}

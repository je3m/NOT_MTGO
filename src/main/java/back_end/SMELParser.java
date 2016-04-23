package back_end;

public class SMELParser {

	private String cost;
	private String effect;
	private String target;
	private Zone zone;
	private Zone resolveZone;

	public SMELParser(String smel) {
		if (smel == null)
			throw new IllegalArgumentException("SMEL: no cost");

		smel = smel.replaceAll("\\s+", "");

		String[] arr = smel.split("(?:(\\{|\\}))");

		for(int i = 0; i < arr.length; i++){
			switch(arr[i]){
			case "COST":
				this.cost = arr[++i];
				break;
			case "EFFECT":
				this.effect = arr[++i];
				break;
			case "TARGET":
				this.target = arr[++i];
				break;

			}
		}
	}

	public String getTarget(){
		return this.target;
	}

	public Zone getZone(){
		return this.zone;
	}

	public Zone getResolveZone(){
		return this.resolveZone;
	}

	public String getCost() {
		if (this.cost == null)
			throw new RuntimeException("SMEL: no cost");

		return this.cost;
	}

	public String getEffect() {
		return this.effect;
	}
}

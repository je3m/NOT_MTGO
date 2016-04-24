package back_end;

public class Ability {

	private String cost;
	private String effect;
	private String target;
	private Zone zone;
	private Zone resolveZone;
	private AbilityType type;
	private String displayText;

	//TODO: support mixed case, bad syntax, bad info
	public Ability(String smel) {
		if (smel == null)
			throw new IllegalArgumentException("SMEL: no cost");

		smel = smel.replaceAll("\\s+", "");

		String[] arr = smel.split("(?:(\\{|\\}))"); //split on { or }

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
			case "ZONE":
				i++;
				this.zone = Zone.getZoneFromString(arr[i]);
				break;
			case "RESOLVE":
				i++;
				this.resolveZone = Zone.getZoneFromString(arr[i]);
				break;
			case "TYPE":
				i++;
				this.type = AbilityType.getTypeFromString(arr[i]);
				break;
			case "TEXT":
				this.displayText = arr[++i];
				break;

			}
		}

		if((this.type == AbilityType.CAST) && (this.displayText == null)){
			this.displayText = "Cast";
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

	public AbilityType getType() {

		return this.type;
	}

	public String getText() {
		return this.displayText;
	}
}

package back_end;

/**
 * Represents the types of abilities that a card can have
 * @author jim
 */
public enum AbilityType {
	ACTIVATED,
	TRIGGERED,
	STATIC,
	ETB,
	MANA,
	CAST,
	PLAY;

	public static AbilityType getTypeFromString(String s){
		if(s == null) {
			throw new IllegalArgumentException("Ability type string cannot be null.");
		}
		
		for (AbilityType z: AbilityType.values()){
			if (s.equals(z.name())){
				return z;
			}
		}
		return null;

	}
}

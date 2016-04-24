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
	CAST;

	public static AbilityType getTypeFromString(String s){
		//TODO: invalid input
		for (AbilityType z: AbilityType.values()){
			if (s.equals(z.name())){
				return z;
			}
		}
		return null;

	}
}

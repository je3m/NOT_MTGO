package back_end;
/**
 * This enum represents the health of the players
 *
 */
public enum Health {
	HEALTH1,
	HEALTH2;
	
	int amount;
	
	/**
	 * Sets the health
	 * @param value new health value
	 */
	public void set(int value){
		this.amount = value;
	}

	/**
	 * Gets the health
	 * @return the current health
	 */
	public int get(){
		return this.amount;
	}
}
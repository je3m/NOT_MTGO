package back_end;
/**
 * This enum represents the health of the players
 *
 */
public enum Health {
	HEALTH,
	HEALTH1;
	
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

	/**
	 * Adds amount to the health
	 * @param amount amount to add
	 */
	public void add(int amount) {
		this.amount += amount;
	}

	/**
	 * Removed amount from the health
	 * @param amount amount to remove
	 */
	public void remove(int amount) {
		this.amount -= amount;
	}
}

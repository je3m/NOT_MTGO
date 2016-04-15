package devops.hw1.core;

/**
 * Represents the mana pool for both players
 *
 */
public enum ManaPool {
	WHITE1,
	BLUE1,
	BLACK1,
	RED1,
	GREEN1,
	COLORLESS1,
	WHITE2,
	BLUE2,
	BLACK2,
	RED2,
	GREEN2,
	COLORLESS2;

	int amount;
	/**
	 * Returns the total amount of mana in the pool
	 * @return amount of mana in the pool
	 */
	public int getAmount() {
		return this.amount;
	}

	/**
	 * Adds mana to the mana pool
	 * @param i amount of mana to be added
	 */
	public void add(int i) {
		this.amount = this.amount + i;
	}

	/**
	 * Removes mana from the mana pool
	 * @param i amount of mana to be removed
	 */
	public void remove(int i) {
		this.amount = this.amount - i;
	}

	/**
	 * Empty the mana pool
	 */
	public void empty() {
		this.amount = 0;
	}

	/**
	 * Empties the mana from all mana pools
	 */
	public static void emptyMana(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		ManaPool.WHITE2.empty();
		ManaPool.BLUE2.empty();
		ManaPool.BLACK2.empty();
		ManaPool.RED2.empty();
		ManaPool.GREEN2.empty();
		ManaPool.COLORLESS2.empty();
	}

	/**
	 * easy way to select a mana pool to prevent duplicate code
	 * @param color color of mana pool (wubrgc)
	 * @param player True if player 1, false otherwise
	 * @return ManaPool of player
	 */
	public static ManaPool getPool(char color, boolean player){
		switch(Character.toLowerCase(color)){
		case 'w':
			if (player)
				return ManaPool.WHITE1;

			return ManaPool.WHITE2;

		case 'u':
			if (player)
				return ManaPool.BLUE1;

			return ManaPool.BLUE2;

		case 'b':
			if (player)
				return ManaPool.BLACK1;

			return ManaPool.BLACK2;

		case 'r':
			if (player)
				return ManaPool.RED1;

			return ManaPool.RED2;
		case 'g':
			if (player)
				return ManaPool.GREEN1;

			return ManaPool.GREEN2;

		default:
			throw new IllegalArgumentException("invalid color '" + color + "'");

		}
	}
}

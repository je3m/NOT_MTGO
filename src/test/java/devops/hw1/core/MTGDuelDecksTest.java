package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MTGDuelDecksTest {
	@Test
	public void testPhase1(){
		MTGDuelDecks game = new MTGDuelDecks();
		assertEquals(game.getPhase(), Phase.UNTAP1);
	}
	
	@Test
	public void testPhase2(){
		MTGDuelDecks game = new MTGDuelDecks();
		game.changePhase();
		assertEquals(game.getPhase(), Phase.UPKEEP1);
	}
}

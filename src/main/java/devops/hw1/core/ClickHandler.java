package devops.hw1.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Handles clicking for the game
 * @author malinocr
 *
 */
public class ClickHandler implements MouseListener {
	MTGComponent MTGComp;
	
	
	/**
	 * Creates a clickhandler object for a given component
	 * @param MTGComp the component where the clicks will occur
	 */
	public ClickHandler(MTGComponent MTGComp) {
		this.MTGComp = MTGComp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		handleClickCard(e);
	}
	//!# following two methods need to be refactored so that they figure out the card that was clicked, pass that information on to the game logic engine and then implement at the gui level the changes mandated by the game logic engine 
	/**
	 * Handles clicking for displayed cards
	 * @param e the click event
	 */
	public void handleClickCard(MouseEvent e){
		checkZoneForCardClick(MTGComp.getHandGUICards1(), Zone.HAND);
		checkZoneForCardClick(MTGComp.getHandGUICards2(), Zone.HAND1);
		checkZoneForCardClick(MTGComp.getBattleGUICards1(), Zone.BATTLE_FIELD);
		checkZoneForCardClick(MTGComp.getBattleGUICards2(), Zone.BATTLE_FIELD1);
		checkDispCardClick1();
		checkDispCardClick2();
	}

	/**
	 * Handles click checking for the first players GUI card
	 */
	private void checkDispCardClick1() {
		if(MTGComp.getDispGUICard1() != null){
			if(MTGComp.getDispGUICard1().getRec().contains(MTGComp.getMousePosition())){
				MTGComp.setDispGUICard1(null);
				MTGComp.repaint();
			}
		}
	}
	
	/**
	 * Handles click checking for the second players GUI card
	 */
	private void checkDispCardClick2() {
		if(MTGComp.getDispGUICard2() != null){
			if(MTGComp.getDispGUICard2().getRec().contains(MTGComp.getMousePosition())){
				MTGComp.setDispGUICard2(null);
				MTGComp.repaint();
			}
		}
	}

	/**
	 * Checks to see if any card in an array list is clicked and tells the backend if something is clicked 
	 * @param e the click event
	 * @param cardAL the array list containing all cards to be checked
	 * @param start starting zone of the cards in the arraylist
	 */
	public void checkZoneForCardClick(ArrayList<GUICard> cardAL, Zone start){
		for(int i = 0; i < cardAL.size(); i++){
			if(cardAL.get(i).getRec().contains(MTGComp.getMousePosition())){
				if(start == Zone.HAND || start == Zone.BATTLE_FIELD){
					MTGComp.generateDispGUICard1(cardAL.get(i).getCard());
				} else if (start == Zone.HAND1 || start == Zone.BATTLE_FIELD1){
					MTGComp.generateDispGUICard2(cardAL.get(i).getCard());
				}
				MTGComp.repaint();
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}

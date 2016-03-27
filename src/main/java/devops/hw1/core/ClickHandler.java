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
		checkZoneForCardClick(e, MTGComp.getHandGUICards1(), Zone.HAND);
		checkZoneForCardClick(e, MTGComp.getHandGUICards2(), Zone.HAND1);
		checkZoneForCardClick(e, MTGComp.getBattleGUICards1(), Zone.BATTLE_FIELD);
		checkZoneForCardClick(e, MTGComp.getBattleGUICards2(), Zone.BATTLE_FIELD1);
	}

	/**
	 * Checks to see if any card in an array list is clicked, moving that card from its current zone to another zone if it is clicked 
	 * @param e the click event
	 * @param cardAL the array list containing all cards to be checked
	 * @param start starting zone of the cards in the arraylist
	 * @param end zone to move clicked cards into
	 */
	public void checkZoneForCardClick(MouseEvent e, ArrayList<GUICard> cardAL, Zone start){
		for(int i = 0; i < cardAL.size(); i++){
			if(cardAL.get(i).getRec().contains(e.getPoint())){
				Backend.handleCardClicked(start, i, cardAL.get(i).getCard());
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

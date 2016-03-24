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
	 * @param MTGComp the component where the clicks whill occur
	 */
	public ClickHandler(MTGComponent MTGComp) {
		this.MTGComp = MTGComp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		handleClickCard(e);
	}
	
	/**
	 * Handles clicking for displayed cards
	 * @param e the click event
	 */
	public void handleClickCard(MouseEvent e){
		handleSpecificZoneCardClick(e, MTGComp.getHandGUICards1(), Zone.HAND, Zone.BATTLE_FIELD);
		handleSpecificZoneCardClick(e, MTGComp.getHandGUICards2(), Zone.HAND1, Zone.BATTLE_FIELD1);
	}

	/**
	 * Checks to see if any card in an array list is clicked, moving that card from its current zone to another zone if it is clicked 
	 * @param e the click event
	 * @param cardAL the array list containing all cards to be checked
	 * @param start starting zone of the cards in the arraylist
	 * @param end zone to move clicked cards into
	 */
	public void handleSpecificZoneCardClick(MouseEvent e, ArrayList<GUICard> cardAL, Zone start, Zone end){
		for(int i = 0; i < cardAL.size(); i++){
			if(cardAL.get(i).getRec().contains(e.getPoint())){
				start.remove(i);
				end.addCard(cardAL.get(i).getCard(),0);
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

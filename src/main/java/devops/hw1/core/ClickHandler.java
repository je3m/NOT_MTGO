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
		checkPassButtonClick1();
		checkPassButtonClick2();
		
	}

	/**
	 * Handles click checking for the first players GUI card
	 */
	private void checkDispCardClick1() {
		if(MTGComp.getDispGUICard1() != null){
			if(MTGComp.getDispGUICard1().getRec().contains(MTGComp.getMousePosition())){
				MTGComp.setDispGUICard1(null);
				MTGComp.repaint();
			} else {
				for(int i = 0; i < MTGComp.getDispGUICard1().getAbilityBoxes().length; i++){
					if(MTGComp.getDispGUICard1().getAbilityBoxes()[i].contains(MTGComp.getMousePosition())){
						if(MTGComp.getDispGUICard1().getCard().getManaAbility() != null && i==0) {
							MTGComp.getBackend().activateManaAbility(MTGComp.getDispGUICard1().getCard(), true);
						} else {
							Backend.activateAbility(MTGComp.getDispGUICard1().getCard(), MTGComp.getDispGUICard1().getZone(), MTGComp.getDispGUICard1().getIndex(), i);
						}
						
						MTGComp.setDispGUICard1(null);
						MTGComp.repaint();
						break;
					}
				}
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
			} else {
				for(int i = 0; i < MTGComp.getDispGUICard2().getAbilityBoxes().length; i++){
					if(MTGComp.getDispGUICard2().getAbilityBoxes()[i].contains(MTGComp.getMousePosition())){
						if(MTGComp.getDispGUICard2().getCard().getManaAbility() != null && i==0) {
							MTGComp.getBackend().activateManaAbility(MTGComp.getDispGUICard2().getCard(), false);
						} else {
							Backend.activateAbility(MTGComp.getDispGUICard2().getCard(), MTGComp.getDispGUICard2().getZone(), MTGComp.getDispGUICard2().getIndex(), i);
						}
						MTGComp.setDispGUICard2(null);
						MTGComp.repaint();
						break;
					}
				}
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
				if((start == Zone.HAND || start == Zone.BATTLE_FIELD) && MTGComp.getDispGUICard1() == null){
					MTGComp.generateDispGUICard1(cardAL.get(i).getCard(), i, start);
				} else if ((start == Zone.HAND1 || start == Zone.BATTLE_FIELD1) && MTGComp.getDispGUICard2() == null){
					MTGComp.generateDispGUICard2(cardAL.get(i).getCard(), i, start);
				}
				MTGComp.repaint();
			}
		}
	}
	
	/**
	 * Checks to see if player 1's pass button is clicked and if so tells the backend to try to pass priority from that player
	 */
	public void checkPassButtonClick1() {
		if(MTGComp.getPassButton1().contains(MTGComp.getMousePosition())) {
			MTGComp.getBackend().passPriority();//TODO make this pass in which player is trying to pass priority
			MTGComp.repaint();
		}
	}
	
	/**
	 * Checks to see if player 2's pass button is clicked and if so tells the backend to try to pass priority from that player
	 */
	public void checkPassButtonClick2() {
		if(MTGComp.getPassButton2().contains(MTGComp.getMousePosition())) {

			MTGComp.getBackend().passPriority();//TODO make this pass in which player is trying to pass priority
			MTGComp.repaint();
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

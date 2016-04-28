package front_end;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import back_end.Backend;
import back_end.Zone;

/**
 * Handles clicking for the game
 *
 */
public class ClickHandler implements MouseListener {
	MTGComponent MTGComp;
	DispGUICard targetGUICard;
	int targetAbilityIndex;
	Boolean targetPlayer;

	/**
	 * Creates a clickhandler object for a given component
	 * @param MTGComp the component where the clicks will occur
	 */
	public ClickHandler(MTGComponent MTGComp) {
		this.MTGComp = MTGComp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.handleClickCard(e);
	}

	/**
	 * Handles clicking for displayed cards
	 * @param e the click event
	 */
	public void handleClickCard(MouseEvent e){
		if(targetGUICard == null){
			Boolean changed = false;
			changed = changed || this.handleDispCardClick1(true);
			changed = changed || this.handleDispCardClick1(false);
			changed = changed || this.handleZoneForCardClick(this.MTGComp.getHandGUICards1(), Zone.HAND);
			changed = changed || this.handleZoneForCardClick(this.MTGComp.getHandGUICards2(), Zone.HAND1);
			changed = changed || this.handleZoneForCardClick(this.MTGComp.getBattleGUICards1(), Zone.BATTLE_FIELD);
			changed = changed || this.handleZoneForCardClick(this.MTGComp.getBattleGUICards2(), Zone.BATTLE_FIELD1);
			changed = changed || this.handlePassButtonClick1(true);
			changed = changed || this.handlePassButtonClick1(false);
			changed = changed || this.checkGraveyardClick(true);
			changed = changed || this.checkGraveyardClick(false);
			if(changed){
				this.MTGComp.repaint();
			}
		} else {
			handleTargeting();
		}
	}

	private boolean checkGraveyardClick(Boolean player) {
		Rectangle graveyardButton;
		if (player){
			graveyardButton = this.MTGComp.getGraveyardButton1();
		}else{
			graveyardButton = this.MTGComp.getGraveyardButton2();
		}
		if(graveyardButton.contains(this.MTGComp.getMousePosition())) {
			if(player){
				JOptionPane.showMessageDialog(this.MTGComp, "Grave1");
			} else {
				JOptionPane.showMessageDialog(this.MTGComp, "Grave2");
			}
			return true;
		}
		return false;
	}

	/**
	 * Handles click checking for the first players GUI card
	 * @param player the player whose card is being checked
	 * @return true if anything has been changed, false if not
	 */
	private boolean handleDispCardClick1(Boolean player) {
		DispGUICard gCard;

		if (player){
			gCard = this.MTGComp.getDispGUICard1();
		}else{
			gCard = this.MTGComp.getDispGUICard2();
		}
		
		if(gCard != null){
			if(gCard.getRec().contains(this.MTGComp.getMousePosition())){
				if (player) {
					this.MTGComp.setDispGUICard1(null);
				} else {
					this.MTGComp.setDispGUICard2(null);
				}
					return true;
			} else {
				for(int i = 0; i < (gCard.getAbilityBoxes().length); i++){
					if(gCard.getAbilityBoxes()[i].contains(this.MTGComp.getMousePosition())){
						try {
							int index = gCard.getAbilityIndex(i);
							if(gCard.getCard().getAbilities()[index].getTarget() == null){
								Backend.getInstance().activateAbility(gCard.getCard(), gCard.getZone(), gCard.getIndex(), index, null);
							} else {
								this.targetGUICard = gCard;
								this.targetAbilityIndex = index;
								this.targetPlayer = player;
								this.MTGComp.setDispGUICard1(null);
								this.MTGComp.setDispGUICard2(null);
								JOptionPane.showMessageDialog(this.MTGComp, "Select a target");
								return true;
							}
							
							if (player){
								this.MTGComp.setDispGUICard1(null);
							}else{
								this.MTGComp.setDispGUICard2(null);
							}
							
							return true;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this.MTGComp, e.getMessage());
						}
					}
				}
			}

		}
		return false;

	}

	/**
	 * Handles when a card in an array list is clicked
	 * @param cardAL the array list containing all cards to be checked
	 * @param start starting zone of the cards in the arraylist
	 * @return true if anything is changed, false if not
	 */
	public boolean handleZoneForCardClick(ArrayList<GUICard> cardAL, Zone start){
		for(int i = 0; i < cardAL.size(); i++){
			if(cardAL.get(i).getRec().contains(this.MTGComp.getMousePosition())){
				if(((start == Zone.HAND) || (start == Zone.BATTLE_FIELD)) && (this.MTGComp.getDispGUICard1() == null)){
					this.MTGComp.generateDispGUICard1(cardAL.get(i).getCard(), i, start);
				} else if (((start == Zone.HAND1) || (start == Zone.BATTLE_FIELD1)) && (this.MTGComp.getDispGUICard2() == null)){
					this.MTGComp.generateDispGUICard2(cardAL.get(i).getCard(), i, start);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Handles pass button is clicked
	 * @param player the player whos button is being pressed
	 * @return true if anything is changed, false if not
	 */
	public boolean handlePassButtonClick1(Boolean player) {
		Rectangle passButton;
		if (player){
			passButton = this.MTGComp.getPassButton1();
		}else{
			passButton = this.MTGComp.getPassButton2();
		}
		if(passButton.contains(this.MTGComp.getMousePosition())) {
			this.MTGComp.getBackend().passPriority(player);
			return true;
		}
		return false;
	}
	
	public void handleTargeting(){
		Boolean changed = false;
		changed = changed || this.targetHandleZoneForCardClick(this.MTGComp.getHandGUICards1(), Zone.HAND);
		changed = changed || this.targetHandleZoneForCardClick(this.MTGComp.getHandGUICards2(), Zone.HAND1);
		changed = changed || this.targetHandleZoneForCardClick(this.MTGComp.getBattleGUICards1(), Zone.BATTLE_FIELD);
		changed = changed || this.targetHandleZoneForCardClick(this.MTGComp.getBattleGUICards2(), Zone.BATTLE_FIELD1);
		if(changed){
			targetGUICard = null;
		}
	}

	/**
	 * Handles card clickng for targeting
	 * @param cardAL Arraylist of GUICards in the zone
	 * @param start Zone to check in
	 * @return 
	 */
	public boolean targetHandleZoneForCardClick(ArrayList<GUICard> cardAL, Zone start){
		for(int i = 0; i < cardAL.size(); i++){
			if(cardAL.get(i).getRec().contains(this.MTGComp.getMousePosition())){
				try	{
					Backend.getInstance().activateAbility(this.targetGUICard.getCard(), this.targetGUICard.getZone(), this.targetGUICard.getIndex(), this.targetAbilityIndex, cardAL.get(i).getCard());
					return true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this.MTGComp, e.getMessage());
					return true;
				}
			}
		}
		return false;
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

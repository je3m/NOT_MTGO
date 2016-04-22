package devops.hw1.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Handles clicking for the game
 * @author malinocr
 *
 */
public class ClickHandler implements MouseListener {
	MTGComponent MTGComp;
	short targetState = 0;
	DispGUICard tarFire = null;

	public void dealWithTarFire(DispGUICard gCard, boolean player){

		switch(this.targetState){
		case 0:
			if (gCard.getCard().getName().equals("tarfire")){
				this.targetState = 1;
			}
			this.tarFire = gCard;
			System.out.println("select target");
			break;

		case 1:
			boolean owner;
			if(this.tarFire.getZone().equals(Zone.HAND1))
				owner = false;
			else
				owner = true;


			System.out.println("casting " + this.tarFire.getCard().getName() + " on " + gCard.getCard().getName());

			if(Backend.getInstance().castSpell(this.tarFire.getZone(), this.tarFire.getCard(), this.tarFire.index, owner, gCard.getCard(), Zone.GRAVEYARD)){

			} else {
				JOptionPane.showMessageDialog(this.MTGComp, "Cannot play that card");
			}
			this.targetState = 0;
			break;
		}
	}



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
		this.checkZoneForCardClick(this.MTGComp.getHandGUICards1(), Zone.HAND);
		this.checkZoneForCardClick(this.MTGComp.getHandGUICards2(), Zone.HAND1);
		this.checkZoneForCardClick(this.MTGComp.getBattleGUICards1(), Zone.BATTLE_FIELD);
		this.checkZoneForCardClick(this.MTGComp.getBattleGUICards2(), Zone.BATTLE_FIELD1);
		this.checkDispCardClick1(true);
		this.checkDispCardClick1(false);
		this.checkPassButtonClick1();
		this.checkPassButtonClick2();

	}

	/**
	 * Handles click checking for the first players GUI card
	 */
	private void checkDispCardClick1(Boolean player) {
		DispGUICard gCard;

		if (player){
			gCard = this.MTGComp.getDispGUICard1();
		}else{
			gCard = this.MTGComp.getDispGUICard2();
		}

		if(gCard != null){
			if ( (this.targetState == 1)){
				this.dealWithTarFire(gCard, player);
			}else
				if(gCard.getRec().contains(this.MTGComp.getMousePosition())){
					if (player) {
						this.MTGComp.setDispGUICard1(null);
					} else {
						this.MTGComp.setDispGUICard2(null);
					}

					this.MTGComp.repaint();
				} else {
					if(gCard.getAbilityBoxes()[0].contains(this.MTGComp.getMousePosition())){
						Zone z = null;

						System.out.println(gCard.getCard().getName());
						try{
							if (gCard.getCard().getName().equals("tarfire")){
								this.dealWithTarFire(gCard, !player);
							} else if(Backend.getInstance().castSpell(gCard.getZone(), gCard.getCard(), gCard.index, player, null, z)){

							} else {
								JOptionPane.showMessageDialog(this.MTGComp, "Cannot play that card");
							}

							this.MTGComp.repaint();
						} catch (Exception e){
							e.printStackTrace();
						}

					}else {

						for(int i = 0; i < (gCard.getAbilityBoxes().length); i++){
							if(gCard.getAbilityBoxes()[i].contains(this.MTGComp.getMousePosition())){
								if((gCard.getCard().getManaAbility() != null) && (i==1)) {
									this.MTGComp.getBackend().activateManaAbility(gCard.getCard(), player);
								} else {
									Backend.activateAbility(gCard.getCard(), gCard.getZone(), gCard.getIndex(), i);
								}

								this.MTGComp.setDispGUICard1(null);
								this.MTGComp.repaint();
								break;
							}
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
			if(cardAL.get(i).getRec().contains(this.MTGComp.getMousePosition())){
				if(((start == Zone.HAND) || (start == Zone.BATTLE_FIELD)) && (this.MTGComp.getDispGUICard1() == null)){
					this.MTGComp.generateDispGUICard1(cardAL.get(i).getCard(), i, start);
				} else if (((start == Zone.HAND1) || (start == Zone.BATTLE_FIELD1)) && (this.MTGComp.getDispGUICard2() == null)){
					this.MTGComp.generateDispGUICard2(cardAL.get(i).getCard(), i, start);
				}
				this.MTGComp.repaint();
			}
		}
	}

	/**
	 * Checks to see if player 1's pass button is clicked and if so tells the backend to try to pass priority from that player
	 */
	public void checkPassButtonClick1() {
		if(this.MTGComp.getPassButton1().contains(this.MTGComp.getMousePosition())) {
			this.MTGComp.getBackend().passPriority(true);//TODO make this pass in which player is trying to pass priority
			this.MTGComp.repaint();
		}
	}

	/**
	 * Checks to see if player 2's pass button is clicked and if so tells the backend to try to pass priority from that player
	 */
	public void checkPassButtonClick2() {
		if(this.MTGComp.getPassButton2().contains(this.MTGComp.getMousePosition())) {

			this.MTGComp.getBackend().passPriority(false);//TODO make this pass in which player is trying to pass priority
			this.MTGComp.repaint();
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

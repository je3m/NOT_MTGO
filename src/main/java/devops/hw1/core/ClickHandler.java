package devops.hw1.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class ClickHandler implements MouseListener {
	MTGComponent MTGComp;
	
	public ClickHandler(MTGComponent MTGComp) {
		this.MTGComp = MTGComp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		handleClickCard(e);
	}
	
	public void handleClickCard(MouseEvent e){
		handleSpecificZoneCardClick(e, MTGComp.getHandGUICards1(), Zone.HAND, Zone.BATTLE_FIELD);
		handleSpecificZoneCardClick(e, MTGComp.getHandGUICards2(), Zone.HAND1, Zone.BATTLE_FIELD1);
	}

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

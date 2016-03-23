package devops.hw1.core;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MTGDuelDecks {
	
	
	public static void main(String[] args){
		JFrame MTGJFrame = setUpJFrame();
		MTGComponent MTGComp = new MTGComponent(MTGJFrame.getWidth(), MTGJFrame.getHeight());
		MTGJFrame.add(MTGComp);
	}
	
	public static JFrame setUpJFrame(){
		JFrame Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Duel Decks");
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		return Frame;
	}
}

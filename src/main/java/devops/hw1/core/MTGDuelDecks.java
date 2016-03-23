package devops.hw1.core;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MTGDuelDecks {
	private static final Dimension JFRAME_SIZE = new Dimension(1200, 800);
	
	
	public static void main(String[] args){
		JFrame MTGJFrame = setUpJFrame();
	}
	
	public static JFrame setUpJFrame(){
		JFrame Frame = new JFrame();
		Frame.setSize(JFRAME_SIZE);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Duel Decks");
		return Frame;
	}
}

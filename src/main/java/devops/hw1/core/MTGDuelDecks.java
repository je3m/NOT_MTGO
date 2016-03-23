package devops.hw1.core;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class MTGDuelDecks {
	
	
	public static void main(String[] args){
		JFrame MTGJFrame = setUpJFrame();
	}
	
	/**
	 * Sets up the JFrame to include all the default values for the game
	 * @return initialized game JFrame
	 */
	private static JFrame setUpJFrame(){
		JFrame Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Duel Decks");
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		MTGComponent MTGComp = new MTGComponent(Frame.getContentPane().getWidth(), Frame.getContentPane().getHeight());
		Frame.add(MTGComp);
		Frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	MTGComp.setWindowX(Frame.getContentPane().getWidth());
		    	MTGComp.setWindowY(Frame.getContentPane().getHeight());
		    }
		});
		return Frame;
	}
}

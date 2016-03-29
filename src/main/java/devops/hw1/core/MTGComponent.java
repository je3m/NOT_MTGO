package devops.hw1.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
 
/**
 * The component that draws everything for the game.
 * @author malinocr
 *
 */
@SuppressWarnings("serial")
public class MTGComponent extends JComponent{
	private int windowX;
	private int windowY;
	private ArrayList<GUICard> handGUICards1;
	private DispGUICard dispGUICard1;
	private ArrayList<GUICard> handGUICards2;
	private DispGUICard dispGUICard2;
	private ArrayList<GUICard> battleGUICards1;
	private ArrayList<GUICard> battleGUICards2;
	
	/**
	 * Constructor for a MTGComponent
	 * @param x width of the usable space in the JFrame that the component is placed in
	 * @param y height of the usable space in the JFrame that the component is placed in
	 */
	public MTGComponent(int x, int y){
		this.windowX = x;
		this.windowY = y;
		this.handGUICards1 = new ArrayList<GUICard>();
		this.dispGUICard1 = null;
		this.handGUICards2 = new ArrayList<GUICard>();
		this.dispGUICard2 = null;
		this.battleGUICards1 = new ArrayList<GUICard>();
		this.battleGUICards2 = new ArrayList<GUICard>();
	}
	
	public ArrayList<GUICard> getHandGUICards1() {
		return handGUICards1;
	}

	public ArrayList<GUICard> getHandGUICards2() {
		return handGUICards2;
	}

	public ArrayList<GUICard> getBattleGUICards1() {
		return battleGUICards1;
	}

	public ArrayList<GUICard> getBattleGUICards2() {
		return battleGUICards2;
	}

	public void setWindowX(int x){
		this.windowX = x;
	}
	
	public void setWindowY(int y){
		this.windowY = y;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;
		drawFormat(graphics2);
		drawCountedZones1(graphics2);
		drawCountedZones2(graphics2);
		generateGUICards(this.handGUICards1, Zone.HAND, 0);
		generateGUICards(this.handGUICards2, Zone.HAND1, 0.9);
		generateGUICards(this.battleGUICards1, Zone.BATTLE_FIELD, 0.1);
		generateGUICards(this.battleGUICards2, Zone.BATTLE_FIELD1, 0.8);
		drawGUICards(graphics2);
	}
	
	/**
	 * Draws the basic format for the board
	 * @param graphics2
	 */
	private void drawFormat(Graphics2D graphics2) {
		graphics2.setColor(Color.BLACK);
		graphics2.drawLine((int)Math.round(windowX*0.5), 0, (int)Math.round(windowX*0.5), windowY);
		
		graphics2.draw(new Rectangle(0,0,(int)Math.round(windowX*0.1), windowY - 1));
		graphics2.draw(new Rectangle(0,(int)Math.round(windowY*0.8),(int)Math.round(windowX*0.1),(int)Math.round(windowY*0.1)));
		graphics2.draw(new Rectangle((int)Math.round(windowX*0.9)-1,0,(int)Math.round(windowX*0.1), windowY - 1));
		graphics2.draw(new Rectangle((int)Math.round(windowX*0.9)-1,(int)Math.round(windowY*0.8),(int)Math.round(windowX*0.1),(int)Math.round(windowY*0.1)));
	}
	
	/**
	 * Draws counted library, graveyard, and exile for player 1
	 * @param graphics2
	 */
	private void drawCountedZones1(Graphics2D graphics2){
		graphics2.setFont(new Font("TimesRoman", Font.PLAIN, (int)Math.round(windowY*0.8*0.1))); 
		graphics2.drawString(String.valueOf(Zone.LIBRARY.getSize()),(int)Math.round(windowX*0.038),(int)Math.round(windowY*0.98) - 1);
		graphics2.drawString(String.valueOf(Zone.GRAVEYARD.getSize()),(int)Math.round(windowX*0.015),(int)Math.round(windowY*0.88) - 1);
		graphics2.drawString(String.valueOf(Zone.EXILE.getSize()),(int)Math.round(windowX*0.065),(int)Math.round(windowY*0.88) - 1);
	}
	
	/**
	 * Draws counted library, graveyard, and exile for player 2
	 * @param graphics2
	 */
	private void drawCountedZones2(Graphics2D graphics2){
		graphics2.setFont(new Font("TimesRoman", Font.PLAIN, (int)Math.round(windowY*0.8*0.1))); 
		graphics2.drawString(String.valueOf(Zone.LIBRARY1.getSize()),(int)Math.round(windowX*0.938),(int)Math.round(windowY*0.98) - 1);
		graphics2.drawString(String.valueOf(Zone.GRAVEYARD1.getSize()),(int)Math.round(windowX*0.915),(int)Math.round(windowY*0.88) - 1);
		graphics2.drawString(String.valueOf(Zone.EXILE1.getSize()),(int)Math.round(windowX*0.965),(int)Math.round(windowY*0.88) - 1);
	}
	
	/**
	 * Generates a GUICard object for each card in a given zone and puts them into a given arraylist
	 * @param cardsAL an array list to place the created GUICard objects
	 * @param zone the zone where the MTGCards are be created from
	 * @param baseXLocation the base x percentage of the width of the screen where the cards rectangle should be placed 
	 */
	private void generateGUICards(ArrayList<GUICard> cardsAL, Zone zone, double baseXLocation){//!#stress-test this
		cardsAL.clear();
		int height = getCardHeight(zone);
		int width = (int)Math.round(height * 3.5/2.5);
		int currentSpace = (int)Math.round(0.025*windowY);
		Card[] cards = zone.getCards();
		for(int i = 0; i < zone.getSize(); i++){
			cardsAL.add(new GUICard(new Rectangle((int)Math.round((0.05 + baseXLocation)*windowX - width/2), currentSpace, width, height), cards[i]));
			currentSpace = currentSpace + (int)Math.round(1.25*height);
		}
	}
	
	/**
	 * Draw all the GUICards onto the graphics object
	 * @param graphics2 the graphics object to draw onto
	 */
	private void drawGUICards(Graphics2D graphics2){
		drawGUICardArrayList(handGUICards1, graphics2);
		drawGUICardArrayList(handGUICards2, graphics2);
		drawGUICardArrayList(battleGUICards1, graphics2);
		drawGUICardArrayList(battleGUICards2, graphics2);
		drawBigGUICard(dispGUICard1, graphics2);
		drawBigGUICard(dispGUICard2, graphics2);
	}

	/**
	 * Draw all the GUICards in a given arraylist
	 * @param cardsAL the arraylist where the cards to draw are located
	 * @param graphics2 the graphics object to draw onto
	 */
	private void drawGUICardArrayList(ArrayList<GUICard> cardsAL, Graphics2D graphics2 ){
		for(int i = 0; i < cardsAL.size(); i++){
			graphics2.draw(cardsAL.get(i).getRec());
			graphics2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			graphics2.drawString(cardsAL.get(i).getCard().getName(), (int)cardsAL.get(i).getRec().getCenterX(), (int)cardsAL.get(i).getRec().getCenterY());
		}
	}
	
	/**
	 * Determine the height a card in a given zone should be based on the window height
	 * @param zone the zone the card is located in
	 * @return the height of the card
	 */
	private int getCardHeight(Zone zone){
		int HeightRec = 3*windowY/(5*zone.getSize()-1);
		int heightWidthRec = (int)Math.round(HeightRec * 3.5/2.5);
		int windowWidthRec = (int)Math.round(windowX*0.08);
		if(heightWidthRec < windowWidthRec){
			return HeightRec;
		} else {
			return (int)Math.round(windowWidthRec * 2.5/3.5);
		}
	}
	
	/**
	 * Draws the large display for a card
	 * @param dispCard display card object to be drawn
	 * @param graphics2 graphics to draw the card on
	 */
	private void drawBigGUICard(DispGUICard dispCard, Graphics2D graphics2) {
		if(dispCard != null){
			graphics2.draw(dispCard.getRec());
			graphics2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			graphics2.drawString(dispCard.getCard().getName(), (int)dispCard.getRec().getCenterX(), (int)dispCard.getRec().getCenterY());
		}
	}
	
	public void generateDispGUICard1(Card card){
		this.dispGUICard1 = new DispGUICard(new Rectangle((int)Math.round(0.15*windowX), (int)Math.round(0.2*windowY), (int)Math.round(0.2*windowX), (int)Math.round(0.2*windowX*(3.5/2.5))),card);
	}
	
	public void generateDispGUICard2(Card card){
		this.dispGUICard2 = new DispGUICard(new Rectangle((int)Math.round(0.55*windowX), (int)Math.round(0.2*windowY), (int)Math.round(0.2*windowX), (int)Math.round(0.2*windowX*(3.5/2.5))),card);
	}
}

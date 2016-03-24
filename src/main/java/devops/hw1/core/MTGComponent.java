package devops.hw1.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class MTGComponent extends JComponent{
	private int windowX;
	private int windowY;
	private ArrayList<GUICard> guiCards1;
	private ArrayList<GUICard> guiCards2;
	
	public MTGComponent(int x, int y){
		this.windowX = x;
		this.windowY = y;
		this.guiCards1 = new ArrayList<GUICard>();
		this.guiCards2 = new ArrayList<GUICard>();
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
		generateGUICards1();
		generateGUICards2();
		drawGUICards(graphics2);
	}
	
	/**
	 * Draws the basic format for the board
	 * @param graphics2
	 */
	private void drawFormat(Graphics2D graphics2) {
		graphics2.setColor(Color.BLACK);
		graphics2.fill(new Rectangle((int)Math.round(windowX*0.49),0,(int)Math.round(windowX*0.02),windowY));
		
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
	
	private void generateGUICards1(){
		guiCards1.clear();
		int height = getCardHeight();
		int width = (int)Math.round(height * 3.5/2.5);
		int currentSpace = (int)Math.round(0.025*windowY);
		Card[] cards = Zone.HAND.getCards();
		for(int i = 0; i < Zone.HAND.getSize(); i++){
			guiCards1.add(new GUICard(new Rectangle((int)Math.round(0.05*windowX - width/2), currentSpace, width, height), cards[i]));
			currentSpace = currentSpace + (int)Math.round(1.25*height);
		}
	}
	
	private void generateGUICards2(){
		guiCards2.clear();
		int height = getCardHeight();
		int width = (int)Math.round(height * 3.5/2.5);
		int currentSpace = (int)Math.round(0.025*windowY);
		Card[] cards = Zone.HAND1.getCards();
		for(int i = 0; i < Zone.HAND1.getSize(); i++){
			guiCards2.add(new GUICard(new Rectangle((int)Math.round(0.95*windowX - width/2), currentSpace, width, height), cards[i]));
			currentSpace = currentSpace + (int)Math.round(1.25*height);
		}
	}
	
	private void drawGUICards(Graphics2D graphics2){
		for(int i = 0; i < guiCards1.size(); i++){
			graphics2.draw(guiCards1.get(i).getRec());
		}
		for(int i = 0; i < guiCards2.size(); i++){
			graphics2.draw(guiCards2.get(i).getRec());
		}
	}
	
	private int getCardHeight(){
		int HeightRec = 3*windowY/(5*Zone.HAND.getSize()-1);
		int heightWidthRec = (int)Math.round(HeightRec * 3.5/2.5);
		int windowWidthRec = (int)Math.round(windowX*0.08);
		if(heightWidthRec < windowWidthRec){
			return HeightRec;
		} else {
			return (int)Math.round(windowWidthRec * 2.5/3.5);
		}
	}
}

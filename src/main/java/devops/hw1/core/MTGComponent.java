package devops.hw1.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class MTGComponent extends JComponent{
	int windowX;
	int windowY;
	
	public MTGComponent(int x, int y){
		this.windowX = x;
		this.windowY = y;
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
	}
	
	/**
	 * Draws the basic format for the board
	 * @param graphics2 The graphic2D to be drawn onto
	 */
	private void drawFormat(Graphics2D graphics2) {
		graphics2.setColor(Color.BLACK);
		graphics2.fill(new Rectangle((int)Math.round(windowX*0.49),0,(int)Math.round(windowX*0.02),windowY));
		
		graphics2.draw(new Rectangle(0,0,(int)Math.round(windowX*0.1), windowY - 1));
		graphics2.draw(new Rectangle(0,(int)Math.round(windowY*0.8),(int)Math.round(windowX*0.1),(int)Math.round(windowY*0.1)));
		graphics2.draw(new Rectangle((int)Math.round(windowX*0.9)-1,0,(int)Math.round(windowX*0.1), windowY - 1));
		graphics2.draw(new Rectangle((int)Math.round(windowX*0.9)-1,(int)Math.round(windowY*0.8),(int)Math.round(windowX*0.1),(int)Math.round(windowY*0.1)));
	}
	
	private void drawCountedZones1(Graphics2D graphics2){
		graphics2.setFont(new Font("TimesRoman", Font.PLAIN, (int)Math.round(windowY*0.8*0.1))); 
		graphics2.drawString(String.valueOf(Zone.LIBRARY.getSize()),(int)Math.round(windowX*0.038),(int)Math.round(windowY*0.98) - 1);
		graphics2.drawString(String.valueOf(Zone.GRAVEYARD.getSize()),(int)Math.round(windowX*0.015),(int)Math.round(windowY*0.88) - 1);
		graphics2.drawString(String.valueOf(Zone.EXILE.getSize()),(int)Math.round(windowX*0.065),(int)Math.round(windowY*0.88) - 1);
	}
	
	private void drawCountedZones2(Graphics2D graphics2){
		graphics2.setFont(new Font("TimesRoman", Font.PLAIN, (int)Math.round(windowY*0.8*0.1))); 
		graphics2.drawString(String.valueOf(Zone.LIBRARY1.getSize()),(int)Math.round(windowX*0.938),(int)Math.round(windowY*0.98) - 1);
		graphics2.drawString(String.valueOf(Zone.GRAVEYARD1.getSize()),(int)Math.round(windowX*0.915),(int)Math.round(windowY*0.88) - 1);
		graphics2.drawString(String.valueOf(Zone.EXILE1.getSize()),(int)Math.round(windowX*0.965),(int)Math.round(windowY*0.88) - 1);
	}
}

package devops.hw1.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


public class MTGComponent extends JComponent{
	int windowX;
	int windowY;
	
	public MTGComponent(int x, int y){
		windowX = x;
		windowY = y;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;
		drawFormat(graphics2);
	}
	
	private void drawFormat(Graphics2D graphics2) {
		graphics2.setColor(Color.BLACK);
		System.out.print(windowX + " " + windowY);
		graphics2.draw(new Rectangle((int)Math.round(windowX*0.49),0,(int)Math.round(windowX*0.02),windowY));
	}
}

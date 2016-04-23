package front_end;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import back_end.Card;
/**
 * This class represents a card that is displayed in the GUI
 * @author malinocr
 *
 */
public class GUICard {
	protected Rectangle rec;
	protected Card card;
	
	/**
	 * Constructs a GUI card object
	 * @param rec the rectangle that is displayed for the card
	 * @param card the card the GUI object represents
	 */
	public GUICard(Rectangle rec, Card card){
		this.rec = rec;
		this.card = card;
	}

	/**
	 * Get the GUI card's rectangle
	 * @return the GUI card's rectangle
	 */
	public Rectangle getRec() {
		return rec;
	}

	/**
	 * Set the GUI card's rectangle
	 * @return the GUI card's rectangle
	 */
	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	/**
	 * Get the GUI card's card
	 * @return the GUI card's card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Set the GUI card's card
	 * @return the GUI card's card
	 */
	public void setCard(Card card) {
		this.card = card;
	}
	
	/**
	 * Get the image of GUI card
	 * @return the image of the GUI card
	 */
	public BufferedImage getImage(){
		if(card.getImage() == null){
			return null;
		} else {
			BufferedImage img = null;
	
			try 
			{
				img = ImageIO.read(new File(card.getImage()));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return img;
		}
	}
}

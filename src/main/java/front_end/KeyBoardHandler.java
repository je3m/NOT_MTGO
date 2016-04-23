package front_end;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Handles Keyboard events for the MTG Duel Decks
 *
 */
public class KeyBoardHandler implements KeyListener {

	private MTGComponent MTGComp;
	private boolean priority = true;

	public KeyBoardHandler(MTGComponent comp) {
		this.MTGComp = comp;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == ' '){
			this.MTGComp.getBackend().passPriority(this.priority);
			this.priority = !this.priority;
			this.MTGComp.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

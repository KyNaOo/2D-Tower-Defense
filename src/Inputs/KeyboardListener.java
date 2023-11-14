package src.Inputs;

import static src.main.GameStates.PLAYING;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.main.Game;
import src.main.GameStates;

public class KeyboardListener implements KeyListener {
	private Game game;

	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(GameStates.gameState == PLAYING){
			game.getPlaying().keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

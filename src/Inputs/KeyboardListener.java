package src.Inputs;

import src.Scenes.Playing;
import src.main.Game;
import src.main.GameStates;
import static src.main.GameStates.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode()== KeyEvent.VK_A){
            GameStates.gameStates = MENU;
        } else if (keyEvent.getKeyCode()== KeyEvent.VK_S){
            GameStates.gameStates = PLAYING;
        } else if (keyEvent.getKeyCode()== KeyEvent.VK_D){
            GameStates.gameStates = QUIT;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

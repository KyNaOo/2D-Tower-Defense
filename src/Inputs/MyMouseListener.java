package src.Inputs;

import src.main.Game;
import src.main.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private Game game;

    public MyMouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameStates){
                case MENU:
                    game.getMenu().mouseClicked(mouseEvent.getX(), mouseEvent.getY());
                    break;
                case PLAYING:
                    break;
                case QUIT:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        switch (GameStates.gameStates){
            case MENU:
                game.getMenu().mousePressed(mouseEvent.getX(), mouseEvent.getY());
                break;
            case PLAYING:
                break;
            case QUIT:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        switch (GameStates.gameStates){
            case MENU:
                game.getMenu().mouseReleased(mouseEvent.getX(), mouseEvent.getY());
                break;
            case PLAYING:
                break;
            case QUIT:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        switch (GameStates.gameStates){
            case MENU:
                game.getMenu().mouseMoved(mouseEvent.getX(), mouseEvent.getY());
                break;
            case PLAYING:
                break;
            case QUIT:
                break;
        }
    }
}

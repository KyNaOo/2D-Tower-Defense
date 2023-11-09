package src.main;

import src.Inputs.KeyboardListener;
import src.Inputs.MyMouseListener;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel{
    private Game game;
    private Dimension size;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game){
        this.game = game;
        setPanelSize();
    }

    public void initInput(){
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640,640);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);
    }

}

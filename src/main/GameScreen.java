package src.main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GameScreen extends JPanel{

    private Random random;

    public GameScreen(){
        random=new Random();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for(int x = 0; x<20;x++){
            //g.setColor(Color .RED); Basic color
            g.setColor(new Color(105,10,50));//Custom color
            g.fillRect(x*32, 0, 32, 32);
        }
    }
}

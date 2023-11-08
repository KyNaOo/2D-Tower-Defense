package src.main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel{

    private Random random;
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage img){
        this.img=img;
        loadSprites();
        random=new Random();
    }

    private void loadSprites() {
        for (int y = 0; y < 3; y++) {
            for(int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x*32, y*32, 32, 32));
            }

    }
}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for (int y=0; y<20; y++) {
            for(int x=0; x<20;x++){
                g.drawImage(sprites.get(getRndInt()), x*32, y*32, null);
            }
        }
        


        /*for(int x = 0; x<20;x++){
            for(int y=0; y<20;y++){
            //g.setColor(Color .RED); Basic color
            g.setColor(getRndColor());//Custom color
            g.fillRect(x*32, y*32, 32, 32);
            }
        }*/  
    }

    private int getRndInt(){
        return random.nextInt(29);
    }

    private Color getRndColor(){
        int r= random.nextInt(256);
        int g=  random.nextInt(256);
        int b=  random.nextInt(256);

        return new Color(r,g,b);
    }
}

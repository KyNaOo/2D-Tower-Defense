package src.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{

    private GameScreen gameScreen;

    private BufferedImage img;
    private long lastTime;
    private Thread gameThread;
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    public Game() {
        importImg();

        setSize(640, 640);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Use JFrame.EXIT_ON_CLOSE
        setLocationRelativeTo(null);

        gameScreen=new GameScreen(img);
        add(gameScreen);
        setVisible(true);
    }

   
     private void importImg() {
        InputStream is = getClass().getResourceAsStream("img/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }


    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastTimeCheck = System.currentTimeMillis();
        while (true){
            if(System.nanoTime()-lastFrame >= timePerFrame){
                lastFrame = System.nanoTime();
                repaint();
                frames++;
            }
            if (System.nanoTime() - lastUpdate >= timePerUpdate){
                lastUpdate = System.nanoTime();
                updates++;
            }
            if(System.currentTimeMillis() - lastTimeCheck>=1000){
                System.out.println("fps = "+ frames + " | ups = "+ updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }



    }
}

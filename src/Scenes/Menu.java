package src.Scenes;

import src.main.Game;
import src.ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static src.main.GameStates.QUIT;
import static src.main.GameStates.PLAYING;
import static src.main.GameStates.setGameState;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    private MyButton bPlaying, bQuit;
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w/3;
        int x = 640/2  -w/2;
        int y = 150;
        int yOffset = 100;
        bPlaying = new MyButton("Play", x, y, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset, w, h);


    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x,y)){
            setGameState(PLAYING);
        }
        if (bQuit.getBounds().contains(x,y)){
            setGameState(QUIT);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        if (bPlaying.getBounds().contains(x,y)){
            bPlaying.setMouseOver(true);
        }
        bQuit.setMouseOver(false);
        if (bQuit.getBounds().contains(x,y)){
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x,y)){
            bPlaying.setMousePressed(true);
        }
        if (bQuit.getBounds().contains(x,y)){
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bQuit.draw(g);
    }

    private void loadSprites() {
        for (int y = 0; y < 3; y++) {
            for(int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x*32, y*32, 32, 32));
            }
        }
    }
    private int getRndInt(){
        return random.nextInt(29);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("../main/img/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

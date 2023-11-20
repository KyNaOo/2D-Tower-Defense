package src.Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import src.help.LoadSave;
import src.main.Game;
import src.ui.MyButton;
import static src.main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private BufferedImage img;
	private BufferedImage backgroundImage;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	private MyButton bPlaying, bQuit;

	public Menu(Game game) {
		super(game);
		importImg();
		loadSprites();
		initButtons();
		importBackImg();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y + yOffset, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);

	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bQuit.draw(g);

	}
	private void drawBackground(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }
    }

	private void importImg() {

		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("src/help/spriteV2.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadSprites() {

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}

	}

	private void importBackImg() {
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("src/help/Image.png");
        try {
            backgroundImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void mouseClicked(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
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

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}

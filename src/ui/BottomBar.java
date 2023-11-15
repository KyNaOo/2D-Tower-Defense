package src.ui;

import static src.main.GameStates.MENU;
import static src.main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.objects.Tile;
import src.Scenes.Playing;

public class BottomBar {

	private int x, y, width, height;
	private Playing playing;
	private MyButton bMenu;

	private Tile selectedTile;

	private ArrayList<MyButton> tileButtons = new ArrayList<>();

	public BottomBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;

		initButtons();
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", 2, 642, 100, 30);

		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		int i = 0;

	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);

		drawSelectedTile(g);

	}

	private void drawSelectedTile(Graphics g) {

		if (selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
			g.setColor(Color.black);
			g.drawRect(550, 650, 50, 50);
		}

	}

	public void draw(Graphics g) {

		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for (MyButton b : tileButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else {
			for (MyButton b : tileButtons) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for (MyButton b : tileButtons)
			b.resetBooleans();

	}

}

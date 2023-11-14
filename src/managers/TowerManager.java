package src.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.help.LoadSave;
import src.objects.Tower;
import src.Scenes.Playing;

public class TowerManager {

	private Playing playing;
	private BufferedImage[] towerImgs;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;

	public TowerManager(Playing playing) {
		this.playing = playing;
		loadTowerImgs();
	}

	private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[4];
		for (int i = 0; i < 4; i++)
			towerImgs[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
	}

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
	}

	public void update() {
	}

	public void draw(Graphics g) {
		for (Tower t : towers)
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
	}

	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}

    public Tower getTowerAt(int x, int y) {
		for (Tower t : towers)
			if (t.getX() == x)
				if (t.getY() == y)
					return t;
		return null;
	}

}
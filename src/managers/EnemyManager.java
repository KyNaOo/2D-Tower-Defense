package src.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.Entities.Plant;
import src.help.LoadSave;
import src.Scenes.Playing;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[] enemyImgs;
	private ArrayList<Plant> enemies = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		enemyImgs = new BufferedImage[4];
		addEnemy(10 * 32, 2 * 32);
		loadEnemyImgs();
	}

	private void loadEnemyImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		enemyImgs[0] = atlas.getSubimage(9*32, 2*32, 32, 32);
		enemyImgs[1] = atlas.getSubimage(32, 32, 32, 32);
		enemyImgs[2] = atlas.getSubimage(2 * 32, 32, 32, 32);
		enemyImgs[3] = atlas.getSubimage(3 * 32, 32, 32, 32);
	}

	public void update() {
		for (Plant e : enemies)
			e.move(0.5f, 0);
	}

	public void addEnemy(int x, int y) {
		enemies.add(new Plant(x, y, 0, 0));
	}

	public void draw(Graphics g) {
		for (Plant e : enemies)
			drawEnemy(e, g);

	}

	private void drawEnemy(Plant e, Graphics g) {
		g.drawImage(enemyImgs[0], (int) e.getX(), (int) e.getY(), null);
	}

}

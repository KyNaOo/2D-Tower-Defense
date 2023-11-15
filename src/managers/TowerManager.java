package src.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.help.LoadSave;
import src.objects.Tower;
import src.Entities.APlant;
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
		for(Tower t : towers){
			t.update();
			attackEnemyIfClose(t);
		}
		
	}

	private void attackEnemyIfClose(Tower t) {
		for (APlant e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive())
				if (isEnemyInRange(t, e)) {
					if (t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
					}
				} 
		}

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
    private boolean isEnemyInRange(Tower t, APlant e) {
        int range = GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }

    private int GetHypoDistance(float x1, float y1, float x2, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);

        return (int) Math.hypot(xDiff, yDiff);
    }
	public void upgradeTower(Tower displayedTower) {
		for(Tower t : towers)
			if(t.getId() == displayedTower.getId())
				t.upgradeTower();
	}

	public void removeTower(Tower displayedTower) {
		for (int i = 0; i < towers.size(); i++)
			if (towers.get(i).getId() == displayedTower.getId())
				towers.remove(i);
	}


    public void reset(){
        towers.clear();
        towerAmount = 0;
    }

}

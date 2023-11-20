package src.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.help.LoadSave;
import src.objects.*;
import src.Scenes.Playing;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<IAlly> Zombies = new ArrayList<>();
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

    public void addTower(Zombie selectedZombie, int xPos, int yPos) {
        switch (selectedZombie.getTowerType()){
            case 0:
                Zombies.add(new ZombieArcher(xPos, yPos, towerAmount++));
                break;
            case 1:
                Zombies.add(new ZombieWizard(xPos, yPos, towerAmount++));
                break;
            case 2:
                Zombies.add(new ZombieCannon(xPos, yPos, towerAmount++));
                break;
            case 3:
                Zombies.add(new ZombieLaser(xPos, yPos, towerAmount++));
                break;
        }
    }

	public void update() {
		for(IAlly t : Zombies){
			t.update();
			attackEnemyIfClose(t);
		}
		
	}

	private void attackEnemyIfClose(IAlly t) {
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
        for (IAlly t : Zombies)
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public IAlly getTowerAt(int x, int y) {
        for (IAlly t : Zombies)
            if (t.getX() == x)
                if (t.getY() == y)
                    return t;
        return null;
    }
    private boolean isEnemyInRange(IAlly t, APlant e) {
        int range = GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }

    private int GetHypoDistance(float x1, float y1, float x2, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);

        return (int) Math.hypot(xDiff, yDiff);
    }
	public void upgradeTower(IAlly displayedZombie) {
		for(IAlly t : Zombies)
			if(t.getId() == displayedZombie.getId())
				t.upgradeTower();
	}

	public void removeTower(IAlly displayedZombie) {
		for (int i = 0; i < Zombies.size(); i++)
			if (Zombies.get(i).getId() == displayedZombie.getId())
				Zombies.remove(i);
	}


    public void reset(){
        Zombies.clear();
        towerAmount = 0;
    }

}

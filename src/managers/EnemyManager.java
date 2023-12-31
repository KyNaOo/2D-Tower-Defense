package src.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.help.LoadSave;
import src.Scenes.Playing;
import src.objects.*;

import static src.help.Constants.Direction.*;

import static src.help.Constants.Tiles.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[] enemyImgs;
	private ArrayList<APlant> enemies = new ArrayList<>();
	//private float speed = 0.5f;
	private int HPbarWidth=20;


	public EnemyManager(Playing playing) {
		this.playing = playing;
		enemyImgs = new BufferedImage[4];
		loadEnemyImgs();
	}

	private void loadEnemyImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		enemyImgs[0] = atlas.getSubimage(0, 1*32, 32, 32);
		enemyImgs[1] = atlas.getSubimage(32, 32, 32, 32);
		enemyImgs[2] = atlas.getSubimage(2 * 32, 32, 32, 32);
		enemyImgs[3] = atlas.getSubimage(3 * 32, 32, 32, 32);

	}

	public void update() {

		for (APlant e : enemies){
			if(e.isAlive()){
				updateEnemyMove(e);
			}	
		}
	}

	private void updateEnemyMove(APlant e) {
		if (e.getLastDir() == -1){
			setNewDirectionAndMove(e);
		}
		int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(),e));
		int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(),e));
		if (getTileType(newX, newY)== ROAD_TILE){
			e.move(e.getSpeed(), e.getLastDir());
		} else if (isAtEnd(e)){
			e.kill();
			playing.removeOneLife();
		} else {
			setNewDirectionAndMove(e);
		}
	}

	private void setNewDirectionAndMove(APlant e) {
		int dir = e.getLastDir();
		int xCord = (int) (e.getX()/32);
		int yCord = (int) (e.getY()/32);
		fixEnemeyOffsetTile(e, dir, xCord, yCord);
		if (isAtEnd(e)){
			return;
		}
		if (dir == LEFT || dir == RIGHT){
			int newY = (int) (e.getY() + getSpeedAndHeight(UP,e));
			if (getTileType((int)e.getX(), newY) == ROAD_TILE){
				e.move(e.getSpeed(), UP);
			}else {
				e.move(e.getSpeed(), DOWN);
			}
		} else {
			int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT,e));
			if (getTileType(newX, (int)e.getY())== ROAD_TILE){
				e.move(e.getSpeed(), RIGHT);
			} else {
				e.move(e.getSpeed(), LEFT);
			}
		}
	}

	private void fixEnemeyOffsetTile(APlant e, int dir, int xCord, int yCord) {
		switch (dir){
			case RIGHT:
				if (xCord<19){
					xCord++;
				}
				break;
			case DOWN:
				if (yCord<19){
					yCord++;
				}
				break;
		}
		e.setPos(xCord*32, yCord*32);
	}

	private boolean isAtEnd(APlant e) {
		if (e.getY() == 6*32)
			if (e.getX() == 18*32)
				return true;
		return false;
	}

	private int getTileType(int x, int y) {
		return playing.getTileType(x,y);
	}

	private float getSpeedAndHeight(int dir, APlant enemyType) {
		if (dir == UP){
			return -enemyType.getSpeed();
		} else if (dir == DOWN) {
			return enemyType.getSpeed() + 32;
		}
		return 0;
	}

	private float getSpeedAndWidth(int dir, APlant enemyType) {
		if (dir == LEFT){
			return -enemyType.getSpeed();
		} else if (dir == RIGHT) {
			return enemyType.getSpeed() + 32;
		}
		return 0;
	}
	public void addEnemy(int enemyType) {
		int x = 0;
		int y = 64;
		switch (enemyType){
			case 0:
				enemies.add(new PlantBasic(x, y, 0, this));
				break;
			case 1:
				enemies.add(new PlantFast(x, y, 1, this));
				break;
			case 2:
				enemies.add(new PlantWarrior(x, y, 2, this));
				break;
			case 3:
				enemies.add(new PlantBoss(x, y, 3, this));
				break;
		}
	}
	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);
	}
	public void draw(Graphics g) {
		for (APlant e : enemies){
			if(e.isAlive()){
				drawEnemy(e, g);
				drawHealthBar(e,g);
			}	
		}
	}

	private void drawHealthBar(APlant e, Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)e.getX()+16-(getNewBarWidth(e)/2), (int)e.getY()-5, getNewBarWidth(e), 3);
	}

	private int getNewBarWidth(APlant e) {
		return (int)(HPbarWidth*e.getHealthBarFloat());
	}

	private void drawEnemy(APlant e, Graphics g) {
		g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
	}

	public ArrayList<APlant> getEnemies() {
		return enemies;
	}

	public int getAmountOfAliveEnemies() {
		int size = 0;
		for (APlant plant : enemies){
			if (plant.isAlive()){
				size++;
			}
		}
		return size;
	}

	public void rewardPlayer(APlant enemyType) {
		playing.rewardPlayer(enemyType);
	}
	public void reset(){
		enemies.clear();
	}
}

package src.Entities;

import src.managers.EnemyManager;

import java.awt.Rectangle;

import static src.help.Constants.Direction.*;


public abstract class APlant implements IEnemy{

    private float x, y;
    private Rectangle bounds;
    private int health;
    private int maxHealth;
    private int ID;
    private int enemyType;
    private int lastDir;
    private boolean alive = true;
    protected int slowTickLimit = 120;
	protected int slowTick = slowTickLimit;
    protected EnemyManager enemyManager;

    public int getLastDir() {
        return lastDir;
    }

    private void setStartHealth() {
        health=src.help.Constants.Plants.GetStartHealth(enemyType);
        maxHealth=health;
    }

    public APlant(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDir = -1;
        setStartHealth();
    }
    
    public void slow() {
		slowTick = 0;
	}

    public void move(float speed, int dir) {
        lastDir = dir;
        if (slowTick < slowTickLimit) {
			slowTick++;
			speed *= 0.5f;
		}
        switch (dir){
            case LEFT :
                this.x-=speed;
                break;
            case UP:
                this.y-=speed;
                break;
            case RIGHT:
                this.x+=speed;
                break;
            case DOWN:
                this.y+=speed;
                break;
        }

        updateHitbox();
    }
    public void setPos(int x, int y){
        this.x = x;
        this.y=y;
    }

    public float getHealthBarFloat(){
        return health/(float) maxHealth;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void kill() {
        alive = false;
        health = 0;
    }
    public boolean isAlive() {
        return alive;
    }

    public void hurt(int dmg){
        this.health -= dmg;
        if(health<=0 && isAlive()){
            alive=false;
            enemyManager.rewardPlayer(enemyType);
        }
    }

    private void updateHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

    public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}
}
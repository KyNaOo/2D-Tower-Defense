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
    private float speed;
    private int reward;
    private boolean alive = true;
    protected int slowTickLimit = 120;
	protected int slowTick = slowTickLimit;
    protected EnemyManager enemyManager;

    public int getLastDir() {
        return lastDir;
    }

    public APlant(float x, float y, int ID,int enemyType, int pv,float speed, int reward, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.reward=reward;
        this.speed = speed;
        this.maxHealth=pv;
        this.health=pv;
        this.enemyType=enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDir = -1;
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
            enemyManager.rewardPlayer(this);
        }
    }

    private void updateHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

    public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}

    public float getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
    }
}
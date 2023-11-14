package src.Entities;

import java.awt.Rectangle;

import static src.help.Constants.Direction.*;


public abstract class APlant {

    private float x, y;
    private Rectangle bounds;
    private int health;
    private int ID;
    private int enemyType;
    private int lastDir;
    private boolean alive = true;

    public int getLastDir() {
        return lastDir;
    }

    protected void setStartHealth() {
        health=src.help.Constants.Plants.GetStartHealth(enemyType);
    }

    public APlant(float x, float y, int ID, int enemyType) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDir = -1;
    }

    public void move(float speed, int dir) {
        lastDir = dir;
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
    }
    public void setPos(int x, int y){
        this.x = x;
        this.y=y;
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
}
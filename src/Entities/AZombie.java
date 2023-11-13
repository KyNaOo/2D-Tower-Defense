package src.Entities;

public abstract class AZombie implements IAlly{
    protected int hp;
    protected int damage;
    protected int speed;
    protected int atkSpeed;
    protected boolean nearBase = false;

    protected AZombie(int hp, int damage, int speed, int atkSpeed) {
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.atkSpeed = atkSpeed;
    }
    public void attack(IEnemy ennemy) {
    }
}

package src.Entities;

public abstract class Zombie {
    protected int hp;
    protected int damage;
    protected int speed;
    protected int atkSpeed;
    protected boolean nearBase = false;
    protected Plant nearestPlant;
    protected boolean isSlow = false;

    public Zombie(int hp, int damage, int speed, int atkSpeed) {
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.atkSpeed = atkSpeed;
    }
    protected void attack(Ally ally){
        if (ally instanceof Plant){
            ally.recieveDamage(this.damage);
        } else if (ally instanceof Base) {
            ally.recieveDamage(this.damage / 2);
        }
    }
    public void recieveDamage(int damage){
        this.hp -= damage;
    }
}

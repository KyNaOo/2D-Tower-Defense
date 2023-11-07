package src.Entities;
public abstract class Plant {
    protected int hp;
    protected int damage;
    protected int attackSpeed;
    protected boolean isAlive = true;

    public Plant(int hp, int damage, int attackSpeed) {
        this.hp = hp;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public void attack(Ennemy ennemy) {
        ennemy.recieveDamage(this.damage);
    }
    public void recieveDamage(int damage){
        if (this.hp - damage < 0){
            this.hp = 0;
            this.isAlive = false;
        } else {
            this.hp -= damage;
        }
    }
    public int getHp() {
        return hp;
    }

    public int getDamage(){
        return damage;
    }

    public int getAttackSpeed(){
        return attackSpeed;
    }
}

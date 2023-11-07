package src.Entities;
public abstract class Plant {
    protected int hp;
    protected int damage;
    protected int attackSpeed;

    public Plant(int hp, int damage, int attackSpeed) {
        this.hp = hp;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public void attack(Zombie zombie) {
        
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

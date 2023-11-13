package src.Entities;

public class Base implements IAlly {
    private int hp;
    private int gold;

    public Base() {
        this.hp = 150;
    }

    @Override
    public void attack(IEnemy ennemy) {
    }
    public void recieveDamage(IEnemy ennemy){

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}

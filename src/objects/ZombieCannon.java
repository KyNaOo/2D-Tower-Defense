package src.objects;

public class ZombieCannon extends Zombie {
    public ZombieCannon(int x, int y, int id) {
        super(x, y, id, 2, 100, 120, 100, 50, "Cannon");
    }
    @Override
    public void upgradeTower(){
        upgradeTower(5, 20, 15);
    }
}

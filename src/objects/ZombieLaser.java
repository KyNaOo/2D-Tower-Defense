package src.objects;

public class ZombieLaser extends Zombie {
    public ZombieLaser(int x, int y, int id) {
        super(x, y, id, 3, 150, 35, 55, 75, "Laser");
    }
    @Override
    public void upgradeTower(){
        upgradeTower(2, 20, 10);
    }
}

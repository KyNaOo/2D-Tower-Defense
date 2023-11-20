package src.objects;


public class ZombieArcher extends Zombie {
    public ZombieArcher(int x, int y, int id) {
        super(x, y, id, 0, 180, 50, 80, 30, "Archer");
    }
    @Override
    public void upgradeTower(){
        upgradeTower(2, 20, 5);
    }
}

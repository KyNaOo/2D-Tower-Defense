package src.objects;


public class ZombieWizard extends Zombie {
    public ZombieWizard(int x, int y, int id) {
        super(x, y, id, 1, 125, 50, 60, 45, "Wizard");
    }
    @Override
    public void upgradeTower(){
        upgradeTower(2, 20, 10);
    }
}

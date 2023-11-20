package src.Entities;
import src.managers.EnemyManager;


public class PlantWarrior extends APlant{
    public PlantWarrior(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID,2, 2000,0.3f,15, em);
    }
}

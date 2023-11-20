package src.objects;
import src.managers.EnemyManager;


public class PlantBoss extends APlant{
    public PlantBoss(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID,3, 1500,1f,25, em);
    }
}

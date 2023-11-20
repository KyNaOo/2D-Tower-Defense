package src.Entities;
import src.managers.EnemyManager;



public class PlantFast extends APlant{
    public PlantFast(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID,1, 300,2f,3, em);
        
    }
}

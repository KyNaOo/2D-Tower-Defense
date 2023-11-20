package src.objects;
import src.managers.EnemyManager;


public class PlantBasic extends APlant{

    public PlantBasic(float x, float y, int ID,  EnemyManager em) {
        super(x, y, ID,0, 300,0.7f,5, em);
        
    }
}

package src.Entities;
import src.managers.EnemyManager;

import static src.help.Constants.Plants.*;

public class PlantFast extends APlant{
    public PlantFast(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, PLANT_FAST, em);
        
    }
}

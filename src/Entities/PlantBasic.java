package src.Entities;
import src.managers.EnemyManager;

import static src.help.Constants.Plants.*;
public class PlantBasic extends APlant {

    public PlantBasic(float x, float y, int ID,  EnemyManager em) {
        super(x, y, ID, PLANT_BASIC, em);
        
    }
}

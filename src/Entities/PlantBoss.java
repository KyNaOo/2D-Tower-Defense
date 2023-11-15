package src.Entities;
import src.managers.EnemyManager;

import static src.help.Constants.Plants.*;
public class PlantBoss extends APlant{
    public PlantBoss(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, PLANT_BOSS, em);
        
    }
}

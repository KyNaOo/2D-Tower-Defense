package src.Entities;
import src.managers.EnemyManager;

import static src.help.Constants.Plants.*;
public class PlantWarrior extends APlant{
    public PlantWarrior(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, PLANT_WARRIOR, em);
        
    }
}

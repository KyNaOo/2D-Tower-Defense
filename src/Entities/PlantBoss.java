package src.Entities;
import static src.help.Constants.Plants.*;
public class PlantBoss extends APlant{
    public PlantBoss(float x, float y, int ID) {
        super(x, y, ID, PLANT_BOSS);
        setStartHealth();
    }
}

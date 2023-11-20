package src.objects;

public interface IAlly {
    int getDamages();
    float getRange();
    float getCooldown();
    void update();
    boolean isCooldownOver();
    void resetCooldown();
    int getId();
    void upgradeTower();
    int getX();
    int getY();
    int getTowerType();
    int getTier();
    int getDmg();

    int getCost();

    String getName();
}

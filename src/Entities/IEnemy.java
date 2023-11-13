package src.Entities;

public interface IEnemy {
    public void recieveDamage(int damage);
    public void attack(IAlly ally);
}

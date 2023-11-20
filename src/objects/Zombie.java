package src.objects;

public class Zombie implements IAlly {

	protected int x, y, id, towerType, cdTick, dmg, cost;
	protected float range, cooldown;
	protected String name;

	protected int tier;

	public Zombie(int x, int y, int id, int towerType, float range, float cooldown, int dmg, int cost, String name) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		this.range = range;
		this.cooldown = cooldown;
		this.dmg = dmg;
		this.cost = cost;
		this.name = name;
		tier = 1;
	}
	public void upgradeTower(int dmg, float range, float cooldown){
		this.tier++;
		this.dmg += dmg;
		this.range += range;
		this.cooldown -= cooldown;
	}
	public void upgradeTower() {
	}

	public void update() {
		cdTick++;
	}

	public boolean isCooldownOver() {

		return cdTick >= cooldown;
	}

	public void resetCooldown() {
		cdTick = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTowerType() {
		return towerType;
	}
	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getTier() {
		return tier;
	}
	public int getCost(){
		return cost;
	}

	@Override
	public String getName() {
		return name;
	}

}

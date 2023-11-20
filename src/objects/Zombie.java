package src.objects;

public class Zombie implements IAlly {

	private int x, y, id, towerType, cdTick, dmg;
	private float range, cooldown;

	private int tier;

	public Zombie(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	} 	

	public void upgradeTower() {
		this.tier++;

		switch (towerType) {
		case 0:
			dmg += 2;
			range += 20;
			cooldown -= 5;
			break;
		case 1:
			range += 20;
			cooldown -= 10;
			break;
		case 2:
			dmg += 5;
			range += 20;
			cooldown -= 15;
			break;
		case 3 :
			range += 20;
			cooldown -= 10;
		}
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

	private void setDefaultCooldown() {
		switch (towerType){
			case 0:
				cooldown =50;
				break;
			case 1:
				cooldown =50;
				break;
			case 2:
				cooldown =120;
				break;
			case 3:
				cooldown =35;
				break;
		}
	}

	private void setDefaultRange() {
		switch (towerType){
			case 0:
				range =180;
				break;
			case 1:
				range =125;
				break;
			case 2:
				range =100;
				break;
			case 3:
				range =150;
				break;
		}
	}

	private void setDefaultDmg() {
		switch (towerType){
			case 0:
				dmg =80;
				break;
			case 1:
				dmg =60;
				break;
			case 2:
				dmg =100;
				break;
			case 3:
				dmg =55;
				break;
		}
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
	public int getDamages(){
			switch (towerType){
				case 0:
					return 80;
				case 1:
					return 60;
				case 2:
					return 100;
				case 3:
					return 100;
			}
			return 0;
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
		switch (towerType){
			case 0:
				return 30;
			case 1:
				return 45;
			case 2:
				return 50;
			case 3:
				return 75;
		}
		return 0;
	}

	@Override
	public String getName() {
		switch (towerType){
			case 0:
				return "Archer";
			case 1:
				return "Wizard";
			case 2:
				return "Cannon";
			case 3:
				return "Laser";
		}
		return "";
	}

}

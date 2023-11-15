package src.help;

public class Constants {

    public static class Towers {
		public static final int ARCHER = 0;
		public static final int WIZARD = 1;
		public static final int CANNON = 2;
        public static final int LASER = 3;

		public static String GetName(int towerType) {
			switch (towerType) {
			case CANNON:
				return "Cannon";
			case ARCHER:
				return "Archer";
			case WIZARD:
				return "Wizard";
            case LASER:
				return "Laser";
			}
			return "";
		}

        public static int GetStartDmg(int towerType) {
            	switch (towerType) {
			case CANNON:
				return 40;
			case ARCHER:
				return 30;
			case WIZARD:
				return 35;
            case LASER:
				return 50;
			}
            return 0;
        }

        public static float GetDefaultRange(int towerType) {
             	switch (towerType) {
			case CANNON:
				return 100;
			case ARCHER:
				return 150;
			case WIZARD:
				return 125;
            case LASER:
				return 180;
			}
            return 0;
            
        }

        public static float GetDefaultCooldown(int towerType) {
             	switch (towerType) {
			case CANNON:
				return 10;
			case ARCHER:
				return 5;
			case WIZARD:
				return 7;
            case LASER:
				return 6;
			}
            return 0;
            
        }
	}
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Plants{
        public static final int PLANT_BASIC = 0;
        public static final int PLANT_FAST = 1;
        public static final int PLANT_WARRIOR = 2;
        public static final int PLANT_BOSS = 3;

        public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case PLANT_BASIC:
				return 0.5f;
			case PLANT_BOSS:
				return 0.5f;
			case PLANT_WARRIOR:
				return 0.3f;
			case PLANT_FAST:
				return 1f;
			}
			return 0;
		}

        public static int GetStartHealth(int enemyType){
            	switch (enemyType) {
			case PLANT_BASIC:
				return 100;
			case PLANT_BOSS:
				return 500;
			case PLANT_WARRIOR:
				return 300;
			case PLANT_FAST:
				return 85;
			}
            return 0;
        }
    }
    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
        public static final int STONE_TILE = 3;
        public static final int END_TILE = 4;
    }

    public static class Projectiles{
        public static final int ARROW=0;
        public static final int BOMB=1;
        public static final int CHAINS=2;
        public static final int LAZER=3;

        public static float GetSpeed(int type){
            switch (type) {
                case ARROW:
                     return 3f;
                case BOMB:
                     return 1f;
                case CHAINS:
                     return 2f;
                case LAZER:
                     return 4f;
            }
            return 0f;
        }
    }
    
}

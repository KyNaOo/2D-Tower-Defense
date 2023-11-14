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

    }
    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
        public static final int STONE_TILE = 3;
        public static final int END_TILE = 4;
    }
}

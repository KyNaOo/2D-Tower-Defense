package src.help;

public class Constants {
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
        public static final int STONE_TILE = 3;
        public static final int END_TILE = 4;
        public static final int BLUEFLOWER = 5;
        public static final int REDFLOWER = 6;
        public static final int GREENFLOWER = 7;
        public static final int PINKFLOWER = 8;
    }

    public static class Projectiles{
        public static final int ARROW=0;
        public static final int BOMB=1;
        public static final int CHAINS=2;
        public static final int LAZER=3;

        public static float GetSpeed(int type){
            switch (type) {
                case ARROW:
                     return 8f;
                case BOMB:
                     return 6f;
                case CHAINS:
                     return 7f;
                case LAZER:
                     return 8f;
            }
            return 0f;
        }
    }
    
}

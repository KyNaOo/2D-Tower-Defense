package src.main;

public enum GameStates {
    PLAYING, MENU, QUIT;
    public static GameStates gameStates = MENU;
    public static void setGameState(GameStates states){
        gameStates = states;
    }
}
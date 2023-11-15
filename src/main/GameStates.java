package src.main;

public enum GameStates {

	PLAYING, MENU, SETTINGS, GAME_OVER;

	public static GameStates gameState = MENU;

	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}

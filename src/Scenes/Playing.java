package src.Scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import src.Entities.APlant;
import src.help.LevelBuild;
import src.help.LoadSave;
import src.main.Game;
import src.managers.EnemyManager;
import src.managers.TileManager;
import src.managers.TowerManager;
import src.managers.WaveManager;
import src.objects.Tower;
import src.ui.ActionBar;
import static src.help.Constants.Tiles.STONE_TILE;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private ActionBar actionBar;
	private TileManager tileManager;
	private int mouseX, mouseY;
	private WaveManager waveManager;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private Tower selectedTower;

	public Playing(Game game) {
		super(game);

		loadDefaultLevel();
		tileManager = new TileManager();
		actionBar = new ActionBar(0, 640, 640, 100, this);

		enemyManager = new EnemyManager(this);
		towerManager = new TowerManager(this);
		waveManager = new WaveManager(this);

	}

	public WaveManager getWaveManager() {
		return waveManager;
	}

	private void loadDefaultLevel() {
		lvl = LevelBuild.getLevelData();
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void update() {
		enemyManager.update();
		towerManager.update();
		waveManager.update();
		if (isTimeForNewEnemy()){
			spawnEnemy();
		}
		if (isAllEnemiesDead()){
			if (isThereMoreWaves()){
				waveManager.startWaveTimer();
				if (isWaveTimerOver()){
					waveManager.increaseWaveIndex();
					enemyManager.getEnemies().clear();
					waveManager.resetEnemyIndex();
				}
			}
			if (isTimeForNewEnemy()){
				spawnEnemy();
			}
		}
	}
	private void spawnEnemy() {
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}

	private boolean isTimeForNewEnemy() {
		if (waveManager.isTimeForNewEnemy()){
			if (waveManager.isThereMoreEnemiesInWave()){
				return true;
			}
		}
		return false;
	}

	private boolean isWaveTimerOver() {
		return waveManager.isWaveTimerOver();
	}

	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();
	}

	private boolean isAllEnemiesDead() {
		if (waveManager.isThereMoreEnemiesInWave()){
			return false;
		}
		for (APlant plant : enemyManager.getEnemies()){
			if (plant.isAlive()){
				return false;
			}
		}
		return true;
	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}

	@Override
	public void render(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
			}
		}
		//drawLevel(g);
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		drawWaveInfos(g);
		drawSelectedTower(g);


	}

	private void drawWaveInfos(Graphics g) {
	}

	private void drawSelectedTower(Graphics g) {
		if (selectedTower != null)
			g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
	}

	/*private void drawLevel(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}*/

	/*private BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}*/
	

	@Override
	public void mouseClicked(int x, int y) {
	
		if (y >= 640)
		actionBar.mouseClicked(x, y);
	else {
		// Above 640y
		if (selectedTower != null) {
			// Trying to place a tower
			if (isTileStone(mouseX, mouseY)) {
				if (getTowerAt(mouseX, mouseY) == null) {
					towerManager.addTower(selectedTower, mouseX, mouseY);
					selectedTower = null;
				}
			}
		} else {
			// Not trying to place a tower
			// Checking if a tower exists at x,y
			Tower t = getTowerAt(mouseX, mouseY);
			actionBar.displayTower(t);
		}
	}

	}

	private boolean isTileStone(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == STONE_TILE;
	}

	private Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			selectedTower=null;
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			actionBar.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	public int getTileType(int x, int y) {
		int xCord = x/32;
		int yCord = y/32;
		if (xCord<0 || xCord>19){
			return 0;
		}
		if (yCord<0 || yCord>19){
			return 0;
		}
		int id = lvl[y/32][x/32];
		return game.getTileManager().getTile(id).getTileType();
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
}
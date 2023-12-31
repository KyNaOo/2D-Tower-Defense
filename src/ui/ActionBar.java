package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import src.Scenes.Playing;
import src.objects.*;

import static src.main.GameStates.*;

public class ActionBar extends Bar {

	private Playing playing;
	private MyButton bMenu;

	private MyButton[] towerButtons;
	private Zombie selectedZombie;
	private IAlly displayedZombie;
	private DecimalFormat formatter;
	private boolean showTowerCost;
	private int towerCostType;
	private MyButton sellTower, upgradeTower;
	private int gold = 100;
	private int lives = 25;

	public int getLives() {
		return lives;
	}
	public void removeOneLife(){
		lives--;
		if (lives<=0){
			SetGameState(GAME_OVER);
		}
	}

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		formatter = new DecimalFormat("0.0");
		initButtons();
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", 2, 642, 100, 30);

		towerButtons = new MyButton[4];
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		for (int i = 0; i < towerButtons.length; i++) 
			towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);

		sellTower = new MyButton("Sell", 420, 702, 80, 25);
		upgradeTower = new MyButton("Upgrade", 545, 702, 80, 25);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		for (MyButton b : towerButtons) {
			g.setColor(Color.white);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}
	}

	public void draw(Graphics g) {

		// Background
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);

		drawDisplayedTower(g);

		drawWaveInfo(g);

		drawGoldAmont(g);
		if (showTowerCost){
			drawTowerCost(g);
		}
		g.setColor(Color.black);
		g.drawString("Lives: "+lives, 1, 725);
	}

	private void drawTowerCost(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(350, 650, 120, 50);
		g.setColor(Color.black);
		g.drawRect(350, 650, 120, 50);
		g.drawString(getTowerCostName(), 355, 670);
		g.drawString("Cost: " + getTowerCostCost() + "g", 355, 695);

	
		if (isTowerCostMoreThanCurrentGold()) {
			g.setColor(Color.RED);
			g.drawString("Can't Afford", 270, 725);

		}

	}

	private boolean isTowerCostMoreThanCurrentGold() {
		return getTowerCostCost() > gold;
	}

	private int getTowerCostCost() {
		switch (towerCostType){
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

	private String getTowerCostName() {
		switch (towerCostType) {
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

	private void drawGoldAmont(Graphics g) {
		g.drawString("Gold: " + gold,110, 725);
	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		drawWavesLeftInfo(g);
	}

	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();

		g.drawString("Wave " + (current + 1) + " / " + size, 275, 725);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else {

			if (displayedZombie != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTowerClicked();

					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedZombie.getTier() < 3 && gold >= getUpgradeAmount(displayedZombie)) {
					upgradeTowerClicked();
					return;
				}
			}

			for (MyButton b : towerButtons) {
				if (b.getBounds().contains(x, y)) {
					if (!isGoldEnoughForTower(b.getId()))
						return;
					switch (b.getId()){
						case 0:
							selectedZombie = new ZombieArcher(0, 0, -1);
							break;
						case 1:
							selectedZombie = new ZombieWizard(0, 0, -1);
							break;
						case 2:
							selectedZombie = new ZombieCannon(0, 0, -1);
							break;
						case 3:
							selectedZombie = new ZombieLaser(0, 0, -1);
							break;
					}
					playing.setSelectedTower(selectedZombie);
					return;
				}
			}
		}

	}

	private boolean isGoldEnoughForTower(int towerType) {
		switch (towerType){
			case 0:
				return gold >=30;
			case 1:
				return gold >=45;
			case 2:
				return gold >=50;
			case 3:
				return gold >=75;
		}
		return false;
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		showTowerCost = false;
		sellTower.setMouseOver(false);
		upgradeTower.setMouseOver(false);

		for (MyButton b : towerButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {

			if (displayedZombie != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTower.setMouseOver(true);
					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedZombie.getTier() < 3) {
					upgradeTower.setMouseOver(true);
					return;
				}
			}

			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					showTowerCost = true;
					towerCostType = b.getId();
					return;
				}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else {

			if (displayedZombie != null) {
				if (sellTower.getBounds().contains(x, y)) {
					sellTower.setMousePressed(true);
					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedZombie.getTier() < 3) {
					upgradeTower.setMousePressed(true);
					return;
				}
			}

			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
		}

	}


	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for (MyButton b : towerButtons)
			b.resetBooleans();
		sellTower.resetBooleans();
		upgradeTower.resetBooleans();

	}
	private void drawDisplayedTower(Graphics g) {
		if (displayedZombie != null) {
			g.setColor(Color.white);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedZombie.getTowerType()], 420, 650, 50, 50, null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + displayedZombie.getName(), 480, 660);
			g.drawString("ID: " + displayedZombie.getId(), 480, 675);
			g.drawString("Tier: " + displayedZombie.getTier(), 560, 660);
			drawDisplayedTowerBorder(g);
			drawDisplayedTowerRange(g);

			// Sell button
			sellTower.draw(g);
			drawButtonFeedback(g, sellTower);

			// Upgrade Button
			if (displayedZombie.getTier() < 3 && gold >= getUpgradeAmount(displayedZombie)) {
				upgradeTower.draw(g);
				drawButtonFeedback(g, upgradeTower);
			}

			if (sellTower.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("Sell for: " + getSellAmount(displayedZombie) + "g", 480, 695);
			} else if (upgradeTower.isMouseOver() && gold >= getUpgradeAmount(displayedZombie)) {
				g.setColor(Color.blue);
				g.drawString("Upgrade for: " + getUpgradeAmount(displayedZombie) + "g", 480, 695);
			}

		}

	}

	private int getUpgradeAmount(IAlly displayedZombie) {
		return (int) (displayedZombie.getCost()*0.3f);
	}

	private int getSellAmount(IAlly displayedZombie) {
		int upgradeCost = (displayedZombie.getTier() - 1) * getUpgradeAmount(displayedZombie);
		upgradeCost *= 0.5f;
		return displayedZombie.getCost() / 2 + upgradeCost;
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.yellow);
		g.drawOval(displayedZombie.getX()+16-(int)(displayedZombie.getRange()*2)/2, displayedZombie.getY()+16-(int)(displayedZombie.getRange()*2)/2, (int) displayedZombie.getRange()*2, (int) displayedZombie.getRange()*2);
	}

	private void sellTowerClicked() {
		playing.removeTower(displayedZombie);
		gold += displayedZombie.getCost() / 2;

		int upgradeCost = (displayedZombie.getTier() - 1) * getUpgradeAmount(displayedZombie);
		upgradeCost *= 0.5f;
		gold += upgradeCost;

		displayedZombie = null;

	}

	private void upgradeTowerClicked() {
		playing.upgradeTower(displayedZombie);
		gold -= getUpgradeAmount(displayedZombie);

	}

	private void drawDisplayedTowerBorder(Graphics g) {

		g.setColor(Color.CYAN);
		g.drawRect(displayedZombie.getX(), displayedZombie.getY(), 32, 32);

	}

	public void displayTower(IAlly t) {
		displayedZombie = t;
	}

	public void payForTower(int towerType) {
		switch (towerType){
			case 0:
				this.gold -=30;
				break;
			case 1:
				this.gold -=45;
				break;
			case 2:
				this.gold -=50;
				break;
			case 3:this.gold -=70;

				break;
		}
	}

	public void addGold(int getReward) {
		this.gold+= getReward;
	}

	public void resetEveryThing() {
		lives = 25;
		towerCostType = 0;
		showTowerCost = false;
		gold = 100;
		selectedZombie = null;
		displayedZombie = null;
	}
}

package src.ui;

import static src.main.GameStates.MENU;
import static src.main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import src.Scenes.Playing;
import src.help.Constants;
import src.help.Constants.Towers;
import src.objects.Tower;

public class ActionBar extends Bar {

	private Playing playing;
	private MyButton bMenu;

	private MyButton[] towerButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	private DecimalFormat formatter;
	private boolean showTowerCost;
	private int towerCostType;
	private int gold = 100;


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
	}

	private void drawTowerCost(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(350, 650, 120, 50);
		g.setColor(Color.black);
		g.drawRect(350, 650, 120, 50);
		g.drawString(getTowerCostName(), 355, 670);
		g.drawString("Cost:"+ getTowerCostCost() + "g", 355, 695);
	}

	private int getTowerCostCost() {
		return src.help.Constants.Towers.GetTowerCost(towerCostType);
	}

	private String getTowerCostName() {
		return src.help.Constants.Towers.GetName(towerCostType);
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
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					if (!isGoldEnoughForTower(b.getId())){
						return;
					}
					selectedTower = new Tower(0,0,-1,b.getId());
					playing.setSelectedTower(selectedTower);
					return;
				}
			}
		}

	}

	private boolean isGoldEnoughForTower(int towerType) {
		return gold >= Constants.Towers.GetTowerCost(towerType);

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		showTowerCost = false;
		for (MyButton b : towerButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
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
		else
			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for (MyButton b : towerButtons)
			b.resetBooleans();

	}

	private void drawDisplayedTower(Graphics g) {
		if (displayedTower != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + Towers.GetName(displayedTower.getTowerType()), 490, 660);
			g.drawString("ID: " + displayedTower.getId(), 490, 675);
			drawDisplayedTowerRange(g);
		}

	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.white);
		g.drawOval(displayedTower.getX()+16-(int)(displayedTower.getRange()*2)/2, displayedTower.getY()+16-(int)(displayedTower.getRange()*2)/2, (int)displayedTower.getRange()*2, (int)displayedTower.getRange()*2);
	}

	public void displayTower(Tower t) {
		displayedTower = t;
	}

	public void payForTower(int towerType) {
		this.gold -= Constants.Towers.GetTowerCost(towerType);
	}

	public void addGold(int getReward) {
		this.gold+= getReward;
	}
}

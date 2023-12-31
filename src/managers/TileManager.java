package src.managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.help.LoadSave;
import src.objects.Tile;
import static src.help.Constants.Tiles.*;

public class TileManager {

	public Tile GRASS, WATER, ROAD, STONE, FLOWER1,FLOWER2, FLOWER3, FLOWER4;
	private BufferedImage atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();

	public TileManager() {

		loadAtalas();
		createTiles();

	}

	private void createTiles() {

		int id = 0;
		tiles.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));
		tiles.add(WATER = new Tile(getSprite(0, 0), id++, WATER_TILE));
		tiles.add(ROAD = new Tile(getSprite(8, 0), id++, ROAD_TILE));
		tiles.add(STONE = new Tile(getSprite(6,2), id++, STONE_TILE));
		tiles.add(STONE = new Tile(getSprite(2,2), id++, END_TILE));
		tiles.add(FLOWER1 = new Tile(getSprite(4,0),id++, BLUEFLOWER));
		tiles.add(FLOWER1 = new Tile(getSprite(5,0),id++, REDFLOWER));
		tiles.add(FLOWER1 = new Tile(getSprite(6,0),id++, GREENFLOWER));
		tiles.add(FLOWER1 = new Tile(getSprite(7,0),id++, PINKFLOWER));


	}

	private void loadAtalas() {

		atlas = LoadSave.getSpriteAtlas();

	}

	public Tile getTile(int id) {
		return tiles.get(id);
	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

}

package src.Scenes;


import src.main.Game;
import src.help.LevelBuild;
import src.managers.TileManager;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;


    public Playing(Game game) {
        super(game);
        lvl=LevelBuild.getLevelData();
        tileManager=new TileManager();
    }

    @Override
    public void render(Graphics g) {
        for(int y=0; y<lvl.length; y++) {
             for(int x=0; x<lvl[y].length;x++){
                int id =lvl[y][x];
                g.drawImage(tileManager.getSprite(id), y*32, x*32,null);
             }
        }
    }
}

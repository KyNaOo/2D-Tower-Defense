package src.managers;

import src.Scenes.Playing;
import src.events.Wave;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawTickLimit = 60;
    private int enemySpawnTick = enemySpawTickLimit;
    private int enemyIndex, waveIndex;
    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,1,3,2,0,1,0,1,1,0))));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }
    public void update(){
        if (enemySpawnTick<enemySpawTickLimit)
            enemySpawnTick++;
    }
    public int getNextEnemy(){
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawTickLimit;
    }
    public boolean isThereMoreEnemiesInWave(){
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }
}

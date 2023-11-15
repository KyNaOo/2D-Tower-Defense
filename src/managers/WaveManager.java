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
    private boolean waveStartTimer, waveTickTimerOver;
    private int waveTickLimit = 5 * 60;
    private int waveTick = 0;
    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0))));
        waves.add(new Wave(new ArrayList<>(Arrays.asList(0,1,1,2,1,0,2,1,2,1,2,0,0,1,1,1,1,0,0,1,0))));
        waves.add(new Wave(new ArrayList<>(Arrays.asList(2,2,2,2,0,1,1,1,1,0,3,3,1,1,1))));
        waves.add(new Wave(new ArrayList<>(Arrays.asList(3,2,2,3,3,3,2,2,1,1,1,1,1,0,0,0,1,0,2,3,3,3,2,2,0,1,1,1))));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }
    public void update(){
        if (enemySpawnTick<enemySpawTickLimit)
            enemySpawnTick++;
        if (waveStartTimer){
            waveTick++;
            if (waveTick>= waveTickLimit){
                waveTickTimerOver = true;
            }
        }
    }
    public void increaseWaveIndex(){
        waveIndex++;
        waveTickTimerOver = false;
        waveStartTimer = false;
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

    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();
    }
    public void startWaveTimer(){
        waveStartTimer = true;
    }

    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    public int getWaveIndex() {
        return waveIndex;
    }
    public float getTimeLeft(){
        float tickLeft = waveTickLimit - waveTick;
        return tickLeft / 60.0f;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }
    public void reset(){
        waves.clear();
        createWaves();
        enemyIndex = 0;
        waveIndex = 0;
        waveStartTimer = false;
        waveTickTimerOver = false;
        waveTick = 0;
        enemySpawnTick = enemySpawTickLimit;
    }
}

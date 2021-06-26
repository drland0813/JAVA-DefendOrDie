package data;

import org.newdawn.slick.opengl.Texture;

public class WaveManager {
	private float timeSinceLastWave,  timeBetweenEnemies;
	private int waveNumber, enemiesPerWave;
	private Enemy[] enemyTypes;
	private Wave currentWave;
	private item aItem;
	private Texture textureItem;


	public WaveManager(Enemy[] enemyTypes, int enemiesPerWave, int timeBetweenEnemies) {
		this.enemyTypes = enemyTypes;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.enemiesPerWave = enemiesPerWave;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.currentWave = null;
		newWave();
		
	}

	public void update() {
		if (!currentWave.isCompleted()) {
			currentWave.update();
		}
		else 
			newWave();	
	}

	public void newWave() {
		Player.modifyEnemiesPerWave(0);
		currentWave = new Wave(enemyTypes, timeBetweenEnemies, enemiesPerWave + waveNumber);
		Player.modifyEnemiesPerWave(currentWave.getEnemiesPerWave());
		waveNumber ++;
		
		System.out.println("Begining Wave "+ waveNumber);
	}
	public Wave getCurrentWave() {
		return currentWave;
	}
	

	
	public int getWave() {
		return waveNumber;
	}
	
}

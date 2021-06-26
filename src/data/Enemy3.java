package data;

import static helpers.Artist.*;

public class Enemy3 extends Enemy {

	public Enemy3(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture(QuickLoadEnemy("enemy3"));
		this.setHealth(1000);
		this.setSpeed(90);
	}
}

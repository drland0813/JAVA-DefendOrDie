package data;

import static helpers.Artist.*;

public class Enemy4 extends Enemy {

	public Enemy4(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture(QuickLoadEnemy("enemy4"));
		this.setHealth(1000);
		this.setSpeed(90);
	}
}

package data;
import static helpers.Artist.*;
public class Enemy2 extends Enemy{

	public Enemy2(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture(QuickLoadEnemy("enemy2"));
		this.setSpeed(80);
		this.setHealth(1000);
	}

}

package data;
import static helpers.Artist.*;
public class Enemy1 extends Enemy {
	public Enemy1(int tileX, int tileY, TileGrid grid) {
		super(tileX, tileY, grid);
		this.setTexture(QuickLoadEnemy("enemy1"));
		this.setHealth(5000);
		this.setSpeed(70);
	}

}

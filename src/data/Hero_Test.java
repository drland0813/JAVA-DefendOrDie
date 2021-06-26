package data;

import static helpers.Artist.*;
import static helpers.Artist.TILE_SIZE;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hero_Test extends Hero {
	public Hero_Test(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX,tileY,grid,enemies);
		this.setTexture(QuickLoad("item"));
		this.setRange(1000);
		this.setWidth(65);
		this.setHeight(65);
	}
	@Override
	public void skill(Enemy target) {

	}
	@Override
	public void Sound() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Animation(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

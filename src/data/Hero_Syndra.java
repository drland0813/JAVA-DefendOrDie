package data;

import static helpers.Artist.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class Hero_Syndra extends Hero{

	public Hero_Syndra(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX, tileY, grid, enemies);
		this.setTexture(QuickLoad("syndra"));
		this.showTexture=QuickLoadHero("syndra");
		this.nameString = "Syndra";
	}

	@Override
	public void skill(Enemy target) {
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

	@Override
	public void Sound() {
		// TODO Auto-generated method stub
		
	}

}

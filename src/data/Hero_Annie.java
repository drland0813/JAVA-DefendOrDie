package data;

import static helpers.Artist.QuickLoadHero;
import java.util.concurrent.CopyOnWriteArrayList;


public class Hero_Annie extends Hero{

	public Hero_Annie(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX, tileY, grid, enemies);
		this.setTexture(QuickLoadHero("annie"));
		this.showTexture=QuickLoadHero("annie");
		this.nameString = "Annie";
		// TODO Auto-generated constructor stub
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

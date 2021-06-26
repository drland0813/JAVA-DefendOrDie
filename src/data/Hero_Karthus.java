package data;

import static helpers.Artist.QuickLoadHero;

import java.util.concurrent.CopyOnWriteArrayList;

import helpers.Music;

public class Hero_Karthus extends Hero {

	private int count;
	public Hero_Karthus(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX,tileY,grid,enemies);
		this.texture = QuickLoadHero("karthus1");
		this.sound = new Music("/Music/karthus.mp3");
		this.count = 0;
	}
	
	@Override
	public void skill(Enemy target) {
		
		target.damage(50);
		target.reduceHiddenHealth(50);
		target.setSpeed(20);
	}

	@Override
	public void Sound() {
		sound.Play(-20.0f);
	}

	@Override
	public void Animation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (alive) {
			if (!targeted || target.getHiddenHealth() < 0) {
				target = acquireTarget();
			}
			else {
				skill(target);
				
			}
			if(target==null || target.isAlive() == false) {
				targeted = false;
			}
		}
		
	}
}

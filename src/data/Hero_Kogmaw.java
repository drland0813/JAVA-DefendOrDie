package data;

import java.util.concurrent.CopyOnWriteArrayList;

import helpers.Music;

import static helpers.Artist.*;

public class Hero_Kogmaw extends Hero{
	private Music sound = new Music("/Music/kogmaw.mp3");
	public Hero_Kogmaw(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX,tileY,grid,enemies);
		this.setTexture(QuickLoadHero("kogmaw1"));
		this.setRange(1000);
		this.setWidth(65);
		this.setHeight(65);
	}
	@Override
	public void skill(Enemy target) {
		int xne =(int) target.getX() / TILE_SIZE;
		int yne =(int) target.getY() / TILE_SIZE;
		System.out.println("Toa do E: "+xne+"  " + yne);
		//DrawQuadTex(QuickLoad("skillkarthus"), xne*TILE_SIZE, yne*TILE_SIZE, 16, 200);
		target.setSpeed(20);
		target.damage(50);
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
		// TODO Auto-generated method stub
		
	}
}

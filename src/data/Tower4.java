package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.QuickLoadTower;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class Tower4 extends HeroShoot {
	public Tower4(HeroShootType type, Tile starTile, CopyOnWriteArrayList<Enemy> enemies ) {
		super(type, starTile, enemies);
		this.nameString = "Tower 4";
		this.showTexture = QuickLoadTower("tower4");
	}
	
	@Override 
	public void shoot(Enemy target) {
		super.projectiles.add(new Projectile_Tower4(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

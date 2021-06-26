package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.QuickLoadTower;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class Tower7 extends HeroShoot {
	public Tower7(HeroShootType type, Tile starTile, CopyOnWriteArrayList<Enemy> enemies ) {
		super(type, starTile, enemies);
		this.nameString = "Tower 7";
		this.showTexture = QuickLoadTower("tower7");
	}
	
	@Override 
	public void shoot(Enemy target) {
		super.projectiles.add(new Projectile_Tower7(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

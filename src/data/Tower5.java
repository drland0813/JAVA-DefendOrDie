package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.QuickLoadTower;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class Tower5 extends HeroShoot {
	public Tower5(HeroShootType type, Tile starTile, CopyOnWriteArrayList<Enemy> enemies ) {
		super(type, starTile, enemies);
		this.nameString = "Tower 5";
		this.showTexture = QuickLoadTower("tower5");
	}
	
	@Override 
	public void shoot(Enemy target) {
		super.projectiles.add(new Projectile_Tower5(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

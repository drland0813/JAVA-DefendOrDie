package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.QuickLoadTower;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class Tower_Red extends HeroShoot {
	public Tower_Red(HeroShootType type, Tile starTile, CopyOnWriteArrayList<Enemy> enemies ) {
		super(type, starTile, enemies);
		this.nameString = "Fire and Fire";
		this.showTexture = QuickLoadTower("towerred");
	}
	
	@Override 
	public void shoot(Enemy target) {
		super.projectiles.add(new Projectile_Lucian(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

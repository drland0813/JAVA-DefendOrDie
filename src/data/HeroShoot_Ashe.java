package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.QuickLoadHero;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class HeroShoot_Ashe extends HeroShoot {
	public HeroShoot_Ashe(HeroShootType type, Tile starTile, CopyOnWriteArrayList<Enemy> enemies ) {
		super(type, starTile, enemies);
		this.nameString = "Ice Gun";
		this.showTexture = QuickLoad("ashe");
	}
	
	@Override 
	public void shoot(Enemy target) {
		super.projectiles.add(new ProjectileIe_Ashe(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

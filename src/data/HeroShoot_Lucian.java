package data;

import static helpers.Artist.*;
import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class HeroShoot_Lucian extends HeroShoot {
	
	public HeroShoot_Lucian(HeroShootType type, Tile starTile, CopyOnWriteArrayList<Enemy> enemies)
	{
		super(type, starTile, enemies);
		this.nameString = "Machine Gun";
		this.showTexture = QuickLoad("MG");
		
	}
	
	@Override 
	public void shoot(Enemy target) {
		super.projectiles.add(new Projectile_Lucian(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
		//super.target.setX(target.getX() + 64);
		//super.target.setY(target.getY() - 64);
	}
}


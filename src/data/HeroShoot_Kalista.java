package data;

import java.util.concurrent.CopyOnWriteArrayList;
import static helpers.Artist.*;

public class HeroShoot_Kalista extends HeroShoot{

	public HeroShoot_Kalista(HeroShootType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
		this.setWidth(110);
		this.setHeight(110);
		this.showTexture=QuickLoadHero("vayne");
		this.nameString = "Kalista";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(Enemy target) {
		// TODO Auto-generated method stub
		super.projectiles.add(new Projectile_Lucian(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}

}

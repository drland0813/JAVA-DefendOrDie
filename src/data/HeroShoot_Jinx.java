package data;

import static helpers.Artist.QuickLoadHero;

import java.util.concurrent.CopyOnWriteArrayList;

public class HeroShoot_Jinx extends HeroShoot{

	public HeroShoot_Jinx(HeroShootType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
		this.setWidth(110);
		this.setHeight(110);
		this.showTexture=QuickLoadHero("jinx");
		this.nameString = "Jinx";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(Enemy target) {
		// TODO Auto-generated method stub
		super.projectiles.add(new Projectile_Lucian(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}

}

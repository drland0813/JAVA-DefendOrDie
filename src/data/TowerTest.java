package data;

import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.opengl.Texture;

public class TowerTest extends HeroShoot {

	public TowerTest(HeroShootType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(Enemy target) {
		super.projectiles
				.add(new ProjectileTest(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

package data;
import static helpers.Artist.*;
import static helpers.Clock.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SuperEnemy extends Enemy{
	public Enemy target;
	private int x, y, range;
	public boolean targeted;
	public ArrayList<Projectile> projectiles;
	private CopyOnWriteArrayList<Enemy> enemies;
	private float timeSinceLastShot, firingspeed;
	public SuperEnemy(int tileX, int tileY,TileGrid grid,CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX, tileY, grid);
		this.enemies = enemies;
		this.setTexture(QuickLoadSupE("superE_1"));
		this.setHealth(50);
		this.setSpeed(10);
		this.targeted = false;
		this.timeSinceLastShot = 1f;
		this.projectiles = new ArrayList<Projectile>();
		this.firingspeed = 0.5f;
	}
	
	public void Skill(Enemy e) {
		System.out.println("Ban");
		projectiles.add(new Projectile_Lucian(ProjectileType.Pro_Lucian, e, this.getX(), this.getY(), 32, 32));
		target.reHealth(100);
		
	}
	private Enemy acquireTarget() {
		Enemy closestEnemy = null;
		float closeDistance = 10000;
		for (Enemy e : enemies) {
			if (isRange(e) && findDistance(e) < closeDistance && e.getHiddenHealth() > 0) {
				closeDistance = findDistance(e);
				closestEnemy = e;
			}
		}
		if (closestEnemy != null) {
			targeted = true;
		}
		return closestEnemy;
	}
	
	private boolean isRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}
	
	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	public void updateEnemiesList(CopyOnWriteArrayList<Enemy> newList) {
		enemies = newList;
	}
	public void updateforE() {
		super.draw();
		super.update();
		//updateEnemiesList(enemies);
		if (!targeted || target.getHiddenHealth() > 0) {
			target = acquireTarget();
		}
		else {
			if (timeSinceLastShot > firingspeed) {
				Skill(target);
				timeSinceLastShot = 0;
			}
		}
		if(target==null || target.isAlive() == false) {
			targeted = false;
		}
		timeSinceLastShot += Delta();
		for (Projectile p : projectiles)
			p.update();
	}
}

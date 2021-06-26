package data;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {
	private float x, y, timeSinceLastShot, firingspeed, angle;
	private int width, height, damage, range;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private CopyOnWriteArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted;

	public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, CopyOnWriteArrayList<Enemy> enemies) {
		this.baseTexture = QuickLoad("Base");
		this.cannonTexture = QuickLoad("MG");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.damage = damage;
		this.range = range;
		this.firingspeed = 3;
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.targeted = false;
		// this.target = acquireTarget();
		// this.angle = calculateAngle();
	}

	private Enemy acquireTarget() {
		Enemy closestEnemy = null;
		float closeDistance = 10000;
		for (Enemy e : enemies) {
			if (isRange(e) && findDistance(e) < closeDistance) {
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

	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}

	private void shoot() {
		timeSinceLastShot = 0;
		/*projectiles.add(new ProjectileIceGun(QuickLoad("Bullet_MG"), target, x + TILE_SIZE / 2 -TILE_SIZE / 4,
				y + TILE_SIZE / 2 -TILE_SIZE / 4, 32, 32, 900, 10));*/
	}
	
	public void updateEnemiesList(CopyOnWriteArrayList<Enemy> newList) {
		enemies = newList;
	}
	

	public void update() {
		if (!targeted) {
			target = acquireTarget();
		}
		
		if (target == null || target.isAlive() == false) {
			targeted = false;
		}
		
		timeSinceLastShot += Delta();
		if (timeSinceLastShot > firingspeed) {
			shoot();
		}
		for (Projectile p : projectiles)
			p.update();

		angle = calculateAngle();
		draw();
	}

	public void draw() {
		DrawQuadTex(baseTexture, x, y, width + 10, height + 10);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}
}

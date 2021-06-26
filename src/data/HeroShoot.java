package data;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public abstract class HeroShoot implements Entity {
	private float x, y, timeSinceLastShot, firingspeed, angle;
	private int width, height, damage, range, cost;
	public Enemy target;
	public Texture[] textures;
	private CopyOnWriteArrayList<Enemy> enemies;
	private boolean targeted;
	public ArrayList<Projectile> projectiles;
	public HeroShootType type;
	public String nameString;
	public Texture showTexture;

	public HeroShoot(HeroShootType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		this.type = type;
		this.textures = type.textures;
		this.damage = type.damage;
		this.range = type.range;
		this.cost = type.cost;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.enemies = enemies;
		this.targeted = false;
		this.timeSinceLastShot = 0f;
		this.projectiles = new ArrayList<Projectile>();
		this.firingspeed = type.firingspeed;
		this.angle = 0f;
		this.nameString = null;
		this.showTexture = null;
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

	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}

	public abstract void shoot(Enemy target);

	public void updateEnemiesList(CopyOnWriteArrayList<Enemy> newList) {
		enemies = newList;
	}

	public void draw() {
		DrawQuadTex(textures[0], x, y, width, height);
		if (textures.length > 1)
			for (int i = 0; i < textures.length; i++) {
				DrawQuadTexRot(textures[i], x, y, width, height, angle);
			}
	}

	public void update() {
		if (!targeted || target.getHiddenHealth() < 0) {
			target = acquireTarget();
		} else {
			angle = calculateAngle();
			if (timeSinceLastShot > firingspeed) {
				shoot(target);
				timeSinceLastShot = 0;
			}
		}

		if (target == null || target.isAlive() == false) {
			targeted = false;
		}
		timeSinceLastShot += Delta();
		for (Projectile p : projectiles)
			p.update();
		draw();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getDamage() {
		return damage;
	}
	public int getRange() {
		return range;
	}
	public float getFiringSpeed() {
		return firingspeed;
	}

	public Enemy getTarget() {
		return target;
	}

	public int getCost()
	{
		return cost;
	}
	public void upDamage(int a) {
		this.damage +=a;
	}
	public void changePro(ProjectileType a) {
		this.type.projectileType = a;
	}
	public String getName() {
		return nameString;
	}
	public Texture getShowTexture() {
		return showTexture;
	}
	public void setTexture(Texture[] a) {
		this.textures = a;
	}
	
}

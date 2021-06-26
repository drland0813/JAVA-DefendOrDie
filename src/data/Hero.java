package data;

import static helpers.Artist.*;


import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Delayed;
import helpers.Music;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;

public abstract class Hero implements Entity {
	private int width, height, range;
	private float speed, x, y, health, startHealth, hiddenHealth;
	public Texture texture, healthBackgroud, healthForeground, healthBorder;
	private Tile startTile;
	public boolean first, alive;
	public boolean targeted;
	private TileGrid grid;
	private CopyOnWriteArrayList<Enemy> enemies;
	public Enemy target;
	public ArrayList<Enemy> targetlist;
	public Animation animation;
	public SpriteSheet sheet;
	public Music sound,sound2;
	public String nameString;
	public Texture showTexture;
	
	public Hero(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		this.texture = null;
		this.healthBackgroud = QuickLoad("HealthBG");
		this.healthForeground = QuickLoad("HealthFG");
		this.healthBorder = QuickLoad("HealthB");
		this.startTile = grid.getTile(tileX, tileY);
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = 80;
		this.height = 80;
		this.range = 300;
		this.speed = 50;
		this.health = 50;
		this.enemies = enemies;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.alive = true;
		this.first = true;
		this.targeted = false;
		this.targetlist = new ArrayList<Enemy>();
		this.sound = null;
		this.sound2 = null;
		this.nameString = null;
		this.showTexture = null;
		
	}

	
	public void draw() {
		if (alive) {
			float healthPercentage = health / startHealth;
			DrawQuadTex(texture, x, y, width, height);
			DrawQuadTex(healthBackgroud, x, y - 16, this.width, 8);
			DrawQuadTex(healthForeground, x, y - 16, this.width * healthPercentage, 8);
			DrawQuadTex(healthBorder, x, y - 16, this.width, 8);
		}
		
	}

	public abstract void skill(Enemy target);
	
	public abstract void Animation(int x, int y);

	public Enemy acquireTarget() {
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

	public void resetTargeted() {
		targeted = false;
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
	public abstract void update();
	
	public void update3() {
		if (alive) {
			if (!targeted || target.getHiddenHealth() < 0) {
				target = acquireTarget();
			}
			else {
				skill(target);
				Animation((int)target.getX(), (int)target.getY());
			}
			if(target==null || target.isAlive() == false) {
				targeted = false;
			}
		}
		
	}
	public void update2() {
		if (alive) {
			if (!targeted || target.getHiddenHealth() < 0) {
				target = acquireTarget();
				targetlist.add(target);
				resetTargeted();
				target = acquireTarget();
				targetlist.add(target);
				resetTargeted();
				target = acquireTarget();
				targetlist.add(target);
			}
			else {
				for(Enemy target : targetlist) {
					skill(target);
				}
				
			}
			if(target==null || target.isAlive() == false) {
				if (target == null) {
					alive = false;
				}
				targeted = false;
			}
		}
		
	}
	
	public abstract void Sound();

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}
	public void setRange(int r) {
		this.range = r;
	}


	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;	
	}

	public TileGrid getTileGrid() {
		return grid;
	}

	public boolean isAlive() {
		return alive;
	}

	public void reduceHiddenHealth(float amount) {
		hiddenHealth -= amount;
	}

	public float getHiddenHealth() {
		return hiddenHealth;
	}

	public void setTexture(String name) {
		this.texture = QuickLoad(name);
	}
	public String getName() {
		return nameString;
	}
	public Texture getShowTexture() {
		return showTexture;
	}
}

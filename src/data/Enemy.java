package data;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import javax.naming.directory.DirContext;

public class Enemy implements Entity {
	private int width, height, currentCheckpoint;
	private float speed, x, y, health, startHealth, hiddenHealth;
	private Texture texture, healthBackgroud, healthForeground, healthBorder;
	private item aItem;
	private Tile startTile;
	private boolean first, alive;
	public static boolean oneway;
	private TileGrid grid;
	private ArrayList<Checkpoint> checkpoints;
	private int[] direction;

	public Enemy(int tileX, int tileY, TileGrid grid) {
		this.texture = QuickLoad("Enemy2");
		this.healthBackgroud = QuickLoad("HealthBG");
		this.healthForeground = QuickLoad("HealthFG");
		this.healthBorder = QuickLoad("HealthB");
		this.startTile = grid.getTile(tileX, tileY);
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = TILE_SIZE;
		this.height = TILE_SIZE;
		this.speed = 50;
		this.health = 50;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.alive = true;
		this.first = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.direction = new int[2];
		this.direction[0] = 0;
		this.direction[1] = 0;
		this.direction = findNextD(startTile);
		this.currentCheckpoint = 0;
		this.oneway = false;
		populateCheckpointList();
	}
	

	public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
		this.texture = texture;
		this.healthBackgroud = QuickLoad("HealthBG");
		this.healthForeground = QuickLoad("HealthFG");
		this.healthBorder = QuickLoad("HealthB");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.startHealth = health;
		this.hiddenHealth = health;
		this.grid = grid;
		this.alive = true;
		this.first = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.direction = new int[2];
		this.direction[0] = 0;
		this.direction[1] = 0;
		this.direction = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}

	public void update() {
		// Kiem tra xem co phai lan dau update hay khong
		if (first) {
			first = false;
		} else {
			if (checkpointReached()) {
				if (currentCheckpoint + 1 == checkpoints.size()) {
					endofMazeReached();

				} else {
					currentCheckpoint++;
				}

			} else {
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
			}
		}
	}

	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		if (x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}

	public void populateCheckpointList() {
		// add o ma enemy co the di duoc vao list
		checkpoints.add(findNextC(startTile, direction = findNextD(startTile)));
		int counter = 0;
		boolean cont = true;
		while (cont) {
			// tim kiem chi so x va y de co the di den duoc do
			// counter 0 la o dau tien trong list
			int[] currentD = findNextD(checkpoints.get(counter).getTile());
			// neu = 0 thi khong co o nao co the di duoc
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				// nguoc lai tuc la co huong di duoc
				// add o tiep theo co the di duoc cua o dau tien vao list
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(),
						direction = findNextD(checkpoints.get(counter).getTile())));
			}
			// bo dem list tang them 1
			counter++;
		}
	}

	private Checkpoint findNextC(Tile s, int[] dir) {
		Tile next = null;
		Checkpoint c = null;
		boolean found = false;

		int counter = 1;
		while (!found) {
			if (s.getXPlace() + dir[0] * counter == grid.getTilesWide()
					|| s.getYPlace() + dir[1] * counter == grid.getTilesHigh() || s.getType() != grid
							.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter).getType()) {
				found = true;
				counter -= 1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
			}
			counter++;
		}
		c = new Checkpoint(next, dir[0], dir[1]);
		return c;
	}

	private int[] findNextD(Tile s) {

		if (oneway == false) {
			int[] dir = new int[2];
			Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1);
			Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
			Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
			Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());
			if (s.getType() == u.getType() && direction[1] != 1) {
				dir[0] = 0;
				dir[1] = -1;
			} else if (s.getType() == r.getType() && direction[0] != -1) {
				dir[0] = 1;
				dir[1] = 0;
			} else if (s.getType() == d.getType() && direction[1] != -1) {
				dir[0] = 0;
				dir[1] = 1;
			} else if (s.getType() == l.getType() && direction[0] != 1) {
				dir[0] = -1;
				dir[1] = 0;
			} else {
				dir[0] = 2;
				dir[1] = 2;
				// System.out.print("NO DIRECTION FOUND");
			}
			return dir;
		}
		else {
			int[] dir = new int[2];
//			Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1);
//			Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
//			Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
			Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());
//			if (s.getType() == u.getType() && direction[1] != 1) {
//				dir[0] = 0;
//				dir[1] = -1;
//			} else
//
//			if (s.getType() == r.getType() && direction[0] != -1) {
//				dir[0] = 1;
//				dir[1] = 0;
//			} else if (s.getType() == d.getType() && direction[1] != -1) {
//				dir[0] = 0;
//				dir[1] = 1;
//			} else
			if (s.getType() == l.getType() && direction[0] != 1) {
				dir[0] = -1;
				dir[1] = 0;
			} else {
				dir[0] = 2;
				dir[1] = 2;
				// System.out.print("NO DIRECTION FOUND");
			}
			return dir;
		}
		

		
	}

	/*
	 * public boolean pathCountinues() { boolean answer = true; Tile myTile =
	 * grid.GetTile((int)(x / 64), (int)(y / 64)); Tile nextTile =
	 * grid.GetTile((int)(x / 64) + 1, (int)(y / 64));
	 * 
	 * if (myTile.getType()!=nextTile.getType()) answer = false; return answer; }
	 */
	public void damage(int amount) {
		health -= amount;
		if (health <= 0) {
			// System.out.println("chet");
			die();
			// afterDie();
			Player.modifyCash(15);
		}

	}

	public void die() {
		alive = false;
	}

	public void afterDie() {
		if (alive == false) {
			float returnXTile = Enemy.this.getX() / TILE_SIZE;
			float returnYTile = Enemy.this.getY() / TILE_SIZE;
			System.out.println("Vi tri chet: " + " X: " + returnXTile + "  Y: " + returnYTile);
			// aItem = new item((int) returnXTile, (int) returnYTile, grid) {
			// };
			aItem.draw();

			// DrawQuadTex(QuickLoad("item1"), returnXTile, returnYTile, 30, 30);
		}

	}

	public int XDie(Enemy e) {
		int returnX = (int) e.getX() / TILE_SIZE;
		return returnX;
	}

	public int YDie(Enemy e) {
		int returnY = (int) e.getY() / TILE_SIZE;
		return returnY;
	}

	public void draw() {
		float healthPercentage = this.health / this.startHealth;
		DrawQuadTex(texture, x, y, width, height);
		DrawQuadTex(healthBackgroud, x, y - 16, width, 8);
		DrawQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		DrawQuadTex(healthBorder, x, y - 16, width, 8);

	}

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

	public void reHealth(float amount) {
		health += amount;
	}

	public float getHiddenHealth() {
		return hiddenHealth;
	}

	public void setTexture(String name) {
		this.texture = QuickLoad(name);
	}

	private void endofMazeReached() {
		Player.modifyLives(-1);
		die();
		if (Player.Lives == -1) {
			Pause();
			Player.setPlay(false);
		}

	}
	public void setOneWay(boolean a) {
			this.oneway = a;
	}

}

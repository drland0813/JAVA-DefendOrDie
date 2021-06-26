package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Tile {

	private float x, y;
	private int width, height;
	private Texture texture;
	private TileType type;
	private boolean occupied, skilled;
	private HeroShoot hold;
	private Hero holdHero;

	public Tile(float x, float y, int width, int height, TileType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = QuickLoad(type.textureName);
		this.skilled = false;
		this.hold = null;
		if (type.buildable) {
			occupied = false;
		} else {
			occupied = true;
		}
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

	public int getXPlace() {
		return (int) x / TILE_SIZE;
	}

	public int getYPlace() {
		return (int) y / TILE_SIZE;
	}

	public void setY(float y) {
		this.y = y;
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public boolean getOccupied() {
		return occupied;
	}
	
	public boolean getSkilled() {
		return skilled;
	}
	
	public void setSkilled(boolean skilled) {
		this.skilled = skilled;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public void setElement(HeroShoot a) {
		this.hold = a;
	}
	public void setElementHero(Hero a) {
		this.holdHero = a;
	}
	public HeroShoot getElement() {
		return hold;
	}
	public Hero getElementHero() {
		return holdHero;
	}

	public void draw() {
		DrawQuadTex(texture, x, y, width, height);
	}
}

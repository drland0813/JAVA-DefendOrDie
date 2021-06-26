package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import javax.naming.directory.DirContext;
public class item implements Entity{
	private int width, height, time;
	private int x, y;
	private Texture texture;
	private Tile startTile;
	private TileGrid grid;
	private boolean alive;
	
	public item(int tileX, int tileY, TileGrid grid) {
		this.texture = QuickLoad("item1");
		this.grid = grid;
		this.startTile = grid.getTile(tileX, tileY);
		this.x =(int) startTile.getX();
		this.y = (int) startTile.getY();
		this.width = TILE_SIZE ;
		this.height = TILE_SIZE;
		this.time = 10;
		this.alive = true;
	}
	
	public item(Tile startTile) {
		this.texture = QuickLoad("item1");
		this.x =(int) startTile.getX();
		this.y = (int) startTile.getY();
		this.width = TILE_SIZE ;
		this.height = TILE_SIZE;
		this.time = 10;
		this.alive = true;
	}
	
	public item(Texture texture,int tileX, int tileY, TileGrid grid, int width, int height) {
		this.texture = QuickLoad("item1");
		this.startTile = grid.getTile(tileX, tileY);
		//this.x = startTile.getX();
		//this.y = startTile.getY();
		this.width = TILE_SIZE ;
		this.height = TILE_SIZE;
		//this.grid = grid;
		this.width = width;
		this.height = height;
	}

	public void draw() {
		if (alive) {
				DrawQuadTex(texture, x, y, width, height);
		}
			
	}
	
	public void drawWithXY(int x, int y) {
		DrawQuadTex(texture, x, y, width, height);
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
		this.x = (int)x;
	}

	public void setY(float y) {
		this.y = (int)y;
	}

	public void setWidth(int width) {
		this.width = width;
		
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void update() {
		
	}
	
	
}

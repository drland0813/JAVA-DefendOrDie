package UI;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.QuickLoad;

public class Button {
	private String name;
	private Texture texture;
	private int x, y ,width, height;
	private boolean show;
	private int cost;
	
	public Button(String name, Texture texture, int x, int y, int width, int height)
	{
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.show = true;
	}
	
	public Button(String name, Texture texture, int x, int y, int width, int height, int cost)
	{
		this.cost = cost;
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.show = true;
	}
	
	public Button(String name, Texture texture,  int x, int y)
	{
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = texture.getImageWidth();
		this.height = texture.getImageHeight();
		this.show = true;
	}
	public Button(String name, Texture texture,  int x, int y, int cost)
	{

		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = texture.getImageWidth();
		this.height = texture.getImageHeight();
		this.show = true;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public int getCost() {
		return cost;
	}

	public int getX() {
		return x;
	}
	
	public boolean getShow() {
		return show;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
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

	public String getName() {
		return name;
	}
	
	public void setState(boolean a) {
		show = a;
	}

	public void setName(String name) {
		this.name = name;
	}
}

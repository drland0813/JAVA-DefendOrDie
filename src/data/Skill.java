package data;

import org.newdawn.slick.opengl.Texture;

public abstract class Skill implements Entity {
	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage,width, height;
	private Enemy target;
	private boolean alive;
}

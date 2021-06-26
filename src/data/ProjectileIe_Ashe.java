package data;

import org.newdawn.slick.opengl.Texture;

import helpers.Music;

public class ProjectileIe_Ashe extends Projectile {
	private Music sound = new Music("/Music/Tower/icegun.mp3");
	public ProjectileIe_Ashe(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	@Override
	public void damage() {
		Sound();
		super.getTarget().setSpeed(4f);
		super.setAlive(false);
		super.damage();
	}
	@Override
	public void Sound() {
		sound.Play(-20.0f);
	}
}

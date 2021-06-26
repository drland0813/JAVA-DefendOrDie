package data;

import static helpers.Artist.QuickLoadHero;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import static helpers.Artist.QuickLoadHero;


import helpers.Music;

public class Hero_Yassuo extends Hero{

	public Hero_Yassuo(int tileX, int tileY, TileGrid grid, CopyOnWriteArrayList<Enemy> enemies) {
		super(tileX, tileY, grid, enemies);
		this.setTexture(QuickLoadHero("ys"));
		try {
			sheet = new SpriteSheet("res/SS_En.png", 114, 64);
			animation = new Animation(sheet, 100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.sound = new Music("/Music/yassuo.mp3");
		this.sound2 = new Music("/Music/yassuoR.mp3");
		this.showTexture=QuickLoadHero("ys");
		this.nameString = "Yassuo";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Enemy target) {
		//Animation((int)target.getX(), (int)target.getY());	
		float a = target.getHealth()/3;
		//sound2.Play(0);
		target.setSpeed(0);
		target.damage(40);
		target.reduceHiddenHealth(40);
	}

	@Override
	public void Sound() {
		sound.Play(-20.0f);
	}

	@Override
	public void Animation(int x, int y) {
		//sound2.Play(-20.0f);
		//animation.draw(x, y);
	}

	@Override
	public void update() {
		if (alive) {
			if (!targeted || target.getHiddenHealth() < 0) {
				sound2.Play(-30);
				target = acquireTarget();
			}
			else {
				skill(target);
				
			}
			if(target==null || target.isAlive() == false) {
				targeted = false;
			}
		}
		
	}

}

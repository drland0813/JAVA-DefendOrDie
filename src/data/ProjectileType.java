package data;
import static helpers.Artist.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.tests.TestUtils;

import static helpers.Artist.*;

public enum ProjectileType {
	
	CannonBullet(QuickLoad("BulletCan"), 20, 300),
	Pro_Lucian(QuickLoadHero("pro_lucian"), 100, 2000),
	Pro_Ashe(QuickLoadHero("pro_ashe"), 0, 300),
	Pro_TowerRed(QuickLoadTower("pro_towerred"), 0, 300),
	
	
	Pro_Tower2(QuickLoadTower("pro_tower2"), 200, 300),
	Pro_Tower3(QuickLoadTower("pro_tower3"), 500, 300),
	Pro_Tower4(QuickLoadTower("pro_tower4"), 2500, 300),
	Pro_Tower5(QuickLoadTower("pro_tower5"), 1000, 300),
	Pro_Tower6(QuickLoadTower("pro_tower6"), 200, 300),
	Pro_Tower7(QuickLoadTower("pro_tower7"), 50, 300),
	Pro_Tower8(QuickLoadTower("pro_tower8"), 300, 300),
	Pro_Tower9(QuickLoadTower("pro_tower9"), 0, 300),
	
	
	Test(QuickLoad("PTest"), 0, 1000);
	//Test2(QuickLoad("PTest"), 0, 1000);
	
	
	
	Texture texture;
	int damage;
	float speed;
	
	ProjectileType(Texture texture, int damage, float speed)
	{
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
	public void setDmg(int  a) {
		this.damage +=a;
	}
	public int getDmg() {
		return damage ;
	}
}

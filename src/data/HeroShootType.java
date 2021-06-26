package data;
import static helpers.Artist.*;
import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;

public enum HeroShootType {
	
	Cannon(new Texture[] {QuickLoad("Base"), QuickLoad("Cannon")}, ProjectileType.CannonBullet,10, 1000, 3, 30),
	
	
	Lucian(new Texture[] {QuickLoad("Base"),QuickLoad("MG")}, ProjectileType.Pro_Lucian,0, 1000, 0.5f, 50),
	Ashe(new Texture[] {QuickLoad("Base"),QuickLoad("ashe")}, ProjectileType.Pro_Ashe,0, 1000, 3, 50),
	Vayne(new Texture[] {QuickLoadHero("vayne")}, ProjectileType.Pro_Lucian,0, 1000, 3, 50),
	Jinx(new Texture[] {QuickLoadHero("jinx")}, ProjectileType.Pro_Lucian,0, 1000, 3, 50),
	
	TowerRed(new Texture[] {QuickLoad("Base"),QuickLoadTower("towerred")}, ProjectileType.Pro_Lucian,0, 1000, 3, 50),
	Tower2(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower2")}, ProjectileType.Pro_Tower2, 200, 500, 1, 50),
	Tower3(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower3")}, ProjectileType.Pro_Tower3, 500, 1000, 1, 50),
	Tower4(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower4")}, ProjectileType.Pro_Tower4, 2500, 700, 1, 50),
	Tower5(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower5")}, ProjectileType.Pro_Tower5, 1000, 200, 1, 50),
	Tower6(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower6")}, ProjectileType.Pro_Tower6, 200, 300, 1, 50),
	Tower7(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower7")}, ProjectileType.Pro_Tower7, 50, 500, 0.1f, 50),
	Tower8(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower8")}, ProjectileType.Pro_Tower8, 300, 700, 0.5f, 50),
	Tower9(new Texture[] {QuickLoad("Base"),QuickLoadTower("tower9")}, ProjectileType.Pro_Tower9, 0, 1000, 3, 50),
	
	TowerTest(new Texture[] {QuickLoad("Base1"),QuickLoad("TowerTest")}, ProjectileType.Test,0, 1000, 3, 50);
	
	
	Texture[] textures;
	ProjectileType projectileType;
	int damage, range ,cost;
	float firingspeed;
	
	HeroShootType (Texture[] textures, ProjectileType projectileType,int damage, int range, float firingspeed, int cost)
	{
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = projectileType.damage;
		this.range = range;
		this.firingspeed = firingspeed;
		this.cost = cost;
		
	}
}

package data;

import org.lwjgl.input.Keyboard;
import static helpers.Clock.Pause;
import org.lwjgl.input.Mouse;

import helpers.Clock;
import helpers.Music;

import static helpers.Artist.*;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<HeroShoot> heroShoots;
	private ArrayList<Hero> herolist;
	private ArrayList<HeroMove> heroMoveList;
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower, holdingHero, holdingHeroMove;
	public static boolean clickedE;
	public HeroShoot tempHeroShoot;
	public static HeroShoot clickHeroShoot;
	public static Hero clickHero;
	public Hero herotemp;
	public HeroShoot checkHeroshoot;
	public Hero checkHero;
	private HeroMove tempHeroMove;
	private Music soundBuy = new Music("/Music/buy.mp3");
	public static int Cash, Lives, Score, EnemiesPerWave,dame,cost, range;
	public static double attackspeed;
	public String name;
	public static boolean canplay;
	public ProjectileType a;

	
	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.TTopL;
		this.types[1] = TileType.Way;
		this.types[2] = TileType.TopR;
		this.waveManager = waveManager;
		this.heroShoots = new ArrayList<HeroShoot>();
		this.herolist = new ArrayList<Hero>();
		this.heroMoveList = new ArrayList<HeroMove>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.holdingHero  =false;
		this.holdingHeroMove  =false;
		this.tempHeroShoot = null;
		this.herotemp = null;
		this.canplay = true;
		this.dame = 0;
		this.name = null;
		this.clickedE = false;
		this.clickHeroShoot = null;
		this.cost = 0;
		this.range = 0;
		this.attackspeed = 0.0;
		Cash = 0;
		Lives = 0;
		Score = 0;
		
	}

	public void setup() {
		Cash = 500;
		Lives = 15;
	}
	public void setup(int a) {
		Cash = 500;
		Lives = a;
	}

	public static boolean modifyCash(int amount) {
		if (Cash + amount >= 0) {
			Cash += amount;
			return true;
		}
		return false;
	}
	
	public static void modifyScore(int amount) {
		if (Lives > 0) {
			Score += amount;
		}
		
	}
	
	public static void modifyEnemiesPerWave(int amount) {
		EnemiesPerWave = amount;
	}
	public static void setPlay(boolean a) {
		canplay = a;
	}

	public static void modifyLives(int amount) {
		Lives += amount;
	}

	public void update() {
		if (canplay) {

		if (holdingTower) {
			tempHeroShoot.setX(getMouseTile().getX());
			tempHeroShoot.setY(getMouseTile().getY());
			tempHeroShoot.draw();
		}
		if (holdingHero) {
			herotemp.setX(getMouseTile().getX());
			herotemp.setY(getMouseTile().getY());
			herotemp.Sound();
			herotemp.draw();
		}
		if (holdingHeroMove) {
			tempHeroMove.setX(getMouseTile().getX());
			tempHeroMove.setY(getMouseTile().getY());
			herotemp.Sound();
			tempHeroMove.draw();
		}

		for (HeroShoot t : heroShoots) {
			t.update();
			t.draw();
			t.updateEnemiesList(waveManager.getCurrentWave().getEnemyList());
		}
		for(Hero tHero : herolist) {
			tHero.draw();
			//tHero.Sound();
			tHero.updateEnemiesList(waveManager.getCurrentWave().getEnemyList());
			tHero.update();
			//tHero.update2();
		}
		for (HeroMove t : heroMoveList) {
			t.update();
			t.draw();
		}
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeHeroShoot();
			check();
		}
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeHero();
			checkHero();
			

		}
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeHeroMove();
		}
		if (Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			holdingHero = false;
			holdingTower = false;
		}
		leftMouseButtonDown = Mouse.isButtonDown(0);

		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(-0.2f);
			}
		}
		}
	}

	private void placeHeroShoot() {
		Tile currenTile = getMouseTile();
		if (holdingTower)
			if (!currenTile.getOccupied() && modifyCash(-tempHeroShoot.getCost())) {
				heroShoots.add(tempHeroShoot);
				currenTile.setOccupied(true);
				currenTile.setElement(tempHeroShoot);
				holdingTower = false;
				tempHeroShoot = null;
			}

	}
	
	private void placeHero() {
		Tile currenTile = getMouseTile();
		if (holdingHero)
			if (currenTile.getOccupied()) {
				herolist.add(herotemp);
				currenTile.setOccupied(true);
				currenTile.setElementHero(herotemp);
				holdingHero = false;	
				herotemp = null;
			}

	}
	private void placeHeroMove() {
		Tile currenTile = getMouseTile();
		if (holdingHeroMove)
			if (currenTile.getOccupied()) {
				heroMoveList.add(tempHeroMove);
				currenTile.setOccupied(false);
				holdingHeroMove = false;
				tempHeroMove = null;
			}
	}
	
	public void check() {
		Tile checkTile = getMouseTile();
		
		if (Mouse.isButtonDown(0)) {
			if (checkTile.getOccupied()) {
				 checkHeroshoot =checkTile.getElement();
				if (checkHeroshoot==null) {
					setClickE(false);
				}
				else {
					setClickE(true);

					clickHeroShoot = checkHeroshoot;
					cost = checkHeroshoot.getCost();
					range = checkHeroshoot.getRange();
					dame = checkHeroshoot.getDamage();
					attackspeed = checkHeroshoot.getFiringSpeed();
				}
				//attackspeed = checkHero.getSpeed();
			}
			else {
			}
		}
	}
	public void checkHero() {
		Tile checkTile = getMouseTile();
		
		if (Mouse.isButtonDown(0)) {
			if (checkTile.getOccupied()) {
				 checkHero =checkTile.getElementHero();
				if (checkHero==null) {
					setClickE(false);
				}
				else {
					setClickE(true);

					clickHero = checkHero;
//					cost = checkHero.getCost();
//					range = checkHero.getRange();
//					dame = checkHero.getDamage();
//					attackspeed = checkHero.getFiringSpeed();
				}
				
				//attackspeed = checkHero.getSpeed();
			}
			else {
			}
		}
	}
	
	
	public void pickHeroShoot(HeroShoot t) {
		soundBuy.Play(-0.0f);
		tempHeroShoot = t;
		holdingTower = true;
	}
	
	public void pickHero(Hero t) {
		herotemp = t;
		holdingHero = true;
	}
	public void pickHeroMove(HeroMove t) {
		tempHeroMove = t;
		holdingHeroMove = true;
	}

	private Tile getMouseTile() {
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}

	public static void CheckLives() {
		if (Lives == -1) {
			Pause();
		}
	}
	public static boolean checkClickE() {
		return clickedE;
	}
	public static void setClickE(boolean a) {
		clickedE = a;
	}
	public static HeroShoot getHeroShootClicked() {
		return clickHeroShoot;
	}
	public void setHeroShootClicked(HeroShoot a) {
		 clickHeroShoot = a;
	}
}

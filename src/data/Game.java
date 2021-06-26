package data;

import static helpers.Clock.*;

import java.awt.Color;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.print.attribute.standard.JobKOctets;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;

import UI.*;
import UI.UI.Menu;
import helpers.Clock;
import helpers.Music;
import helpers.StateManager;

import static helpers.Artist.*;
import helpers.StateManager;
import helpers.StateManager.GameState;

public class Game {
	private Music soundRoll = new Music("/Music/roll.mp3");
	private boolean leftMouseButtonDown;
	private PausePlayMenu pausePlayMenu;
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private UI gameUI, gameUI2, gameUi3, gameUi4, gameUi5, gameUi6, gameUi7, gameUi8, pauseUi;
	private Menu noti, menuRoll, menuRoll2, roll, act, cash, update1, update2, update3, pauseMenu;
	private Texture menuBackground, menubackgound2;
	private Enemy[] enemyTypes;
	private HeroMove heroMove;
	private Hero[] herolist;
	private Button[] listButtons;
	private Random random;
	private int ran, key;
	private ProjectileType aProjectileType;

	public Game(int[][] map) {
		// khởi tạo map
		grid = new TileGrid(map);
		enemyTypes = new Enemy[21];
		herolist = new Hero[2];
		listButtons = new Button[9];
		listButtons[0] = new Button("MachineGun", QuickLoad("MG"), 0, 0, 100, 100);
		listButtons[1] = new Button("IceGun", QuickLoad("IceGunBT"), 0, 0, 100, 100);
		listButtons[2] = new Button("Tower2", QuickLoadTower("tower2"), 0, 0, 100, 100);
		listButtons[3] = new Button("Tower3",  QuickLoadTower("tower3"), 0, 0, 100, 100);
		listButtons[4] = new Button("Tower4",  QuickLoadTower("tower4"), 0, 0, 100, 100);
		listButtons[5] = new Button("Tower5",  QuickLoadTower("tower5"), 0, 0, 100, 100);
		listButtons[6] = new Button("Vayne", QuickLoadHero("vayne"), 0, 0, 100, 100);
		listButtons[7] = new Button("Syndra", QuickLoadHero("syndra"), 0, 0, 100, 100);
		listButtons[8] = new Button("Leesin", QuickLoadHero("leesin"), 0, 0, 100, 100);
		setStartEnemyList(29, 1);
		waveManager = new WaveManager(enemyTypes, 10, 1);
		player = new Player(grid, waveManager);
		player.setup();

		this.menubackgound2 = QuickLoad("backgroundmenu");
		this.menuBackground = QuickLoad("menubg");
		setupUI();
	}

	public Game(int[][] map, int a) {
		// khởi tạo map
		this.key = a;
		grid = new TileGrid(map);
		enemyTypes = new Enemy[21];
		listButtons = new Button[11];
		leftMouseButtonDown = false;

		listButtons[0] = new Button("MachineGun", QuickLoad("MG"), 0, 0, 100, 100, 50);
		listButtons[1] = new Button("IceGun", QuickLoad("ashe"), 0, 0, 100, 100, 50);
		listButtons[2] = new Button("TowerRed", QuickLoadTower("towerred"), 0, 0, 100, 100);
		listButtons[3] = new Button("Tower2", QuickLoadTower("tower2"), 0, 0, 100, 100);
		listButtons[4] = new Button("Tower3", QuickLoadTower("tower3"), 0, 0, 100, 100);
		listButtons[5] = new Button("Tower4", QuickLoadTower("tower4"), 0, 0, 100, 100);
		listButtons[6] = new Button("Tower5", QuickLoadTower("tower5"), 0, 0, 100, 100);
		listButtons[7] = new Button("Tower6", QuickLoadTower("tower6"), 0, 0, 100, 100);
		listButtons[8] = new Button("Tower7", QuickLoadTower("tower7"), 0, 0, 100, 100);
		listButtons[9] = new Button("Tower8", QuickLoadTower("tower8"), 0, 0, 100, 100);
		listButtons[10] = new Button("Tower9", QuickLoadTower("tower9"), 0, 0, 100, 100);
		
		switch (key) {
		case 1:
			setStartEnemyList(25, 8, 4, 4);
			break;
		case 2:
			setStartEnemyList(4 ,6 , 25, 6);
			break;
		case 3:
			setStartEnemyList(6, 2);
			break;
		case 4:
			setStartEnemyList(24,9,5,4);
			break;
		case 5:
			setStartEnemyList(3,6,24,6);
			break;
		case 6:
			setStartEnemyList(4,1,25,1);
			break;
		case 7:
			setStartEnemyList(4,11,25,11,4,1,25,1);
			break;
		default:
			
			break;
		}
		waveManager = new WaveManager(enemyTypes, 25,1);

		player = new Player(grid, waveManager);
		player.setup();

		this.menubackgound2 = QuickLoad("menubg");
		setupUI();
	}

	public void setStartEnemyList(int x, int y) {
		enemyTypes[0] = new Enemy1(x, y, grid);
		enemyTypes[1] = new Enemy2(x, y, grid);
		enemyTypes[2] = new Enemy3(x, y, grid);

		enemyTypes[3] = new Enemy1(x, y, grid);
		enemyTypes[4] = new Enemy2(x, y, grid);
		enemyTypes[5] = new Enemy3(x, y, grid);

		enemyTypes[6] = new Enemy1(x, y, grid);
		enemyTypes[7] = new Enemy2(x, y, grid);
		enemyTypes[8] = new Enemy3(x, y, grid);

		enemyTypes[9] = new Enemy1(x, y, grid);
		enemyTypes[10] = new Enemy2(x, y, grid);
		enemyTypes[11] = new Enemy3(x, y, grid);

		enemyTypes[12] = new Enemy1(x, y, grid);
		enemyTypes[13] = new Enemy2(x, y, grid);
		enemyTypes[14] = new Enemy3(x, y, grid);

		enemyTypes[15] = new Enemy1(x, y, grid);
		enemyTypes[16] = new Enemy2(x, y, grid);
		enemyTypes[17] = new Enemy3(x, y, grid);

		enemyTypes[18] = new Enemy1(x, y, grid);
		enemyTypes[19] = new Enemy2(x, y, grid);
		enemyTypes[20] = new Enemy3(x, y, grid);
	}

	public void setStartEnemyList(int x, int y, int a, int b) {
		enemyTypes[0] = new Enemy1(x, y, grid);
		enemyTypes[1] = new Enemy2(x, y, grid);
		enemyTypes[2] = new Enemy3(x, y, grid);

		enemyTypes[3] = new Enemy1(x, y, grid);
		enemyTypes[4] = new Enemy2(x, y, grid);
		enemyTypes[5] = new Enemy3(x, y, grid);

		enemyTypes[6] = new Enemy1(a, b, grid);
		enemyTypes[7] = new Enemy2(a, b, grid);
		enemyTypes[8] = new Enemy3(x, y, grid);

		enemyTypes[9] = new Enemy1(x, y, grid);
		enemyTypes[10] = new Enemy2(x, y, grid);
		enemyTypes[11] = new Enemy3(x, y, grid);

		enemyTypes[12] = new Enemy1(a, b, grid);
		enemyTypes[13] = new Enemy2(a, b, grid);
		enemyTypes[14] = new Enemy3(a, b, grid);

		enemyTypes[15] = new Enemy1(x, y, grid);
		enemyTypes[16] = new Enemy2(x, y, grid);
		enemyTypes[17] = new Enemy3(x, y, grid);

		enemyTypes[18] = new Enemy1(a, b, grid);
		enemyTypes[19] = new Enemy2(a, b, grid);
		enemyTypes[20] = new Enemy3(a, b, grid);
	}
	public void setStartEnemyList(int x, int y, int a, int b,int c,int d, int i,int j) {
		enemyTypes[0] = new Enemy1(x, y, grid);
		enemyTypes[1] = new Enemy2(a, b, grid);
		enemyTypes[2] = new Enemy3(c, d, grid);

		enemyTypes[3] = new Enemy1(i, j, grid);
		enemyTypes[4] = new Enemy2(c, d, grid);
		enemyTypes[5] = new Enemy3(a, b, grid);

		enemyTypes[6] = new Enemy1(x, y, grid);
		enemyTypes[7] = new Enemy2(i, j, grid);
		enemyTypes[8] = new Enemy3(c, d, grid);

		enemyTypes[9] = new Enemy1(a, b, grid);
		enemyTypes[10] = new Enemy2(c, d, grid);
		enemyTypes[11] = new Enemy3(x, y, grid);

		enemyTypes[12] = new Enemy1(i, j, grid);
		enemyTypes[13] = new Enemy2(a, b, grid);
		enemyTypes[14] = new Enemy3(x, y, grid);

		enemyTypes[15] = new Enemy1(c, d, grid);
		enemyTypes[16] = new Enemy2(a, b, grid);
		enemyTypes[17] = new Enemy3(x, y, grid);

		enemyTypes[18] = new Enemy1(i, j, grid);
		enemyTypes[19] = new Enemy2(a, b, grid);
		enemyTypes[20] = new Enemy3(c, d, grid);
	}
	private int ran() {
		random = new Random();
		ran = random.nextInt(listButtons.length);
		return ran;
	}

	private void setupUI() {
		gameUI = new UI();
		gameUI2 = new UI();
		gameUi3 = new UI();
		gameUi4 = new UI();
		gameUi5 = new UI();
		gameUi6 = new UI();
		gameUi7 = new UI();
		gameUi8 = new UI();
		pauseUi = new UI();

		gameUI2.createMenu("MenuRoll", 75 - 15, 905, 1920, 100, 5, 0, 4);
		gameUI2.createMenu("MenuRoll2", (350 + 64 * 4) - 38 - 30, 905, 1920, 100, 5, 0, 4);

		gameUi3.createMenu("roll", 30, 893, 100, 100, 1, 0, 0);

		gameUi6.createMenu("update1", 1653, 840, 100, 100, 1, 0, 0);
		gameUi7.createMenu("update2", 1653, 914, 100, 100, 1, 0, 0);
		gameUi8.createMenu("update3", 1653, 985, 100, 100, 1, 0, 0);

		pauseUi.createMenu("pause", -20, 980, 100, 100, 1, 0, 0);

		menuRoll = gameUI2.getMenu("MenuRoll");
		menuRoll2 = gameUI2.getMenu("MenuRoll2");
		roll = gameUi3.getMenu("roll");
		update1 = gameUi6.getMenu("update1");
		update2 = gameUi7.getMenu("update2");
		update3 = gameUi8.getMenu("update3");
		pauseMenu = pauseUi.getMenu("pause");

		menuRoll.addButtons(listButtons[0]);
		menuRoll.addButtons(listButtons[1]);

		menuRoll2.addButtons(listButtons[4]);
		menuRoll2.addButtons(listButtons[3]);

		roll.addButtons(new Button("Roll", QuickLoad("roll"), 0, 0, 150, 200));

		update1.addButtons(new Button("Attack", QuickLoad("attack"), 0, 0, 90, 75));
		update2.addButtons(new Button("Speed", QuickLoad("speed"), 0, 0, 90, 75));
		update3.addButtons(new Button("Engineer", QuickLoad("eng"), 0, 0, 90, 75));
		pauseMenu.addButtons(new Button("Pause", QuickLoad("pause"), 0, 0, 100, 100));

	}

	private void Roll() {
		player.modifyCash(-10);
		listButtons[0] = new Button("MachineGun", QuickLoad("MG"), 0, 0, 100, 100, 50);
		listButtons[1] = new Button("IceGun", QuickLoad("ashe"), 0, 0, 100, 100, 50);
		listButtons[2] = new Button("TowerRed", QuickLoadTower("towerred"), 0, 0, 100, 100);
		listButtons[3] = new Button("Tower2", QuickLoadTower("tower2"), 0, 0, 100, 100);
		listButtons[4] = new Button("Tower3", QuickLoadTower("tower3"), 0, 0, 100, 100);
		listButtons[5] = new Button("Tower4", QuickLoadTower("tower4"), 0, 0, 100, 100);
		listButtons[6] = new Button("Tower5", QuickLoadTower("tower5"), 0, 0, 100, 100);
		listButtons[7] = new Button("Tower6", QuickLoadTower("tower6"), 0, 0, 100, 100);
		listButtons[8] = new Button("Tower7", QuickLoadTower("tower7"), 0, 0, 100, 100);
		listButtons[9] = new Button("Tower8", QuickLoadTower("tower8"), 0, 0, 100, 100);
		listButtons[10] = new Button("Tower9", QuickLoadTower("tower9"), 0, 0, 100, 100);
		
		int[] numbers = new int[4];
		Vector<Integer> v = new Vector<Integer>();
		int f = 0;
		menuRoll.resetButton();
		menuRoll2.resetButton();
		gameUI2.resetMenu();

		gameUI2.createMenu("MenuRoll", 75 - 15, 905, 1920, 100, 5, 0, 4);
		gameUI2.createMenu("MenuRoll2", (350 + 64 * 4) - 38 - 30, 905, 1920, 100, 5, 0, 4);

		menuRoll = gameUI2.getMenu("MenuRoll");
		menuRoll2 = gameUI2.getMenu("MenuRoll2");

		for (int i = 0; i < 100; i++) {
			f = ran();
			if (!v.contains(f)) {
				v.add(f);
			}
		}
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = v.get(i);
		}

		menuRoll.addButtons(listButtons[numbers[0]]);
		menuRoll.addButtons(listButtons[numbers[1]]);
		menuRoll2.addButtons(listButtons[numbers[2]]);
		menuRoll2.addButtons(listButtons[numbers[3]]);
		gameUI2.draw();
	}

	private void updateUI() {
		gameUI2.draw();
		gameUi3.draw();
		gameUi6.draw();
		gameUi7.draw();
		gameUi8.draw();
		pauseUi.draw();
		gameUI.drawString2(20, 550, "Score: " + player.Score, 30);
		gameUI.drawString2(20, 650, "Wave: " + waveManager.getWave(),30);
		gameUI.drawString2(20, 750, "Lives: " + player.Lives,30);
		if (player.checkHeroshoot == null) {
			DrawQuadTex(QuickLoad("nullbutton"), 1187, 863, 100, 100);
		} else {
			DrawQuadTex(player.checkHeroshoot.showTexture, 1187, 863, 100, 100);
			gameUI.drawString2(1400, 850, player.checkHeroshoot.getName(), 30);
		}
		DrawQuadTex(QuickLoad("cash"), 7, 831, 50, 50);
		gameUI.drawString2(64, 833, "Cash: " + player.Cash, 30);
		gameUI.drawString((1700 - 64 * 4) + 10, 1000 - 2, "Cost: " + player.cost);
		


		gameUI.drawString((1700 - 64 * 4) + 10, 915, "Damage: " + player.dame);
		gameUI.drawString((1700 - 64 * 4) + 10, 955, "Firing Speed: " + player.attackspeed);

		gameUI.drawString(0, 0, "FPS: " + StateManager.frameInLastSecond);
		if (player.canplay) {
			if (Mouse.next() && !leftMouseButtonDown) {
				boolean mouseClicked = Mouse.isButtonDown(0);
				if (mouseClicked) {
					if (menuRoll.checkButton("MachineGun")) {
						if (menuRoll.isButtonClicked("MachineGun")) {
							player.pickHeroShoot(new HeroShoot_Lucian(HeroShootType.Lucian, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("MachineGun");
						}
					}
					// menu1
					if (menuRoll.checkButton("MachineGun2")) {
						if (menuRoll.isButtonClicked("MachineGun2")) {
							player.pickHeroShoot(new HeroShoot_Lucian(HeroShootType.Lucian, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("MachineGun2");
						}
					}

					// menu1
					if (menuRoll.checkButton("IceGun")) {
						if (menuRoll.isButtonClicked("IceGun")) {
							player.pickHeroShoot(new HeroShoot_Ashe(HeroShootType.Ashe, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("IceGun");
						}

					}
					// menu1
					if (menuRoll.checkButton("Tower2")) {
						if (menuRoll.isButtonClicked("Tower2")) {
							player.pickHeroShoot(new Tower2(HeroShootType.Tower2, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower2");
						}
					}
					if (menuRoll.checkButton("Tower3")) {
						if (menuRoll.isButtonClicked("Tower3")) {
							player.pickHeroShoot(new Tower3(HeroShootType.Tower3, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower3");
						}
					}
					if (menuRoll.checkButton("Tower4")) {
						if (menuRoll.isButtonClicked("Tower4")) {
							player.pickHeroShoot(new Tower4(HeroShootType.Tower4, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower4");
						}
					}
					if (menuRoll.checkButton("Tower5")) {
						if (menuRoll.isButtonClicked("Tower5")) {
							player.pickHeroShoot(new Tower5(HeroShootType.Tower5, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower5");
						}
					}
					if (menuRoll.checkButton("Tower6")) {
						if (menuRoll.isButtonClicked("Tower6")) {
							player.pickHeroShoot(new Tower6(HeroShootType.Tower6, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower6");
						}
					}
					if (menuRoll.checkButton("Tower7")) {
						if (menuRoll.isButtonClicked("Tower7")) {
							player.pickHeroShoot(new Tower7(HeroShootType.Tower7, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower7");
						}
					}
					if (menuRoll.checkButton("Tower8")) {
						if (menuRoll.isButtonClicked("Tower8")) {
							player.pickHeroShoot(new Tower8(HeroShootType.Tower8, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower8");
						}
					}
					if (menuRoll.checkButton("Tower9")) {
						if (menuRoll.isButtonClicked("Tower9")) {
							player.pickHeroShoot(new Tower9(HeroShootType.Tower9, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("Tower9");
						}
					}
					if (menuRoll.checkButton("TowerRed")) {
						if (menuRoll.isButtonClicked("TowerRed")) {
							player.pickHeroShoot(new Tower_Red(HeroShootType.TowerRed, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll.hideButton("TowerRed");
						}
					}
					
					
					// menu2
					if (menuRoll2.checkButton("MachineGun")) {
						if (menuRoll2.isButtonClicked("MachineGun")) {
							player.pickHeroShoot(new HeroShoot_Lucian(HeroShootType.Lucian, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("MachineGun");
						}
					}

					// menu2
					if (menuRoll2.checkButton("MachineGun2")) {
						if (menuRoll2.isButtonClicked("MachineGun2")) {
							player.pickHeroShoot(new HeroShoot_Lucian(HeroShootType.Lucian, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("MachineGun2");
						}
					}
					// menu2
					if (menuRoll2.checkButton("IceGun")) {
						if (menuRoll2.isButtonClicked("IceGun")) {
							player.pickHeroShoot(new HeroShoot_Ashe(HeroShootType.Ashe, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("IceGun");
						}
					}

					// menu2
					if (menuRoll2.checkButton("Tower2")) {
						if (menuRoll2.isButtonClicked("Tower2")) {
							player.pickHeroShoot(new Tower2(HeroShootType.Tower2, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower2");
						}
					}
					if (menuRoll2.checkButton("Tower3")) {
						if (menuRoll2.isButtonClicked("Tower3")) {
							player.pickHeroShoot(new Tower3(HeroShootType.Tower3, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower3");
						}
					}
					if (menuRoll2.checkButton("Tower4")) {
						if (menuRoll2.isButtonClicked("Tower4")) {
							player.pickHeroShoot(new Tower4(HeroShootType.Tower4, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower4");
						}
					}
					if (menuRoll2.checkButton("Tower5")) {
						if (menuRoll2.isButtonClicked("Tower5")) {
							player.pickHeroShoot(new Tower5(HeroShootType.Tower5, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower5");
						}
					}
					if (menuRoll2.checkButton("Tower6")) {
						if (menuRoll2.isButtonClicked("Tower6")) {
							player.pickHeroShoot(new Tower6(HeroShootType.Tower6, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower6");
						}
					}
					if (menuRoll2.checkButton("Tower7")) {
						if (menuRoll2.isButtonClicked("Tower7")) {
							player.pickHeroShoot(new Tower7(HeroShootType.Tower7, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower7");
						}
					}
					if (menuRoll2.checkButton("Tower8")) {
						if (menuRoll2.isButtonClicked("Tower8")) {
							player.pickHeroShoot(new Tower8(HeroShootType.Tower8, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower8");
						}
					}
					if (menuRoll2.checkButton("Tower9")) {
						if (menuRoll2.isButtonClicked("Tower9")) {
							player.pickHeroShoot(new Tower9(HeroShootType.Tower9, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("Tower9");
						}
					}
					if (menuRoll2.checkButton("TowerRed")) {
						if (menuRoll2.isButtonClicked("TowerRed")) {
							player.pickHeroShoot(new Tower_Red(HeroShootType.TowerRed, grid.getTile(0, 0),
									waveManager.getCurrentWave().getEnemyList()));
							menuRoll2.hideButton("TowerRed");
						}
					}
					if (roll.isButtonClicked("Roll")) {
						if (player.Cash >= 10) {
							soundRoll.Play(-0.0f);
							Roll();
						}
					}
					if (pauseMenu.isButtonClicked("Pause")) {
						StateManager.setState(GameState.PAUSE);
					}
				}
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
			
		} else {
			StateManager.setState(GameState.DEFEAT);
		}
	}

	public void update() {
		DrawQuadTex(menubackgound2, 0, 830, 2038, 300);
		grid.draw();
		waveManager.update();
		player.update();
		updateUI();
	}
}

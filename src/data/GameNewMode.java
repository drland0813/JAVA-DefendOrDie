package data;

import static helpers.Clock.*;

import java.security.PublicKey;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.print.attribute.standard.JobKOctets;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;

import UI.*;
import UI.UI.Menu;
import helpers.Music;
import helpers.StateManager;
import helpers.StateManager.GameState;

import static helpers.Artist.*;

public class GameNewMode {
	private Music soundRoll = new Music("/Music/roll.mp3");

	
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
//	private TestAnimation test;

	public GameNewMode(int[][] map) {
		// khởi tạo map
		grid = new TileGrid(map);
		/*
		 * waveManager = new WaveManager( new Enemy(QuickLoad("zb1"), grid.getTile(1,
		 * 0), grid, TILE_SIZE, TILE_SIZE, 70, 5000), 2, 2);
		 */
		// mảng chứa các loại Enemy/
		// aItem = new item(5,5,grid);
		// aItem.draw();
		enemyTypes = new Enemy[21];

		herolist = new Hero[2];


		listButtons = new Button[9];

		listButtons[0] = new Button("Missfortune", QuickLoadHero("miss"), 0, 0, 100, 100);
		listButtons[1] = new Button("Annie", QuickLoadHero("annie"), 0, 0, 100, 100);
		listButtons[2] = new Button("Jinx",  QuickLoadHero("jinx"), 0, 0, 100, 100);
		listButtons[3] = new Button("Leesin", QuickLoadHero("leesin"), 0, 0, 100, 100);
		listButtons[4] = new Button("Sona", QuickLoadHero("sona"), 0, 0, 100, 100);
		listButtons[5] = new Button("Yassuo", QuickLoadHero("ys"), 0, 0, 100, 100);
		listButtons[6] = new Button("Vayne", QuickLoadHero("vayne"), 0, 0, 100, 100);
		listButtons[7] = new Button("Syndra", QuickLoadHero("syndra"), 0, 0, 100, 100);
		listButtons[8] = new Button("Karthus", QuickLoadHero("karthus1"), 0, 0, 100, 100);
		
		
		// heroMove =new HeroMove(5, 5 , grid);

		enemyTypes[0] = new Enemy1(29, 1, grid);
		enemyTypes[1] = new Enemy2(29, 1, grid);
		enemyTypes[2] = new Enemy3(29, 1, grid);

		enemyTypes[3] = new Enemy1(29, 3, grid);
		enemyTypes[4] = new Enemy2(29, 3, grid);
		enemyTypes[5] = new Enemy3(29, 3, grid);

		enemyTypes[6] = new Enemy1(29, 3, grid);
		enemyTypes[7] = new Enemy2(29, 3, grid);
		enemyTypes[8] = new Enemy3(29, 3, grid);

		enemyTypes[9] = new Enemy1(29, 5, grid);
		enemyTypes[10] = new Enemy2(29, 5, grid);
		enemyTypes[11] = new Enemy3(29, 5, grid);

		enemyTypes[12] = new Enemy1(29, 7, grid);
		enemyTypes[13] = new Enemy2(29, 7, grid);
		enemyTypes[14] = new Enemy3(29, 7, grid);

		enemyTypes[15] = new Enemy1(29, 9, grid);
		enemyTypes[16] = new Enemy2(29, 9, grid);
		enemyTypes[17] = new Enemy3(29, 9, grid);

		enemyTypes[18] = new Enemy1(29, 11, grid);
		enemyTypes[19] = new Enemy2(29, 11, grid);
		enemyTypes[20] = new Enemy3(29, 11, grid);

		
		
		for(Enemy e: enemyTypes) {
			e.setOneWay(true);
		}
		waveManager = new WaveManager(enemyTypes, 10, 1);
		player = new Player(grid, waveManager);
		player.setup(100);

		this.menubackgound2 = QuickLoad("menubg");
		setupUI();
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

		// gameUi4.createMenu("act", 0, 850, 100, 100, 1, 0,0);
		// gameUi5.createMenu("cash", 1600 - 832, 950, 100, 100, 1, 0, 0);

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
		listButtons[0] = new Button("Missfortune", QuickLoadHero("miss"), 0, 0, 100, 100);
		listButtons[1] = new Button("Annie", QuickLoadHero("annie"), 0, 0, 100, 100);
		listButtons[2] = new Button("Jinx",  QuickLoadHero("jinx"), 0, 0, 100, 100);
		listButtons[3] = new Button("Leesin", QuickLoadHero("leesin"), 0, 0, 100, 100);
		listButtons[4] = new Button("Sona", QuickLoadHero("sona"), 0, 0, 100, 100);
		listButtons[5] = new Button("Yassuo", QuickLoadHero("ys"), 0, 0, 100, 100);
		listButtons[6] = new Button("Vayne", QuickLoadHero("vayne"), 0, 0, 100, 100);
		listButtons[7] = new Button("Syndra", QuickLoadHero("syndra"), 0, 0, 100, 100);
		listButtons[8] = new Button("Karthus", QuickLoadHero("karthus1"), 0, 0, 100, 100);
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
		
		for(int i = 0; i<100;i++) {
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
		// gameUi4.draw();
		// gameUi5.draw();
		gameUI.drawString2(0, 200, "Score: " + player.Score, 30);
		gameUI.drawString2(0, 300, "Wave: " + waveManager.getWave(),30);
		gameUI.drawString2(0, 600, "Lives: " + player.Lives,30);
		if (player.checkHero == null) {
			DrawQuadTex(QuickLoad("nullbutton"), 1187, 863, 100, 100);
		} else {
			DrawQuadTex(player.checkHero.getShowTexture(), 1187, 863, 100, 100);
			gameUI.drawString2(1400, 850, player.checkHero.getName(), 30);
		}
//		if (player.checkHeroshoot == null) {
//			DrawQuadTex(QuickLoad("nullbutton"), 1187, 863, 100, 100);
//		} else {
//			DrawQuadTex(player.checkHeroshoot.showTexture, 1187, 863, 100, 100);
//			gameUI.drawString2(1400, 850, player.checkHeroshoot.getName(), 30);
//		}
		DrawQuadTex(QuickLoad("cash"), 7, 831, 50, 50);
		gameUI.drawString2(64, 833, "Cash: " + player.Cash, 30);
		gameUI.drawString((1700 - 64 * 4) + 10, 1000 - 2, "Cost: " + player.cost);

		// gameUI.drawString(1700 - 300, 1000, "Range: " + player.range);
		gameUI.drawString((1700 - 64 * 4) + 10, 915, "Damage: " + player.dame);
		gameUI.drawString((1700 - 64 * 4) + 10, 955, "Firing Speed: " + player.attackspeed);


		// gameUI.drawString(1310, 700, "Enemies: " + player.EnemiesPerWave);
		gameUI.drawString(0, 0, "FPS: " + StateManager.frameInLastSecond);
		if (player.canplay) {

		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (menuRoll.checkButton("Missfortune")) {
					if (menuRoll.isButtonClicked("Missfortune")) {
						player.pickHero(new Hero_Missfortune(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Missfortune");
					}
					
				}
				//menu1
				if (menuRoll.checkButton("Annie")) {
					if (menuRoll.isButtonClicked("Annie")) {
						player.pickHero(new Hero_Annie(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Annie");
					}
				}
				
				//menu1
				if (menuRoll.checkButton("Jinx")) {
					if (menuRoll.isButtonClicked("Jinx")) {
						player.pickHeroShoot(new HeroShoot_Jinx(HeroShootType.Jinx, grid.getTile(0, 0),
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Jinx");
					}

				}
				//menu1
				if (menuRoll.checkButton("Leesin")) {
					if (menuRoll.isButtonClicked("Leesin")) {
						player.pickHero(new Hero_Leesin(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Leesin");
					}
				}
				
				//menu1
				if (menuRoll.checkButton("Karthus")) {
					if (menuRoll.isButtonClicked("Karthus")) {
						player.pickHero(new Hero_Karthus(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Karthus");

					}
				}
				if (menuRoll.checkButton("Yassuo")) {
					if (menuRoll.isButtonClicked("Yassuo")) {
						player.pickHero(new Hero_Yassuo(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Yassuo");

					}
				}
				if (menuRoll.checkButton("Vayne")) {
					if (menuRoll.isButtonClicked("Vayne")) {
						player.pickHeroShoot(new HeroShoot_Kalista(HeroShootType.Vayne, grid.getTile(0, 0),
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Vayne");

					}
				}
				if (menuRoll.checkButton("Syndra")) {
					if (menuRoll.isButtonClicked("Syndra")) {
						player.pickHero(new Hero_Syndra(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Syndra");

					}
				}
				if (menuRoll.checkButton("Sona")) {
					if (menuRoll.isButtonClicked("Sona")) {
						player.pickHero(new Hero_Sona(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll.hideButton("Sona");

					}
				}
				//menu2
				if (menuRoll2.checkButton("Missfortune")) {
					if (menuRoll2.isButtonClicked("Missfortune")) {
						player.pickHero(new Hero_Missfortune(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Missfortune");
					}
					
				}
				//menu2
				if (menuRoll2.checkButton("Annie")) {
					if (menuRoll2.isButtonClicked("Annie")) {
						player.pickHero(new Hero_Annie(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Annie");
					}
				}
				
				//menu1
				if (menuRoll2.checkButton("Jinx")) {
					if (menuRoll2.isButtonClicked("Jinx")) {
						player.pickHeroShoot(new HeroShoot_Jinx(HeroShootType.Jinx, grid.getTile(0, 0),
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Jinx");
					}

				}
				//menu1
				if (menuRoll2.checkButton("Leesin")) {
					if (menuRoll2.isButtonClicked("Leesin")) {
						player.pickHero(new Hero_Leesin(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Leesin");
					}
				}
				
				//menu1
				if (menuRoll2.checkButton("Karthus")) {
					if (menuRoll2.isButtonClicked("Karthus")) {
						player.pickHero(new Hero_Karthus(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Karthus");

					}
				}
				if (menuRoll2.checkButton("Yassuo")) {
					if (menuRoll2.isButtonClicked("Yassuo")) {
						player.pickHero(new Hero_Yassuo(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Yassuo");

					}
				}
				if (menuRoll2.checkButton("Vayne")) {
					if (menuRoll2.isButtonClicked("Vayne")) {
						player.pickHeroShoot(new HeroShoot_Kalista(HeroShootType.Vayne, grid.getTile(0, 0),
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Vayne");

					}
				}
				if (menuRoll2.checkButton("Syndra")) {
					if (menuRoll2.isButtonClicked("Syndra")) {
						player.pickHero(new Hero_Syndra(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Syndra");

					}
				}
				if (menuRoll2.checkButton("Sona")) {
					if (menuRoll2.isButtonClicked("Sona")) {
						player.pickHero(new Hero_Sona(TILE_SIZE, TILE_SIZE, grid,
								waveManager.getCurrentWave().getEnemyList()));
						menuRoll2.hideButton("Sona");

					}
				}

				if (roll.isButtonClicked("Roll")) {
					if (player.Cash>=10) {
						soundRoll.Play(-0.0f);
						Roll();
					}


				}
				if (pauseMenu.isButtonClicked("Pause")) {
					StateManager.setState(GameState.PAUSE);
				}
				/*
				 * if (act.isButtonClicked("Home")) { System.out.println("Home"); }
				 */

				/*
				 * if (towerPickerMenu.isButtonClicked("Item")) { Random random = new Random();
				 * herochosen = random.nextInt(herolist.length); herolist[0] = new
				 * Hero_Karthus(TILE_SIZE, TILE_SIZE, grid,
				 * waveManager.getCurrentWave().getEnemyList()); herolist[1] = new
				 * Hero_Kogmaw(TILE_SIZE, TILE_SIZE, grid,
				 * waveManager.getCurrentWave().getEnemyList());
				 * player.pickHero(herolist[herochosen]); }
				 */
			}
		}
		}
		else {
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

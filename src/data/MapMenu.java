package data;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import UI.*;
import helpers.Clock;
import helpers.Music;
import helpers.StateManager;
import helpers.StateManager.GameState;

import static helpers.Artist.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

public class MapMenu {
	//public Music sound = new Music("/Music/BG/bg1.mp3");
	private Texture background;
	private UI menuUI;
	private boolean leftMouseButtonDown;
	
	public MapMenu() {
		leftMouseButtonDown = true;
		background = QuickLoad("bgmap");
		//sound.Play(-30.0f);
		menuUI = new UI();
		menuUI.addButton("Map1", "Java_map1", WIDTH/2 -(64*11), (int) (HEIGHT * 0.40f)-(HEIGHT/4),256,256);
		menuUI.addButton("Map2", "Java_map2", WIDTH / 2 - (64*6)+64, (int) (HEIGHT * 0.40f)-(HEIGHT/4),256,256);
		menuUI.addButton("Map3", "Java_map3", WIDTH / 2 - (64*1)+128, (int) (HEIGHT * 0.40f)-(HEIGHT/4),256,256);
		menuUI.addButton("Map4", "Java_map4", WIDTH / 2 + (64*7), (int) (HEIGHT * 0.40f)-(HEIGHT/4),256,256);
		
		
		menuUI.addButton("Map5", "Java_map5", WIDTH / 2 - (64*6)+64-207, (int) (HEIGHT * 0.40f)+(HEIGHT/4)-30,256,256);
		menuUI.addButton("Map6", "Java_map6", WIDTH / 2 - (64*1)+128-207, (int) (HEIGHT * 0.40f)+(HEIGHT/4)-30,256,256);
		menuUI.addButton("Map7", "Java_map7", WIDTH / 2 + (64*7)-207, (int) (HEIGHT * 0.40f)+(HEIGHT/4)-30,256,256);
		
		
		menuUI.addButton("Volume", "volume", 60, 50);
		menuUI.addButton("Rank", "rank", 30, 120);

		//menuUI.addButton("Editor", "Editor", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		//menuUI.addButton("Quit", "Quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
	}
	

	private void updateButtons() {
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			if (menuUI.isButtonClicked("Map1"))
			{ 
				StateManager.setState(GameState.MAP1);
			}
			if (menuUI.isButtonClicked("Map2"))
			{ 
				StateManager.setState(GameState.MAP2);
			}
			
			if (menuUI.isButtonClicked("Map3"))
			{ 
				StateManager.setState(GameState.MAP3);
			}
			if (menuUI.isButtonClicked("Map4"))
			{ 
				StateManager.setState(GameState.MAP4);
			}
			if (menuUI.isButtonClicked("Map5"))
			{ 
				StateManager.setState(GameState.MAP5);
			}
			if (menuUI.isButtonClicked("Map6"))
			{ 
				StateManager.setState(GameState.MAP6);
			}
			if (menuUI.isButtonClicked("Map7"))
			{ 
				StateManager.setState(GameState.MAP7);
			}
			if (menuUI.isButtonClicked("Volume"))
				MainMenu.sound.stop();
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

	public void info() {
		menuUI.drawString2( WIDTH/2 -(64*11)+110-20, (int) (HEIGHT * 0.65f)-(HEIGHT/4)+10, "MAP 1",30);
		menuUI.drawString2( WIDTH / 2 - (64*6)+64+110-20, (int) (HEIGHT * 0.65f)-(HEIGHT/4)+10, "MAP 2",30);
		menuUI.drawString2( WIDTH/2 - (64*1)+128+110-20, (int) (HEIGHT * 0.65f)-(HEIGHT/4)+10, "MAP 3",30);
		menuUI.drawString2(  WIDTH / 2 + (64*7)+110-20, (int) (HEIGHT * 0.65f)-(HEIGHT/4)+10, "MAP 4",30);
		
		menuUI.drawString2( WIDTH/2 -(64*11)+110+207-50+3-2, (int) (HEIGHT * 0.65f)+(HEIGHT/4)-15, "MAP 5",30);
		menuUI.drawString2( WIDTH / 2 - (64*6)+64+110+207-50-2, (int) (HEIGHT * 0.65f)+(HEIGHT/4)-15, "MAP 6",30);
		menuUI.drawString2( WIDTH/2 - (64*1)+128+110+207-50-2, (int) (HEIGHT * 0.65f)+(HEIGHT/4)-15, "MAP 7",30);
	}
	public void update() {
		DrawQuadTex(background, 0, 0, 2048, 2000);
		menuUI.draw();
		info();
		updateButtons();
		

	}
}

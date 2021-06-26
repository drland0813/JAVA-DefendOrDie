package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import UI.*;
import helpers.Clock;
import helpers.Music;
import helpers.StateManager;
import helpers.StateManager.GameState;

import static helpers.Artist.*;

public class MainMenu {
	public static Music sound = new Music("/Music/BG/bg1.mp3");
	private Texture background;
	private UI menuUI;
	private boolean leftMouseButtonDown;

	public MainMenu() {
		leftMouseButtonDown = false;
		background = QuickLoad("b3a");
		sound.Play(-30.0f);
		menuUI = new UI();
		menuUI.addButton("Classic", "Play", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		menuUI.addButton("Beta", "Play2", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
		//menuUI.addButton("Editor", "Editor", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit", "Quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.75f));
		
		menuUI.addButton("Volume", "volume", 60, 50);
		menuUI.addButton("Rank", "rank", 30, 120);
	}
	
	
	public MainMenu(Texture a) {
		background = a;
		//sound.Play(-30.0f);
		menuUI = new UI();
		menuUI.addButton("Play", "Editor", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Play2", "Play2", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		//menuUI.addButton("Editor", "Editor", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit", "Quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
	}

	private void updateButtons() {
		if (Mouse.isButtonDown(0) &&!leftMouseButtonDown) {
			if (menuUI.isButtonClicked("Classic"))
				StateManager.setState(GameState.SELECTMAP);
			if (menuUI.isButtonClicked("Beta"))
				StateManager.setState(GameState.BETA);
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
			if (menuUI.isButtonClicked("Volume"))
				sound.stop();
			
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

	public void update() {
		DrawQuadTex(background, 0, 0, 2048, 2000);
		menuUI.draw();
		updateButtons();

	}
}

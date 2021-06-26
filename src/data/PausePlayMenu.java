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

public class PausePlayMenu {
	//public Music sound = new Music("/Music/BG/bg1.mp3");
	private Texture background;
	private UI menuUI;
	private boolean leftMouseButtonDown;

	public PausePlayMenu() {
		background = QuickLoad("pausemenu");
		//sound.Play(-30.0f);
		menuUI = new UI();
		menuUI.addButton("Resume", "resume",  WIDTH / 2 - 128-50, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Retry", "retry",  WIDTH / 2 - 128-50, (int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit", "Quit", WIDTH / 2 - 128-50, (int) (HEIGHT * 0.75f));
		menuUI.addButton("Home", "home", WIDTH / 2 - 128-50, (int) (HEIGHT * 0.65f));
		
		menuUI.addButton("Volume", "volume", 60, 50);
		menuUI.addButton("Rank", "rank", 30, 120);
	}

	
	private void updateButtons() {
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			if (menuUI.isButtonClicked("Resume"))
				StateManager.setState(GameState.RESUME);
			if (menuUI.isButtonClicked("Retry"))
				StateManager.setState(GameState.SELECTMAP);
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
			if (menuUI.isButtonClicked("Home"))
				StateManager.setState(GameState.MAIN_MENU);
			if (menuUI.isButtonClicked("Volume"))
				MainMenu.sound.stop();
			/*
			 * if (menuUI.isButtonClicked("Editor"))
			 * StateManager.setState(GameState.EDITOR);
			 */
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

package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.*;
import static helpers.Leveler.*;
import static helpers.Artist.QuickLoad;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.Text;

import UI.Button;
import UI.UI;
import UI.UI.Menu;
import helpers.Clock;

public class Editor {
	private TileGrid grid;
	private int index;
	private TileType[] types; 
	private UI editorUI;
	private Menu tilePickerMenu ;
	private Texture menubackground;

	public Editor() {
		this.grid = loadMap("newMap1");
		this.index = 0;
		this.types = new TileType[3];
		this.types[0] = TileType.TTopL;
		this.types[1] = TileType.Way;
		this.types[2] = TileType.TopR;
		this.menubackground = QuickLoad("b3a");
		setupUI();
	}

	private void setupUI() {
		editorUI = new UI();
		editorUI.createMenu("TilePicker",  1280, 0, 192, 960, 2, 0,0);
		tilePickerMenu = editorUI.getMenu("TilePicker");
		tilePickerMenu.quickAdd("Grass", "g1");
		tilePickerMenu.quickAdd("Dirt", "dirt4");
		
	}

	public void update() {
		draw();
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (tilePickerMenu.isButtonClicked("Grass")) {
					index = 0;
				}
				else if (tilePickerMenu.isButtonClicked("Dirt")) {
					index = 1;
				}
				else {
					setTile();
				}
			}
		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
				saveMap("newMap1",grid);
			}
		}
	}
	private void setTile() {
		grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
				types[index]);
	}
	
	private void draw() {
		DrawQuadTex(menubackground, 1280, 0, 192, 960);
		editorUI.draw();
		grid.draw();
	}
	
	//Cho phép chỉnh sửa kiểu của ô đang được chọn
	private void moveIndex() {
		index ++;
		if (index > types.length-1) {
			index = 0;
		}
	}
}

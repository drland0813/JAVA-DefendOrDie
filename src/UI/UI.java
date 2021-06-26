package UI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;

public class UI {

	private ArrayList<Button> buttonList;
	private ArrayList<Menu> menuList;
	private TrueTypeFont font, font2;
	private Font awtFont,awtFont2;
	

	
	public UI() {
		buttonList = new ArrayList<Button>();
		menuList = new ArrayList<UI.Menu>();
		awtFont = new Font("Time New Roman", Font.BOLD, 20);
		awtFont2 = new Font("Time New Roman", Font.BOLD, 30);
		font = new TrueTypeFont(awtFont, false);
		font2 = new TrueTypeFont(awtFont2, false);
	}
	
	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
	}
	
	public void drawString2(float x, float y, String text,int size) {
		awtFont2 = new Font("Time New Roman", Font.BOLD, size);
		font2.drawString(x, y, text);
	}


	public void addButton(String name, String textureName, int x, int y) {
		buttonList.add(new Button(name, QuickLoad(textureName), x, y));
	}
	public void addButton(String name, String textureName, int x, int y, int widht, int height) {
		buttonList.add(new Button(name, QuickLoad(textureName), x, y,widht,height));
	}

	public void resetMenu() {
		menuList.clear();
	}

	private Button getButton(String buttonName) {
		for (Button b : buttonList) {
			if (b.getName().equals(buttonName)) {
				return b;
			}
		}
		return null;
	}

	public boolean isButtonClicked(String buttonName) {
		Button button = getButton(buttonName);
		float mouseY = HEIGHT - Mouse.getY() - 1;
		if (Mouse.getX() > button.getX() && Mouse.getX() < button.getX() + button.getWidth() && mouseY > button.getY()
				&& mouseY < button.getY() + button.getHeight()) {
			return true;
		}
		return false;
	}

	public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight,int a) {
		menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight,a));
	}

	public Menu getMenu(String name) {
		for (Menu a : menuList) {
			if (name.equals(a.getName())) {
				return a;
			}
		}
		return null;
	}

	public void draw() {
		for (Button c : buttonList) {
			DrawQuadTex(c.getTexture(), c.getX(), c.getY(), c.getWidth(), c.getHeight());
		}
		for (Menu a : menuList) {
			a.draw();
		}
	}

	public class Menu {
		String name;
		private ArrayList<Button> menuButtons;
		private int amountButtons, x, y, width, height, optionsWidth, optionsHeight, padding;

		public Menu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight, int a) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.optionsWidth = optionsWidth;
			this.optionsHeight = optionsHeight;
			this.padding = (width - (optionsWidth * TILE_SIZE)) / (optionsWidth+a) ;
			this.amountButtons = 0;
			this.menuButtons = new ArrayList<Button>();
		}
		

		public void addButtons(Button b) {
			setButton(b);
		}

		public void resetButton() {
			menuButtons.clear();
		}

		public void quickAdd(String name, String buttonTextureName) {
			Button b = new Button(name, QuickLoad(buttonTextureName), 0, 0);
			setButton(b);
		}

		private void setButton(Button b) {
			if (optionsWidth != 0)
				b.setY(y + ((amountButtons / optionsWidth)) * TILE_SIZE);
			b.setX(x + (amountButtons % 2) * (padding + TILE_SIZE) + padding);
			amountButtons++;
			menuButtons.add(b);
		}

		public boolean isButtonClicked(String buttonName) {
			Button cc = getButton(buttonName);
			if (cc.getShow()) {
				float mouseY = HEIGHT - Mouse.getY() - 1;
				if (Mouse.getX() > cc.getX() && Mouse.getX() < cc.getX() + cc.getWidth() && mouseY > cc.getY()
						&& mouseY < cc.getY() + cc.getHeight()) {
					return true;
				}
			}
			return false;

		}

		public Button getButton(String buttonName) {
			for (Button b : menuButtons) {
				if (b.getName().equals(buttonName)) {
					return b;
				}
			}
			return null;
		}

		public boolean checkButton(String buttonName) {
			for (Button b : menuButtons) {
				if (b.getName().equals(buttonName)) {
					return true;
				}
			}
			return false;
		}

		public void hideButton(String buttonName) {
			for (Button b : menuButtons) {
				if (b.getName().equals(buttonName)) {
					b.setState(false);
					b.setTexture(QuickLoad("nullbutton"));
				}
			}
		}
		public void showButton(String buttonName) {
			for (Button b : menuButtons) {
				if (b.getName().equals(buttonName)) {
					b.setState(true);
				}
			}
		}

		public void draw() {
			for (Button a : menuButtons) {
				DrawQuadTex(a.getTexture(), a.getX(), a.getY(), a.getWidth(), a.getHeight());
			}
		}

		public String getName() {
			return name;
		}

		public void setPad(int a) {
			padding = a;
		}
	}
}

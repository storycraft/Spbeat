package cf.kuiprux.spbeat.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import cf.kuiprux.spbeat.beatmap.object.Pos;
import cf.kuiprux.spbeat.game.drawable.Button;

public class ButtonPanel implements HBScene {
	
	public static final String ROOT = "res/texture/button/";
	
	private Button[][] buttonList;
	private Pos selected = null;
	
	public ButtonPanel() {
		buttonList = new Button[4][4];
	}
	
	public Button getButtonAt(int x, int y) {
		return buttonList[x][y];
	}
	
	public Pos getSelected() {
		return selected;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				buttonList[i][j].render(g, i, j, selected.equals(new Pos(i, j)));
			}
		}
	}

	protected void setProperties(boolean[][] availables, Image[][] buttonImages) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(selected == null && availables[i][j]) selected = new Pos(i, j);
				buttonList[i][j] = new Button(buttonImages[i][j], availables[i][j]);
			}
		}
	}
	
	//This is used for selecting button with arrow key
	@Override
	public void update(GameContainer gc, int g) {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_UP)) {
			moveSelect(Direction.UP);
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			moveSelect(Direction.RIGHT);
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			moveSelect(Direction.DOWN);
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			moveSelect(Direction.LEFT);
		}
	}
	
	public void moveSelect(Direction direction) {
		moveSelect(selected.x, selected.y, direction, false);
	}

	//This is also used for selecting button with arrow key
	private boolean moveSelect(int x, int y, Direction direction, boolean tried) {
		if (selected != null) {
			while(true) {
				switch (direction) {
				case UP:
					x--;
					break;
				case DOWN:
					x++;
					break;
				case RIGHT:
					y++;
					break;
				case LEFT:
					y--;
				}
				if(0 > x || x >= 4 || 0 > y || y >= 4) {
					if(tried) break;
					else {
						switch (direction) {
						case UP:
							x=3;
							break;
						case DOWN:
							x=0;
							break;
						case RIGHT:
							y=0;
							break;
						case LEFT:
							y=3;
						}	
						moveSelect(x, y, direction, true);
					}
				}
				if (buttonList[x][y].isAvailable()) {
					selected.x = x;
					selected.y = y;
					return true;
				}
			}
		}
		return false;
	}
}

enum Direction {
	UP, RIGHT, DOWN, LEFT;
}
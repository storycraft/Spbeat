package cf.kuiprux.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import cf.kuiprux.Pos;
import cf.kuiprux.game.drawable.Button;
import cf.kuiprux.scenes.HBScene;

public class ButtonPanel implements HBScene {
	
	private Button[][] buttonList;
	Pos selected = null;
	
	public ButtonPanel() {
		buttonList = new Button[4][4];
	}
	
	public Button getButtonAt(int x, int y) {
		return buttonList[x][y];
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				buttonList[i][j].render(g, i, j, selected.equals(new Pos(i, j)));
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

	//This is also used for selecting button with arrow key
	public void moveSelect(Direction direction) {
		if (selected != null) {
			int x = selected.x;
			int y = selected.y;
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
				if(0 > x || x >= 4 || 0 > y || y >= 4) break;
				if (buttonList[x][y].isAvailable()) {
					selected.x = x;
					selected.y = y;
					break;
				}
			}
		}
	}
}

enum Direction {
	UP, RIGHT, DOWN, LEFT;
}
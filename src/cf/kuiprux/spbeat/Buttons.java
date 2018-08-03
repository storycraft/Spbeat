package cf.kuiprux.spbeat;

import org.newdawn.slick.Image;

import cf.kuiprux.spbeat.object.Direction;
import cf.kuiprux.spbeat.object.Pos;

public class Buttons {
	Button[][] buttons = new Button[4][4];
	Pos selected = null;

	public Buttons(boolean[][] availables, Image[][] buttonImages) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(selected == null && availables[i][j]) selected = new Pos(i, j);
				buttons[i][j] = new Button(buttonImages[i][j], availables[i][j]);
			}
		}
	}
	
	public Image[][] getImages() {
		Image[][] images = new Image[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				images[i][j] = buttons[i][j].getImage();
			}
		}
		return images;
	}
	
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
				if (buttons[x][y].available) {
					selected.x = x;
					selected.y = y;
					break;
				}
			}
		}
	}
	
	public Pos getSelected() {
		return selected;
	}
}

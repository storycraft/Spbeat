package cf.kuiprux.spbeat.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import cf.kuiprux.spbeat.Buttons;
import cf.kuiprux.spbeat.Reference;
import cf.kuiprux.spbeat.object.Direction;
import cf.kuiprux.spbeat.object.Pos;

public class HBSceneWithButtons implements HBScene {
	
	public static final String ROOT = "res/texture/button/";
	
	Buttons buttons;
	boolean[][] buttonAvailables;

	Image buttonBase, buttonAvailable, buttonSelected;

	HBSceneWithButtons() {
		try {
			buttonBase = new Image("res/texture/button/base/background.png");
			buttonAvailable = new Image("res/texture/button/base/available.png");
			buttonSelected = new Image("res/texture/button/base/selected.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void setProperties(boolean[][] buttonAvailables, Image[][] buttonImages) {
/*		Image[][] images = new Image[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (buttonImages[i][j].equals("")) {
					images[i][j] = null;
				} else {
					try {
						images[i][j] = new Image(ROOT + buttonImages[i][j]);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
			}
		}*/
		
		buttons = new Buttons(buttonAvailables, buttonImages);
		this.buttonAvailables = buttonAvailables;
	}

	@Override
	public void update(GameContainer gc, int g) {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_UP)) {
			buttons.moveSelect(Direction.UP);
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			buttons.moveSelect(Direction.RIGHT);
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			buttons.moveSelect(Direction.DOWN);
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			buttons.moveSelect(Direction.LEFT);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				renderButton(g, buttonBase, i, j);
			}
		}
		
		Image[][] images = buttons.getImages();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(images[i][j] != null) {
					renderButton(g, images[i][j], i, j);
				}
			}
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(buttonAvailables[i][j])
					renderButton(g, buttonAvailable, i, j);
			}
		}
		
		Pos selected = buttons.getSelected();
		if(selected != null) {
			renderButton(g, buttonSelected, selected.x, selected.y);
		}
	}
	
	public void renderButton(Graphics g, Image image, int x, int y) {
		float x1 = (float) (Reference.FRAME_GAP_WIDTH+(Reference.BUTTON_GAP_WIDTH+Reference.BUTTON_WIDTH)*y);
		float y1 = (float) (Reference.BUTTON_START+Reference.FRAME_GAP_HEIGHT+(Reference.BUTTON_GAP_HEIGHT+Reference.BUTTON_HEIGHT)*x);
		float x2 = (float) (x1+Reference.BUTTON_WIDTH);
		float y2 = (float) (y1+Reference.BUTTON_HEIGHT);
		g.drawImage(image, x1, y1, x2, y2, 0f, 0f, 1024f, 1024f);
	}

}

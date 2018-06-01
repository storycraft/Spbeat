package cf.kuiprux.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import cf.kuiprux.HighBeat;

public class HBTestScene implements HBScene {

	public void update(GameContainer gc, int g) {
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_A)) {
			((HighBeat)gc).getWindow().setScene(new HBTestScene2());
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.drawString("HI!", 50, 50);
	}

}

package cf.kuiprux.spbeat.scenes.temp;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.beatmap.object.Pos;
import cf.kuiprux.spbeat.scenes.ButtonPanel;

public class HBMainScene extends ButtonPanel {

	boolean[][] buttonAvailables = new boolean[4][4];
	Image[][] buttonImages = new Image[4][4];
	
	HBMainScene() {
		super();
		try {
			buttonAvailables[3][0] = true;
			buttonAvailables[3][3] = true;
			buttonImages[3][0] = new Image(ROOT+"start.png");
			buttonImages[3][3] = new Image(ROOT+"settings.png");
			setProperties(buttonAvailables, buttonImages);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer gc, int g) {
		super.update(gc, g);
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			Pos selected = getSelected();
			if(selected.x == 3 && selected.y == 0) {
				SpBeAt.instance.getMapHandler().loadMaps();
				SpBeAt.instance.getWindow().setScene(new HBMapsScene(0));
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		super.render(gc, g);
	}

}

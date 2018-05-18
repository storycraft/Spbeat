package cf.kuiprux.scenes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import cf.kuiprux.HighBeat;

public class HBStartScene implements HBScene {

	SpriteSheet startSheet;
	Animation startAn;
	boolean next = false;
	
	public HBStartScene() throws SlickException {
		startSheet = new SpriteSheet("res/texture/logo.png", 100, 100);
		startAn = new Animation(startSheet, 500);
		
	}
	
	@Override
	public void update(GameContainer gc, int g) {
		if(startAn.getFrame() == startAn.getFrameCount()-1) next = true; 
		if(startAn.getFrame() == 0 && next) HighBeat.WINDOW.setScene(new HBMainScene());
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		startAn.draw(100, 100);
	}

}

package cf.kuiprux.spbeat.scenes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import cf.kuiprux.spbeat.Reference;
import cf.kuiprux.spbeat.SpBeAt;

public class HBStartScene implements HBScene {

	SpriteSheet startSheet;
	Animation startAn;
	boolean next = false;
	
	public HBStartScene() {
		try {
			startSheet = new SpriteSheet("res/texture/loading.png", 100, 100);
			startAn = new Animation(startSheet, 50);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(GameContainer gc, int g) {
		if(startAn.getFrame() == startAn.getFrameCount()-1) next = true; 
		if(startAn.getFrame() == 0 && next) SpBeAt.WINDOW.setScene(new HBMainScene());
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setBackground(new Color(255, 255, 255));
		g.drawRect(0, 0, Reference.WIDTH, Reference.HEIGHT);
		startAn.draw((Reference.WIDTH-100)/2, (Reference.HEIGHT-100)/2);
	}

}

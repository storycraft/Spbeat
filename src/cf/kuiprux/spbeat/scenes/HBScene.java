package cf.kuiprux.spbeat.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

//import cf.kuiprux.HighBeat;

public interface HBScene {
/*
	private static int id;
	
	public static final void register(HBScene instance) {
		id = HighBeat.WINDOW.registerScene(instance);
	}
	
	public static final int getId() {
		return id;
	}
	*/
	public void update(GameContainer gc, int g);
	public void render(GameContainer gc, Graphics g);

}

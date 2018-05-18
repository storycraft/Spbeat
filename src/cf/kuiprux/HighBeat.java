package cf.kuiprux;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class HighBeat {
	
	public static final String NAME = "HighBeat";
	public static final HBWindow WINDOW = new HBWindow(NAME);

	public static void main(String[] args) throws SlickException {
		AppGameContainer agc = new AppGameContainer(WINDOW);
		agc.setDisplayMode(800, 600, false);
		agc.start();
	}
}

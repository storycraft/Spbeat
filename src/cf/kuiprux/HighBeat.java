package cf.kuiprux;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class HighBeat extends AppGameContainer {
	
	public static final String NAME = "HighBeat";
	
	private HBWindow window;
	
	public HighBeat(HBWindow game, String[] args) throws SlickException {
		super(game);
		
		this.window = game;
		
		init(args);
		start();
	}
	
	//시작 전 창 관련 요소 초기화
	private void init(String[] args) throws SlickException {
		setDisplayMode(800, 600, false);
	}
	
	public HBWindow getWindow() {
		return window;
	}

	public static void main(String[] args) {
		try {
			HBWindow window = new HBWindow(NAME);
			new HighBeat(window, args);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

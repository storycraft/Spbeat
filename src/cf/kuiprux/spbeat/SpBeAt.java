package cf.kuiprux.spbeat;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class SpBeAt extends AppGameContainer {
	
	public static final String NAME = "SpBeAt";
	
	private HBWindow window;
	
	public SpBeAt(HBWindow game, String[] args) throws SlickException {
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
			new SpBeAt(window, args);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

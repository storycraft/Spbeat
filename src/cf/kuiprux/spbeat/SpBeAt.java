package cf.kuiprux.spbeat;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import cf.kuiprux.spbeat.beatmap.MapHandler;

public class SpBeAt extends AppGameContainer {
	
	public static final String NAME = "SpBeAt";
	
	public static SpBeAt instance;
	private HBWindow window;
	private MapHandler mapHandler;
	
	public SpBeAt(HBWindow game) throws SlickException {
		super(game);
		
		this.window = game;
		mapHandler = new MapHandler();
	}
	
	//시작 전 창 관련 요소 초기화
	private void init(String[] args) throws SlickException {
		setDisplayMode(Reference.WIDTH, Reference.HEIGHT, false);
	}
	
	public HBWindow getWindow() {
		return window;
	}
	
	public MapHandler getMapHandler() {
		return mapHandler;
	}

	public static void main(String[] args) {
		try {
			HBWindow window = new HBWindow(NAME);
			instance = new SpBeAt(window);
			instance.init(args);
			instance.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

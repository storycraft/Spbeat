package cf.kuiprux.spbeat;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

public class WindowHost extends AppGameContainer implements AutoCloseable {
	
	//기본 창 크기. 변경 가능
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;

	public WindowHost(Game game) throws SlickException {
		super(game);
		
		init();
	}
	
	//시작 전 창 관련 요소 초기화
	private void init() throws SlickException {
		setDisplayMode(WIDTH, HEIGHT, false);
	}
	
	//시작시 호출
	@Override
	public void start() throws SlickException {
		super.start();
	}

	//닫힐때 자동 호출
	@Override
	public void close() throws Exception {
		
	}

}

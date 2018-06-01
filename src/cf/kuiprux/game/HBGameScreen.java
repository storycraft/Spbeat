package cf.kuiprux.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import cf.kuiprux.scenes.HBScene;

public class HBGameScreen implements HBScene {
	
	private PlayManager playManager;
	
	public HBGameScreen(PlayManager playManager) {
		this.playManager = playManager;
	}

	@Override
	public void update(GameContainer gc, int g) {
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		
	}

	public PlayManager getPlayManager() {
		return playManager;
	}

}

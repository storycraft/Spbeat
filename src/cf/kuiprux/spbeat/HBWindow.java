package cf.kuiprux.spbeat;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import cf.kuiprux.spbeat.game.ButtonPanel;
import cf.kuiprux.spbeat.scenes.HBScene;
import cf.kuiprux.spbeat.scenes.HBStartScene;

public class HBWindow extends BasicGame {

	private HBScene curScene;
	
	private ButtonPanel buttonPanel;
	
	public HBWindow(String title) {
		super(title);
		
		this.buttonPanel = new ButtonPanel();
	}
	
	public void setScene(HBScene scene) {
		curScene = scene;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(curScene != null) {
			curScene.render(gc, g);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.setScene(new HBStartScene());
	}

	@Override
	public void update(GameContainer gc, int g) throws SlickException {
		if(curScene != null) {
			curScene.update(gc, g);
		}
	}
	
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

}

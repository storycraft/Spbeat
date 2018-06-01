package cf.kuiprux;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import cf.kuiprux.game.ButtonPanel;
import cf.kuiprux.scenes.HBScene;
import cf.kuiprux.scenes.HBStartScene;
import cf.kuiprux.scenes.HBTestScene;

public class HBWindow extends BasicGame {

	//private List<HBScene> scenes = new ArrayList<HBScene>();
	//private List<Class<? extends HBScene>> sceneClasses = new ArrayList<Class<? extends HBScene>>();
	private HBScene curScene;
	
	private ButtonPanel buttonPanel;
	
	public HBWindow(String title) {
		super(title);
		
		this.buttonPanel = new ButtonPanel(16);
	}
	
	/*public boolean registerScene(HBScene scene) {
		if(sceneClasses.contains(scene.getClass())) return false;
		scenes.add(scene);
		sceneClasses.add(scene.getClass());
		return true;
	}
	
	public boolean setScene(Class<? extends HBScene> clazz) {
		int index = sceneClasses.indexOf(clazz);
		if(index == -1) return false;
		System.out.println(index);
		curScene = scenes.get(index);
		return true;
	}
*/
	public void setScene(HBScene scene) {
		curScene = scene;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(curScene != null) {
			curScene.render(gc, g);
		}
		
		//위치 수정 되어야 할것
		buttonPanel.draw(0f, (float) Reference.PANEL_GAP_HEIGHT);
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

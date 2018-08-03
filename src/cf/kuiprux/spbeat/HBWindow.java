package cf.kuiprux.spbeat;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import cf.kuiprux.spbeat.scenes.HBScene;
import cf.kuiprux.spbeat.scenes.HBScenes;
import cf.kuiprux.spbeat.scenes.HBStartScene;
import cf.kuiprux.spbeat.scenes.HBTestScene;

public class HBWindow extends BasicGame {

	//private List<HBScene> scenes = new ArrayList<HBScene>();
	//private List<Class<? extends HBScene>> sceneClasses = new ArrayList<Class<? extends HBScene>>();
	private HBScene curScene;
	
	public HBWindow(String title) {
		super(title);
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

}

package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.SimpleGame;
import cf.kuiprux.spbeat.WindowHost;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Shape;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Triangle;

class DrawableEffectTest extends SimpleGame {

	private Square box;

	public DrawableEffectTest() {
		super("drawable effect test");
	}
	

	@Test
	void test() {
		DrawableEffectTest game = new DrawableEffectTest();
		
		try (WindowHost window = new WindowHost(game)){
			window.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		super.init(container);
		
		Container testContainer = new SimpleContainer();
		
		box = new Square();
		
		box.setLocation(200, 200);
		box.setWidth(100);
		box.setHeight(100);
		
		box.setColor(Color.magenta);
		box.setBorderColor(Color.cyan);
		box.setBorderWidth(5);
		box.setOrigin(AlignMode.CENTRE);
		
		addChild(box);
		
		box.rotateTo(360, EasingType.LINEAR, 1000).fadeOut(EasingType.LINEAR, 1000).expire();
		
		onLoaded();
	}

	//업데이트 함수
	@Override
	public void updateInternal(int delta) {
		
	}

	//그리기 함수
	@Override
	public void drawInternal(Graphics graphics) {
		
	}
}
